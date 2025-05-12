package com.example.scheduleapi.repository.schedule;

import com.example.scheduleapi.domain.Schedule;
import com.example.scheduleapi.dto.schedule.response.ScheduleResponseDto;

public interface ScheduleRepository {
    //일정 저장
    ScheduleResponseDto saveSchedule(Schedule schedule);
}
