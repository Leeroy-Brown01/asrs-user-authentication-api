package com.brown.asrs_user_authentication_api.dto.auth;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegisterRequestDto {
    private String fullName;
    @Column(nullable = false, unique = true)
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
}
