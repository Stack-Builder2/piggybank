package com.example.piggybank.accountmanagement.domain.service.facade;

import com.example.piggybank.accountmanagement.api.dto.response.TransactionResponse;
import com.example.piggybank.accountmanagement.domain.entity.Transaction;
import com.example.piggybank.accountmanagement.domain.service.query.TransactionQueryService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionFacadeServiceImpl implements TransactionFacadeService {
    
    private final TransactionQueryService transactionQueryService;
    
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

}
