package com.example.api.schedule.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
//단건 조회
public class ScheduleResponseDto {
    private Long id; //일정 고유 id
    private String content; //할 일
    private String userName; //작성자명

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt; //글 작성 날짜
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt; //글 수정 날짜
}
