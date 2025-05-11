package com.example.scheduleapi.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserResponseDto {
    private Long userId; //회원 고유 id
    private String name; //회원명
}
