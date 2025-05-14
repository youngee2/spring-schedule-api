package com.example.api.schedule.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
//전체 조회
public class ScheduleListResponseDto {
    private Long id; //고유 id
    private String content; //할일
    private String userName; //작성자명

    @JsonFormat(pattern = "yyyy-MM-dd") 
    private LocalDateTime updatedAt; //날짜

}
