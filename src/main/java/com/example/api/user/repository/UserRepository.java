package com.example.api.user.repository;

import com.example.api.user.dto.response.UserSaveResponseDto;
import com.example.api.user.entity.User;

public interface UserRepository {
    UserSaveResponseDto save(User user);
}
