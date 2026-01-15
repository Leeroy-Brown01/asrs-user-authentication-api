package com.brown.asrs_user_authentication_api.service;

import com.brown.asrs_user_authentication_api.dto.auth.AuthResponseDto;
import com.brown.asrs_user_authentication_api.dto.auth.LoginRequestDto;
import com.brown.asrs_user_authentication_api.dto.auth.RegisterRequestDto;
import com.brown.asrs_user_authentication_api.entity.User;
import com.brown.asrs_user_authentication_api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Registration Logic: POST /api/auth/register
    public AuthResponseDto register(RegisterRequestDto registerRequestDto) {

        if (registerRequestDto.getEmail() == null || registerRequestDto.getEmail().isBlank()) {
            throw new RuntimeException("Email is required");
        }
        if (registerRequestDto.getPassword() == null || registerRequestDto.getPassword().isBlank()) {
            throw new RuntimeException("Password is required");
        }
        if (registerRequestDto.getFullName() == null || registerRequestDto.getFullName().isBlank()) {
            throw new RuntimeException("Full name is required");
        }

        String email = registerRequestDto.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setEmail(email);
        user.setFullName(registerRequestDto.getFullName().trim());
        user.setPassword(registerRequestDto.getPassword()); //  hash later
        user.setRole("APPLICANT");
        user.setActive(true);

        User savedUser = userRepository.save(user);

        return toAuthResponse(savedUser);
    }

    // Login Logic: POST /api/auth/login
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {

        if (loginRequestDto.getEmail() == null || loginRequestDto.getEmail().isBlank()) {
            throw new RuntimeException("Email is required");
        }
        if (loginRequestDto.getPassword() == null || loginRequestDto.getPassword().isBlank()) {
            throw new RuntimeException("Password is required");
        }

        String email = loginRequestDto.getEmail().trim().toLowerCase();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!Boolean.TRUE.equals(user.getActive())) {
            throw new RuntimeException("Account is disabled");
        }

        // Replace with BCrypt matches() later
        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // No save needed for login (unless you track lastLogin)
        return toAuthResponse(user);
    }

    private AuthResponseDto toAuthResponse(User user) {

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setId(user.getId());
        authResponseDto.setEmail(user.getEmail());
        authResponseDto.setFullName(user.getFullName());
        authResponseDto.setRole(user.getRole());
        authResponseDto.setActive(user.getActive());
        authResponseDto.setCreatedAt(user.getCreatedAt());
        return authResponseDto;
    }
}
