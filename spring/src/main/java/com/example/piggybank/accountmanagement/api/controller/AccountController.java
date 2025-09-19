package com.example.piggybank.accountmanagement.api.controller;

import com.example.piggybank.accountmanagement.api.dto.request.AccountCreateRequest;
import com.example.piggybank.accountmanagement.api.dto.request.AccountUpdateRequest;
import com.example.piggybank.accountmanagement.api.dto.response.AccountCreateResponse;
import com.example.piggybank.accountmanagement.domain.entity.Account;
import com.example.piggybank.accountmanagement.domain.service.command.AccountCommandService;
import com.example.piggybank.accountmanagement.domain.service.facade.AccountFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("managementAccountController")
@RequestMapping("/api/v2/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountFacadeService accountService;

    @Operation(summary = "계좌 번호 등록", description = "사용자 계좌 번호 등록")
    @PostMapping("/create")
    public ResponseEntity<AccountCreateResponse> createAccount(@AuthenticationPrincipal String userId, @RequestBody AccountCreateRequest request) {
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
    
    @Operation(summary = "계좌 조회", description = "사용자 계좌 조회")
    @GetMapping("/get/id")
    public ResponseEntity<List<Account>> getAccountId(@AuthenticationPrincipal String userId) {
        
        List<Account> accountIds = accountService.getAccounts(userId);
        return ResponseEntity.ok(accountIds);
        
    }
}