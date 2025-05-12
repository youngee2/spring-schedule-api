package com.example.scheduleapi.service;

import com.example.scheduleapi.domain.Schedule;
import com.example.scheduleapi.dto.schedule.request.ScheduleCreateRequestDto;
import com.example.scheduleapi.dto.schedule.response.ScheduleResponseDto;
import com.example.scheduleapi.repository.schedule.ScheduleRepository;
import com.example.scheduleapi.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;



    @Override
    public ScheduleResponseDto saveSchedule(ScheduleCreateRequestDto requestDto) {
      Schedule schedule = new Schedule(requestDto.getContent(),requestDto.getUserId(),requestDto.getPassword());
        return scheduleRepository.saveSchedule(schedule);
    }


    @Override
    public ScheduleResponseDto findSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 일정이 존재하지 않습니다."));
    }

}
