package com.example.api.user.dto.response;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserSaveResponseDto {
    private Long userId; //회원 고유 id
    private String name; //회원명
    @Email(message = "이메일 형식에 맞게 작성하세요.") //유효값 검증
    private String userEmail;//회원 이메일
    private LocalDateTime createdAt; //회원 가입일
    private LocalDateTime updatedAt; //회원 정보 변경일
}
