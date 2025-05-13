package com.example.api.schedule.service;

import com.example.api.schedule.dto.request.ScheduleCreateRequestDto;
import com.example.api.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.api.schedule.dto.response.ScheduleResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    //일정 저장
    ScheduleResponseDto saveSchedule(ScheduleCreateRequestDto requestDto);
    //일정 단건 조회
    ScheduleResponseDto findSchedule(Long id);

    //조건에 해당하는 일정 전체 조회
    List<ScheduleResponseDto> findAllFilterSchedules(String name, LocalDate updateAt);
    
    //해당 일정 삭제
    int deleteSchedule(Long id, String password);

    //해당 일정 수정
    ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto);
}
