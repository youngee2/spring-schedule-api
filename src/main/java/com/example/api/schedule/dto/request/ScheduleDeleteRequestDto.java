package com.example.api.schedule.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleDeleteRequestDto {
    private String password; //서버에 삭제 요청할 때 비밀번호도 함께 전달
}
