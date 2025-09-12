package com.refactoring.piggybank.membermanagement.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record PasswordChangeRequest(
    @NotNull
    String token,

    @NotBlank(message = "비밀번호는 필수입니다.")
    String password
) {

}
