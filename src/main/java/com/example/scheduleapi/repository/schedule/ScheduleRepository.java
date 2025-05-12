package com.example.scheduleapi.repository.schedule;

import com.example.scheduleapi.domain.Schedule;
import com.example.scheduleapi.dto.schedule.response.ScheduleResponseDto;

import java.util.Optional;

public interface ScheduleRepository {
    //일정 저장
    ScheduleResponseDto saveSchedule(Schedule schedule);
    //단건 일정 조회
    Optional<ScheduleResponseDto> findById(Long id);
}
