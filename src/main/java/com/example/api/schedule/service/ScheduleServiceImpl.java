package com.example.api.schedule.service;

import com.example.api.schedule.entity.Schedule;
import com.example.api.schedule.dto.request.ScheduleCreateRequestDto;
import com.example.api.schedule.dto.response.ScheduleResponseDto;
import com.example.api.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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

    @Override
    public List<ScheduleResponseDto> findAllSchedules(String name, LocalDate updatedAt) {
        //name이 null 또는 공백이고 update 시간이 null인 경우 findAllSchedules
        if(name==null || name.isBlank() && updatedAt==null){
            return scheduleRepository.findAllSchedules();
        }else{
            return scheduleRepository.findAllFilterSchedules(name,updatedAt);
        }
    }


}
