package com.example.scheduleapi.controller;


import com.example.scheduleapi.dto.schedule.request.ScheduleCreateRequestDto;
import com.example.scheduleapi.dto.schedule.response.ScheduleResponseDto;
import com.example.scheduleapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules") //공통 url

public class ScheduleController {
    private final ScheduleService service;

    //생성자 주입
    public ScheduleController(ScheduleService service){
        this.service=service;
    }

    //일정 생성 API
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleCreateRequestDto requestDto){
        return new ResponseEntity<>(service.saveSchedule(requestDto),HttpStatus.CREATED);
    }


}
