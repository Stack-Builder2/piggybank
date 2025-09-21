package com.example.piggybank.accountmanagement.domain.service.facade;

import com.example.piggybank.accountmanagement.api.dto.request.CategoryIdUpdateRequest;
import com.example.piggybank.accountmanagement.api.dto.request.TransactionCreateRequest;
import com.example.piggybank.accountmanagement.api.dto.request.TransactionRequest;
import com.example.piggybank.accountmanagement.api.dto.response.FromCodefResponse;
import com.example.piggybank.accountmanagement.api.dto.response.TransactionResponse;
import com.example.piggybank.accountmanagement.domain.entity.Account;
import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import com.example.piggybank.accountmanagement.domain.service.command.TransactionCommandService;
import com.example.piggybank.accountmanagement.domain.service.query.AccountQueryService;
import com.example.piggybank.accountmanagement.domain.service.query.TransactionQueryService;
import com.example.piggybank.accountmanagement.event.TransactionCreateEvent;
import com.example.piggybank.accountmanagement.infrastructure.event.AnalyzeTransactionEvent;
import com.example.piggybank.accountmanagement.util.TransactionCodefMapper;
import com.example.piggybank.global.codef.dto.res.CodefTransactionResDto.TranHistory;
import java.math.BigDecimal;
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
    public void getTransactions(UUID userId, TransactionCreateRequest request) {
        UUID accountId = UUID.fromString(request.accountId());
        Account account = accountQueryService.getAccount(userId, accountId);
        log.info("transaction account = {}", account);
        eventPublisher.publishEvent(new TransactionCreateEvent(this, userId, account, request.startDate(), request.endDate()));
    }
    
    @Override
    public List<Object> createTransactions(List<TranHistory> transactions, UUID accountId) {
        
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
        
        
        
        return List.of();
    }

    @Override
    public void analyzeCategory(TransactionRequest request) {
        Transaction transaction = transactionQueryService.getTransaction(request.transactionId());

        eventPublisher.publishEvent(new AnalyzeTransactionEvent(this, request.description(), request.transactionId()));
    }




}
