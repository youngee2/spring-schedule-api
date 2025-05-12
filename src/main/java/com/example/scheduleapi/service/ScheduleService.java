package com.example.scheduleapi.service;

import com.example.scheduleapi.dto.schedule.request.ScheduleCreateRequestDto;
import com.example.scheduleapi.dto.schedule.response.ScheduleResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    //일정 저장
    ScheduleResponseDto saveSchedule(ScheduleCreateRequestDto requestDto);
    //일정 단건 조회
    ScheduleResponseDto findSchedule(Long id);

    //조건에 해당하는 일정 전체 조회
    List<ScheduleResponseDto> findAllSchedules(String name, LocalDate updateAt);
}
