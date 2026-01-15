package com.brown.asrs_user_authentication_api.dto.auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AuthResponseDto {
    private Long id;
    private  String email;
    private String fullName;
    private String role;
    private Boolean active;
    private LocalDateTime createdAt;

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class EmailAlreadyInUseException extends RuntimeException {
        public EmailAlreadyInUseException(String message) {
            super(message);
        }
    }
}
