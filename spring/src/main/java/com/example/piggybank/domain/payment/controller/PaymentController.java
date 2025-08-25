package com.example.piggybank.domain.payment.controller;

import com.example.piggybank.domain.payment.dto.resp.PaymentResponse;
import com.example.piggybank.domain.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
  private final PaymentService paymentService;

  @Operation(summary = "거래 소비 패턴 분석 조회", description = "계좌별 거래 소비 패턴 분석 조회")
  @GetMapping("/{id}")
  public ResponseEntity<PaymentResponse> getTransaction(@PathVariable UUID id) {
    return ResponseEntity.ok(paymentService.getTransaction(id));
  }

}
