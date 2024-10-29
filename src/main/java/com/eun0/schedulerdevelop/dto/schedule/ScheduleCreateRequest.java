package com.eun0.schedulerdevelop.dto.schedule;

import com.eun0.schedulerdevelop.entity.Schedule;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class ScheduleCreateRequest {
    @NotNull(message = "제목은 Null일 수 없습니다.")
    @Size(min=1, max=15, message = "제목은 최소 1자에서 최대 15자까지 가능합니다.")
    private String title;

    @NotNull(message = "내용은 Null일 수 없습니다.")
    @Size(min=1, max=200, message = "내용은 최소 1자에서 최대 200자까지 가능합니다.")
    private String content;

    public Schedule toEntity() {
        return new Schedule(this.title, this.content);
    }
}
