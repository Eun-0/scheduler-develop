package com.eun0.schedulerdevelop.controller;

import com.eun0.schedulerdevelop.dto.comment.CommentRequest;
import com.eun0.schedulerdevelop.dto.comment.CommentResponse;
import com.eun0.schedulerdevelop.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules/{scheduleId}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public  ResponseEntity<CommentResponse> createComment(
            @RequestParam Long userId,
            @PathVariable Long scheduleId,
            @RequestBody @Valid CommentRequest requestDto
    ) {
        CommentResponse response = commentService.createComment(userId, scheduleId, requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("")
    public  ResponseEntity<List<CommentResponse>> readAllCommentsByScheduleId(
            @PathVariable Long scheduleId
    ) {
        List<CommentResponse> responses = commentService.readAllCommentsByScheduleId(scheduleId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responses);
    }

    @PutMapping("/{commentId}")
    public  ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long scheduleId,
            @PathVariable Long commentId,
            @RequestBody @Valid CommentRequest requestDto,
            @RequestParam Long userId
    ) {
        CommentResponse response = commentService.updateComment(scheduleId, commentId, requestDto, userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{commentId}")
    public  ResponseEntity<Void> deleteComment(
            @PathVariable Long scheduleId,
            @PathVariable Long commentId,
            @RequestParam Long userId
    ) {

        commentService.deleteComment(scheduleId, commentId, userId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
