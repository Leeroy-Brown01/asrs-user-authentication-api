package com.brown.asrs_user_authentication_api.service;

import com.brown.asrs_user_authentication_api.dto.auth.MeResponseDto;
import com.brown.asrs_user_authentication_api.dto.auth.MeUpdateDto;
import com.brown.asrs_user_authentication_api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class MeService {
    private final UserRepository userRepository;

    public MeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public MeResponseDto getMe(Long Id) {
        return userRepository.findById(Id)
                .map(user -> new MeResponseDto(
                        user.getId(),
                        user.getFullName(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.getAddress(),
                        user.getRole(),
                        user.getActive(),
                        user.getCreatedAt()
                ))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public MeResponseDto updateMe(Long id, MeUpdateDto MeUpdateDto) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFullName(MeUpdateDto.getFullName());
                    user.setPhoneNumber(MeUpdateDto.getPhoneNumber());
                    user.setAddress(MeUpdateDto.getAddress());
                    userRepository.save(user);
                    return new MeResponseDto(
                            user.getId(),
                            user.getFullName(),
                            user.getEmail(),
                            user.getPhoneNumber(),
                            user.getAddress(),
                            user.getRole(),
                            user.getActive(),
                            user.getCreatedAt()
                    );
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
