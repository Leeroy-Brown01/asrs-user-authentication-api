package com.brown.asrs_user_authentication_api.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDto {
    private Long id;
    private String fullName;
    private String email;      // username
    private String role;
    private Boolean active;
    private LocalDateTime createdAt;
}
