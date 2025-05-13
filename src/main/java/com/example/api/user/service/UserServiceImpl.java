package com.example.api.user.service;

import com.example.api.user.dto.request.UserSaveRequestDto;
import com.example.api.user.dto.response.UserResponseDto;
import com.example.api.user.dto.response.UserSaveResponseDto;
import com.example.api.user.entity.User;
import com.example.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserSaveResponseDto saveUser(UserSaveRequestDto requestDto) {
        return userRepository.save(new User(
                requestDto.getUserId(),
                requestDto.getName(),
                requestDto.getUserEmail(),
                requestDto.getCreatedAt(),
                requestDto.getUpdatedAt()));
    }
}
