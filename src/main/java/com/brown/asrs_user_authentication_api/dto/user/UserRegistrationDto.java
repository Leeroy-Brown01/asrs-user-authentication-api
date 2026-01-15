package com.brown.asrs_user_authentication_api.dto.user;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String role;     // REVIEWER, ADMIN
    private Boolean active;
    private String password;
}
