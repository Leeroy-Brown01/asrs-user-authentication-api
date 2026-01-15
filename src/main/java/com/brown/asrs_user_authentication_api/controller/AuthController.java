
package com.brown.asrs_user_authentication_api.controller;

import com.brown.asrs_user_authentication_api.dto.auth.AuthResponseDto;
import com.brown.asrs_user_authentication_api.dto.auth.LoginRequestDto;
import com.brown.asrs_user_authentication_api.dto.auth.RegisterRequestDto;
import com.brown.asrs_user_authentication_api.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/auth")
    public class AuthController {

        private final AuthService authService;

        public AuthController(AuthService authService) {
            this.authService = authService;
        }

        // POST /api/auth/register
        @PostMapping("/register")
        public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto) {
            AuthResponseDto user = authService.register(registerRequestDto);
            return ResponseEntity.ok(user);
        }

        // POST /api/auth/login
        @PostMapping("/login")
        public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
            AuthResponseDto user = authService.login(loginRequestDto);
            return ResponseEntity.ok(user);
        }


    }


