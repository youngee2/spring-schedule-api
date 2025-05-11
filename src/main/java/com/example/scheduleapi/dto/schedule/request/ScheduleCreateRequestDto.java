package com.example.scheduleapi.dto.schedule.request;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ScheduleCreateRequestDto {
    private Long userName; //작성자
    private String content; //일정 내용
    private String password; //비밀번호 전달
}
