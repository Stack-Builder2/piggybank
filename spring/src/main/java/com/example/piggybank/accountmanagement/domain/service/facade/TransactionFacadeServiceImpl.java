package com.example.piggybank.accountmanagement.domain.service.facade;

import com.example.piggybank.accountmanagement.api.dto.request.CreateTransactionRequest;
import com.example.piggybank.accountmanagement.api.dto.request.GetTransactionRequest;
import com.example.piggybank.accountmanagement.api.dto.response.FromCodefResponse;
import com.example.piggybank.accountmanagement.api.dto.response.TransactionResponse;
import com.example.piggybank.accountmanagement.domain.entity.Account;
import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import com.example.piggybank.accountmanagement.domain.service.command.TransactionCommandService;
import com.example.piggybank.accountmanagement.domain.service.query.AccountQueryService;
import com.example.piggybank.accountmanagement.domain.service.query.TransactionQueryService;
import com.example.piggybank.accountmanagement.event.TransactionCreateEvent;
import com.example.piggybank.accountmanagement.util.TransactionCodefMapper;
import com.example.piggybank.global.codef.dto.res.CodefTransactionResDto.TranHistory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionFacadeServiceImpl implements TransactionFacadeService {
    
    private final TransactionCommandService transactionCommandService;
    private final TransactionQueryService transactionQueryService;
    private final AccountQueryService accountQueryService;
    private final ApplicationEventPublisher eventPublisher;
    
    @Override
    public TransactionResponse getTransaction(UUID transactionId) {
        
        Transaction transaction = transactionQueryService.getTransaction(transactionId);
            
        return new TransactionResponse(
            transaction.getTransactionId(),
            transaction.getAccountId(),
            transaction.getCategoryId(),
            transaction.getDescription()
        );
        
    }
    
    @Override
    public void getTransactionsByCodef(UUID userId, CreateTransactionRequest request) {
        UUID accountId = UUID.fromString(request.accountId());
        Account account = accountQueryService.getAccount(userId, accountId);
        eventPublisher.publishEvent(new TransactionCreateEvent(this, userId, account, request.startDate(), request.endDate()));
    }
    
    @Override
    public void createTransactions(List<TranHistory> transactions, UUID accountId) {
        
        List<FromCodefResponse> dtos = TransactionCodefMapper.fromList(transactions);
        
        for(FromCodefResponse dto : dtos) {
            boolean exists = transactionQueryService.existsByAccountIdAndTransactionDate(
                accountId,
                dto.transactionDate()
            );
            if(!exists){
                Transaction transaction = Transaction.builder()
                    .accountId(accountId)
                    .transactionDate(dto.transactionDate())
                    .amount(BigDecimal.valueOf(dto.amount()))
                    .description(dto.description())
                    .inputType(dto.inoutType())
                    .build();
                transactionCommandService.save(transaction);
            }
        }
    }
    
    @Override
    public List<Transaction> getTransactions(GetTransactionRequest request) {
        UUID accountId = UUID.fromString(request.accountId());
        LocalDateTime startDate = translateStringToLocalDateTime(request.startDate(), false);
        LocalDateTime endDate = translateStringToLocalDateTime(request.endDate(), true);
        List<Transaction> transactions = new ArrayList<>();
        
        transactions = transactionQueryService.getTransactions(accountId, startDate, endDate);
        
        return transactions;
    }
    
    private LocalDateTime translateStringToLocalDateTime(String date, boolean endOfDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        LocalDate ld = LocalDate.parse(date, formatter);
        return endOfDay ? ld.atTime(LocalTime.MAX) : ld.atStartOfDay();
    }
}
