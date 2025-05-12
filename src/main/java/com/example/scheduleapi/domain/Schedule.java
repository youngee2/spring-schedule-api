package com.example.scheduleapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @JsonProperty("id")
    private Long id; //일정 고유 id
    @JsonProperty("content")
    private String content; //할일
    @JsonProperty("user_id")
    private Long userId;//고유 id
    @JsonProperty("password")
    private String password; //일정 비밀번호
    @JsonProperty("created_at")
    private LocalDateTime createdAt; //일정 생성일
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt; //일정 수정일


    // 저장 생성자
    public Schedule(String content, Long userId, String password) {
        this(null, content, userId, password, null, null);
    }
}
