package com.example.api.user.controller;


import com.example.api.user.dto.response.UserResponseDto;
import com.example.api.user.dto.request.UserSaveRequestDto;
import com.example.api.user.dto.response.UserSaveResponseDto;
import com.example.api.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")//공통 url
@RequiredArgsConstructor //생성자 주입

public class UserController {
    private final UserService userService;

    //유저 저장 API
    @PostMapping
    public ResponseEntity<UserSaveResponseDto> createUser(@RequestBody @Valid UserSaveRequestDto requestDto){
        return new ResponseEntity<>(userService.saveUser(requestDto), HttpStatus.CREATED);
    }

}
