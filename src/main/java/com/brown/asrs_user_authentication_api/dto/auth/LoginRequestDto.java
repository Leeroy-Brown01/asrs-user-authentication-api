package com.brown.asrs_user_authentication_api.dto.auth;

import lombok.Data;
@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
