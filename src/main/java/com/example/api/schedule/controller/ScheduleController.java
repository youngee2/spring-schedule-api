package com.example.api.schedule.controller;

import com.example.api.schedule.dto.request.ScheduleCreateRequestDto;
import com.example.api.schedule.dto.request.ScheduleDeleteRequestDto;
import com.example.api.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.api.schedule.dto.response.ScheduleResponseDto;
import com.example.api.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedules") //공통 url
@RequiredArgsConstructor

public class ScheduleController {
    private final ScheduleService service;

    //일정 생성 API
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody @Valid ScheduleCreateRequestDto requestDto){
        return new ResponseEntity<>(service.saveSchedule(requestDto),HttpStatus.CREATED);
    }

    //해당 일정 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findSchedule(@PathVariable Long id){
        return new ResponseEntity<>(service.findSchedule(id),HttpStatus.OK);
    }

    //조건에 해당되는 전체 일정 조회 API
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long userId, //회원 아이디로도 조회 가능하게 수정
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate updatedAt
    ){
        List<ScheduleResponseDto> result = service.findAllFilterSchedules(name, userId,updatedAt);
        return new ResponseEntity<>(result,HttpStatus.OK);

    }

    //해당 일정 삭제 API
    @DeleteMapping
    public ResponseEntity<Void> deleteSchedule(@RequestBody ScheduleDeleteRequestDto dto) {
        service.deleteSchedule(dto.getId(),dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //해당 일정 수정 API
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateSchedule(@PathVariable Long id, @RequestBody @Valid ScheduleUpdateRequestDto dto) {
        service.updateSchedule(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
