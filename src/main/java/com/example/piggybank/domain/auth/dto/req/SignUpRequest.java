package com.example.piggybank.domain.auth.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {

        @Email(message = "유효한 이메일 형식이 아닙니다.")
        String email;

        String password;
        String ph;
}
