package com.example.api.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleDeleteRequestDto {
    private Long id;
    @NotBlank(message = "비밀번호를 입력하세요.") //비밀번호 필수값 처리
    private String password; //서버에 삭제 요청할 때 비밀번호도 함께 전달
}
