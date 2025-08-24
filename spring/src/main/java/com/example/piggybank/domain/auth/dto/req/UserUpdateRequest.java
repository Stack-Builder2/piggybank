package com.example.piggybank.domain.auth.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record UserUpdateRequest(

    @NotNull
    String email,

    @NotBlank(message = "비밀번호는 필수입니다.")
    String password
) {

}
