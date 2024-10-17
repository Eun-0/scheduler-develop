package com.eun0.schedulerdevelop.dto.schedule;

import com.eun0.schedulerdevelop.entity.Schedule;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class ScheduleRequest {
    @NotBlank(message = "작성자명은 공백일 수 없습니다.")
    @Size(min=1, max=5, message = "작성자명은 최소 1자에서 최대 5자까지 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣0-9\\p{Punct}]*$", message = "작성자명은 공백을 제외한 영어, 한글, 숫자, 특수기호만 가능합니다.")
    private String writer;

    @NotNull(message = "제목은 Null일 수 없습니다.")
    @Size(min=1, max=15, message = "제목은 최소 1자에서 최대 15자까지 가능합니다.")
    private String title;

    @NotNull(message = "내용은 Null일 수 없습니다.")
    @Size(min=1, max=200, message = "내용은 최소 1자에서 최대 200자까지 가능합니다.")
    private String content;

    public Schedule toEntity() {
        return new Schedule(this.writer, this.title, this.content);
    }
}
