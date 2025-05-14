package com.example.api.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScheduleUpdateRequestDto {
    //선택한 일정 내용 중 할일, 작성자명만 수정 가능, 비밀번호를 함께 전달
    private String userName; //작성자명
    @Size(max = 200, message = "일정은 최대 200자까지 가능합니다.") //최대 200자 이내로 제한
    @NotBlank(message = "일정 내용을 작성하세요") // 필수값 처리
    private String content; //할일
    private String password; //비밀번호 전달
}
