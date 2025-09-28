package com.example.piggybank.domain.payment.service;

import com.example.piggybank.domain.payment.dto.resp.PaymentResponse;
import com.example.piggybank.domain.payment.entity.Payment;
import com.example.piggybank.domain.payment.repository.PaymentRepository;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentResponse getTransaction(UUID paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new EntityNotFoundException("결제 내역이 존재하지 않습니다."));

        return new PaymentResponse(
            payment.getPaymentId(),
            payment.getAccountId(),
            payment.getCategoryId(),
            payment.getDescription()
        );

    }
}
