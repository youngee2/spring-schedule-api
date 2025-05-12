package com.example.scheduleapi.service;

import com.example.scheduleapi.dto.schedule.request.ScheduleCreateRequestDto;
import com.example.scheduleapi.dto.schedule.response.ScheduleResponseDto;

public interface ScheduleService {
    //일정 저장
    ScheduleResponseDto saveSchedule(ScheduleCreateRequestDto requestDto);
    //일정 단건 조회
    ScheduleResponseDto findSchedule(Long id);
}
