package com.brown.asrs_user_authentication_api.service;

import com.brown.asrs_user_authentication_api.dto.user.UserCreateDto;
import com.brown.asrs_user_authentication_api.dto.user.UserResponseDto;
import com.brown.asrs_user_authentication_api.entity.User;
import com.brown.asrs_user_authentication_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET /api/users
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toUserResponse)
                .toList();
    }

    // POST /api/users
    public UserResponseDto createUser(UserCreateDto userCreateDto) {

        if (userCreateDto.getEmail() == null || userCreateDto.getEmail().isBlank()) {
            throw new RuntimeException("Email is required");
        }
        if (userCreateDto.getFullName() == null || userCreateDto.getFullName().isBlank()) {
            throw new RuntimeException("Full name is required");
        }
        if (userCreateDto.getRole() == null || userCreateDto.getRole().isBlank()) {
            throw new RuntimeException("Role is required");
        }

        String email = userCreateDto.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setEmail(email);
        user.setFullName(userCreateDto.getFullName().trim());
        user.setRole(userCreateDto.getRole().trim().toUpperCase());
        user.setActive(userCreateDto.getActive() != null ? userCreateDto.getActive() : true);

        // Temporary password (you'll replace this later with generated + hashed)
        user.setPassword("ChangeMe123!");

        User savedUser = userRepository.save(user);
        return toUserResponse(savedUser);
    }

    // PATCH /api/users/{userId}
    public UserResponseDto updateUser(Long id, UserCreateDto userUpdateDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userUpdateDto.getFullName() != null && !userUpdateDto.getFullName().isBlank()) {
            user.setFullName(userUpdateDto.getFullName().trim());
        }

        if (userUpdateDto.getRole() != null && !userUpdateDto.getRole().isBlank()) {
            user.setRole(userUpdateDto.getRole().trim().toUpperCase());
        }

        if (userUpdateDto.getActive() != null) {
            user.setActive(userUpdateDto.getActive());
        }

        User updatedUser = userRepository.save(user);
        return toUserResponse(updatedUser);
    }

    // DELETE /api/users/{id}
    @Transactional
    public String deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User with id " + id + " does not exist.");
        }

        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted.";
    }

    private UserResponseDto toUserResponse(User user) {

        UserResponseDto response = new UserResponseDto();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setRole(user.getRole());
        response.setActive(user.getActive());
        response.setCreatedAt(user.getCreatedAt());

        return response;
    }
}
