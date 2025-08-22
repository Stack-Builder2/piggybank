package com.example.piggybank.domain.account.controller;

import com.example.piggybank.domain.account.dto.req.AccountCreateRequest;
import com.example.piggybank.domain.account.dto.req.AccountUpdateRequest;
import com.example.piggybank.domain.account.dto.resp.AccountResponse;
import com.example.piggybank.domain.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @Operation(summary = "계좌 번호 등록", description = "사용자 계좌 번호 등록")
  @PostMapping("/create")
  public ResponseEntity<AccountResponse> createAccount(@AuthenticationPrincipal String userId, @RequestBody AccountCreateRequest request) {
    return ResponseEntity.ok(accountService.createAccount(userId, request));
  }

  @Operation(summary = "계좌 관련 정보 수정", description = "사용자 계좌 관련 정보 수정")
  @PostMapping("/update")
  public ResponseEntity<String> updateAccount(@AuthenticationPrincipal String userId, UUID accountId, @RequestBody
      AccountUpdateRequest request) {
    accountService.updateAccount(userId, accountId, request);
    return ResponseEntity.ok("계좌 정보가 수정되었습니다.");
  }

  @Operation(summary = "계좌 정보 삭제", description = "사용자 계좌 정보 삭제")
  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteAccount(@AuthenticationPrincipal String userId, UUID accountId) {
    accountService.deleteAccount(userId, accountId);
    return ResponseEntity.ok("계좌 정보가 삭제되었습니다.");
  }

}
