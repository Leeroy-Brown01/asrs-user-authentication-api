package com.brown.asrs_user_authentication_api.dto.auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;

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


}
