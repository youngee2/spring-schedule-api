package com.example.api.schedule.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ScheduleCreateRequestDto {
    @JsonProperty("user_id")
    private Long userId; //작성자
    @Size(max = 200, message = "일정은 최대 200자까지 가능합니다.") //최대 200자 이내로 제한
    @NotBlank(message = "일정 내용을 작성하세요") // 필수값 처리
    private String content; //일정 내용
    @NotBlank(message = "비밀번호를 작성하세요") // 필수값 처리
    private String password; //비밀번호 전달
}
