package com.example.api.schedule.repository;

import com.example.api.schedule.entity.Schedule;
import com.example.api.schedule.dto.response.ScheduleResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ScheduleRepository {
    //일정 저장
    ScheduleResponseDto saveSchedule(Schedule schedule);
    //단건 일정 조회
    Optional<ScheduleResponseDto> findById(Long id);

    //전체 일정 조회
    //조건에 해당하는 전체 일정 조회
    List<ScheduleResponseDto> findAllFilterSchedules(Map<String,Object> result);

    //일정 삭제 
    int deleteSchedule(Long id, String password);

    //일정 수정
    Optional<ScheduleResponseDto> updateSchedule(Long id, String userName, String content, String password, LocalDateTime updateAt);
}
