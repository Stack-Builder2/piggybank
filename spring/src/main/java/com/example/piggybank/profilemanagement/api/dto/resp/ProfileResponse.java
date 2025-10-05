package com.example.piggybank.profilemanagement.api.dto.resp;

import com.example.piggybank.global.common.dto.ResponseDto;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@Getter
public class ProfileResponse extends ResponseDto{
    @NotNull
    private UUID userId;
    private BigDecimal goal;
    private BigDecimal limit;

    public static ResponseEntity<ProfileResponse> success(UUID userId, String message){
        
        ProfileResponse body =new ProfileResponse(userId, BigDecimal.ZERO, BigDecimal.ZERO);
        
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(body);
    }
}
