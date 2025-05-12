package com.example.scheduleapi.dto.schedule.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ScheduleCreateRequestDto {
    @JsonProperty("user_id")
    private Long userId; //작성자
    private String content; //일정 내용
    private String password; //비밀번호 전달
}
