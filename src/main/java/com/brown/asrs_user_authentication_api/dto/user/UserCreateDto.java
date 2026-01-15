package com.brown.asrs_user_authentication_api.dto.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class UserCreateDto {

    private Long id;
    private String fullName;
    private String email;     // username
    private String role;      // REVIEWER, ADMIN
    private Boolean active = true;
}
