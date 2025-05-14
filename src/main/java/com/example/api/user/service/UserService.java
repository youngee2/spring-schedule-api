package com.example.api.user.service;

import com.example.api.user.dto.request.UserSaveRequestDto;
import com.example.api.user.dto.response.UserSaveResponseDto;

public interface UserService {

    //유저 가입
    UserSaveResponseDto saveUser(UserSaveRequestDto requestDto);
}
