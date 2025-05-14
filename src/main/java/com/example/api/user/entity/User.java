package com.example.api.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId; //회원 고유 id
    private String name; //회원명
    private String userEmail;//회원 이메일
    private LocalDateTime createdAt; //회원 가입일
    private LocalDateTime updatedAt; //회원 정보 변경일
}
