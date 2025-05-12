package com.example.scheduleapi.repository.schedule;

import com.example.scheduleapi.domain.Schedule;
import com.example.scheduleapi.dto.schedule.response.ScheduleResponseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    //일정 저장
    ScheduleResponseDto saveSchedule(Schedule schedule);
    //단건 일정 조회
    Optional<ScheduleResponseDto> findById(Long id);
    //전체 일정 조회
    //조건에 해당하는 전체 일정 조회
    List<ScheduleResponseDto> findAllFilterSchedules(String name, LocalDate updateAt);

    List<ScheduleResponseDto> findAllSchedules();
}
