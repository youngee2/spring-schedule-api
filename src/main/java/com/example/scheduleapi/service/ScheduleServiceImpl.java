package com.example.scheduleapi.service;

import com.example.scheduleapi.domain.Schedule;
import com.example.scheduleapi.dto.schedule.request.ScheduleCreateRequestDto;
import com.example.scheduleapi.dto.schedule.response.ScheduleResponseDto;
import com.example.scheduleapi.repository.schedule.ScheduleRepository;
import com.example.scheduleapi.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;



    @Override

    public ScheduleResponseDto saveSchedule(ScheduleCreateRequestDto requestDto) {

      Schedule schedule = new Schedule(requestDto.getContent(),requestDto.getUserId(),requestDto.getPassword());
        return scheduleRepository.saveSchedule(schedule);
    }
}
