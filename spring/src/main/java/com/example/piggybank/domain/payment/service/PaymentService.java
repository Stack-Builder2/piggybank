package com.example.piggybank.domain.payment.service;

import com.example.piggybank.domain.payment.dto.resp.PaymentResponse;
import com.example.piggybank.domain.payment.entity.Payment;
import java.util.UUID;

public interface PaymentService {
    public PaymentResponse getTransaction(UUID paymentId);
}
