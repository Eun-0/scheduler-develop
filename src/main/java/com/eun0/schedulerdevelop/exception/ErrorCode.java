package com.eun0.schedulerdevelop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // User Error Code
    USER_NOT_FOUND("ERR010", "해당 유저가 존재하지 않습니다.", HttpStatus.NOT_FOUND),

    // Schedule Error Code
    SCHEDULE_NOT_FOUND("ERR020", "해당 일정이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    SCHEDULE_NO_PERMISSION("ERR022", "해당 일정에 대한 편집 권한이 존재하지 않습니다.", HttpStatus.FORBIDDEN),

    // Comment Error Code
    COMMENT_NOT_FOUND("ERR030", "해당 댓글이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    COMMENT_NOT_FOUND_IN_SCHEDULE("ERR031", "해당 댓글이 해당 일정에 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    COMMENT_NO_PERMISSION("ERR032", "해당 댓글에 대한 편집 권한이 존재하지 않습니다.", HttpStatus.FORBIDDEN),;


    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}