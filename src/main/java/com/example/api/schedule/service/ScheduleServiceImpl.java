package com.example.api.schedule.service;

import com.example.api.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.api.schedule.entity.Schedule;
import com.example.api.schedule.dto.request.ScheduleCreateRequestDto;
import com.example.api.schedule.dto.response.ScheduleResponseDto;
import com.example.api.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;


    //일정 저장 메서드
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleCreateRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto.getContent(), requestDto.getUserId(), requestDto.getPassword());
        return scheduleRepository.saveSchedule(schedule);
    }


    //단건 일정 조회 메서드
    @Override
    public ScheduleResponseDto findSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 일정이 존재하지 않습니다."));
    }

    //전체 조회 메서드
    @Override
    public List<ScheduleResponseDto> findAllFilterSchedules(String name,Long userId,LocalDate updatedAt) {
        List<Object> params = new ArrayList<>();
        //조건 저장
        List<String> conditions = new ArrayList<>();
        //repository로 넘길 때 리스트 2개 넘기기 위해 map 사용
        Map<String, Object> result=new HashMap<>();

        //name이 null이 아니고 공백이 아닐 때
        //name에 값이 있는 경우
        if (name != null && !name.isBlank()) {
            conditions.add("u.name = ?");
            params.add(name.trim()); // 공백 제거
        }

        //userId에 값이 있는 경우
        if (userId!=null) {
            conditions.add("u.user_id = ?");
            params.add(userId);
        }

        //updateAt이 null이 아닐 때
        if (updatedAt != null) {
            LocalDateTime from = updatedAt.atStartOfDay();
            LocalDateTime to = updatedAt.atTime(LocalTime.MAX);
            conditions.add("s.updated_at BETWEEN ? AND ?");
            params.add(from);
            params.add(to);
        }

        result.put("Params",params);
        result.put("conditions",conditions);

        return scheduleRepository.findAllFilterSchedules(result);
    }


    //삭제 메서드
    @Override
    public int deleteSchedule(Long id, String password) {

        // 삭제할 글이 없거나 password가 null이면
        if (id == null || password==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else{
            return scheduleRepository.deleteSchedule(id,password);
        }
    }

    // 수정 메서드
    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto) {
        if(dto.getUserName()==null || dto.getContent()==null || dto.getPassword().isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        ScheduleResponseDto responseDto = scheduleRepository
                .updateSchedule(id, dto.getUserName(), dto.getContent(), dto.getPassword(), LocalDateTime.now())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "수정 실패"));
        return responseDto;
    }
}
