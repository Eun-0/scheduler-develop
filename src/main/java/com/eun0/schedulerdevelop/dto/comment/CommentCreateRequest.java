package com.eun0.schedulerdevelop.dto.comment;

import com.eun0.schedulerdevelop.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentCreateRequest {
    @NotNull(message = "내용은 Null일 수 없습니다.")
    @Size(min=1, max=20, message = "댓글은 최소 1자에서 최대 20자까지 가능합니다.")
    private String content;

    @NotBlank(message = "작성자명은 공백일 수 없습니다.")
    @Size(min=1, max=5, message = "작성자명은 최소 1자에서 최대 5자까지 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣0-9\\p{Punct}]*$", message = "작성자명은 공백을 제외한 영어, 한글, 숫자, 특수기호만 가능합니다.")
    private String writer;

    public Comment toEntity() {
        return new Comment(this.content, this.writer);
    }
}
