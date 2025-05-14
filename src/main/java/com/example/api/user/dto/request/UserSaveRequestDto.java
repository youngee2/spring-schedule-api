package com.example.api.user.dto.request;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor

//회원정보 저장 요청 dto
public class UserSaveRequestDto {
    private Long userId; //회원 고유 id
    private String name; //회원명
    @Email(message = "이메일 형식에 맞게 작성하세요.")
    private String userEmail;//회원 이메일
    private LocalDateTime createdAt; //회원 가입일
    private LocalDateTime updatedAt; //회원 정보 변경일
}
