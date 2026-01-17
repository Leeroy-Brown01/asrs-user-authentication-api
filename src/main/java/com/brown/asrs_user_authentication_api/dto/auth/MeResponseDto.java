package com.brown.asrs_user_authentication_api.dto.auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class MeResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String role;
    private Boolean active;
    private LocalDateTime createdAt;

    public MeResponseDto(Long id, String fullName, String email, String phoneNumber, String address, String role, Boolean active, LocalDateTime createdAt) {
    }
}
