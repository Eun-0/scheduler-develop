package com.eun0.schedulerdevelop.dto.schedule;

import com.eun0.schedulerdevelop.entity.Schedule;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class SchedulePagingResponse {
    private String title;
    private String content;
    private int commentsCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String writer;

    public static SchedulePagingResponse from(Schedule schedule) {
        return new SchedulePagingResponse(
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCommentList().size(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                schedule.getUser().getUsername()
        );
    }
}
