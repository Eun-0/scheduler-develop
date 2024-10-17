package com.eun0.schedulerdevelop.dto.schedule;

import com.eun0.schedulerdevelop.entity.Schedule;

import java.time.LocalDateTime;

public class SchedulePagingResponse {
    private String title;
    private String content;
    private int commentsCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String writer;

    public SchedulePagingResponse(String title, String content, int commentsCount, LocalDateTime createdAt, LocalDateTime modifiedAt, String writer) {
        this.title = title;
        this.content = content;
        this.commentsCount = commentsCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.writer = writer;
    }

    public static SchedulePagingResponse from(Schedule schedule) {
        return new SchedulePagingResponse(
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCommentList().size(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                schedule.getWriter()
        );
    }
}
