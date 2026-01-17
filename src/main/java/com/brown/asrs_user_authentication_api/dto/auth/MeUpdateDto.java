package com.brown.asrs_user_authentication_api.dto.auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MeUpdateDto {
    private String fullName;
    private String phoneNumber;
    private String address;
}
