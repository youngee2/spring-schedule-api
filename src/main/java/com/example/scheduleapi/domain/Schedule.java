package com.example.scheduleapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    private Long id; //일정 고유 id
    private String content; //할일
    private Long userId;//고유 id
    private String password; //일정 비밀번호
    private LocalDateTime createdAt; //일정 생성일
    private LocalDateTime updatedAt; //일정 수정일
}
