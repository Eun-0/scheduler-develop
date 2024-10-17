package com.eun0.schedulerdevelop.dto.comment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentUpdateRequest {
    @NotNull(message = "내용은 Null일 수 없습니다.")
    @Size(min=1, max=20, message = "댓글은 최소 1자에서 최대 20자까지 가능합니다.")
    private String content;
}
