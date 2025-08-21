package com.example.piggybank.domain.signUp.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@NotNull
@NotBlank
public class SignUpRequest {

        @Email(message = "유효한 이메일 형식이 아닙니다.")
        String email;

        String password;
        String ph;
}
