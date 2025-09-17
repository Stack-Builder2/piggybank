package com.example.piggybank.accountmanagement.domain.service.facade;

import com.example.piggybank.accountmanagement.api.dto.response.TransactionResponse;
import java.util.UUID;

public interface TransactionFacadeService {
    public TransactionResponse getTransaction(UUID paymentId);
}
