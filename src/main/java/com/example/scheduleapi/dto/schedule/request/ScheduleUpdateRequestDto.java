package com.example.scheduleapi.dto.schedule.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleUpdateRequestDto {
    //선택한 일정 내용 중 할일, 작성자명만 수정 가능, 비밀번호를 함께 전달
    private String content; //할일
    private String userName; //작성자명
    private String password; //비밀번호 전달
}
