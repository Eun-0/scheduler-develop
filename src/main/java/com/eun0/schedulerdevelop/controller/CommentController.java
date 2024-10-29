package com.eun0.schedulerdevelop.controller;

import com.eun0.schedulerdevelop.dto.comment.CommentRequest;
import com.eun0.schedulerdevelop.dto.comment.CommentResponse;
import com.eun0.schedulerdevelop.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules/{scheduleId}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public CommentResponse createComment(@RequestParam Long userId, @PathVariable Long scheduleId, @RequestBody @Valid CommentRequest requestDto) {
        return commentService.createComment(userId, scheduleId, requestDto);
    }

    @GetMapping("")
    public List<CommentResponse> readAllCommentsByScheduleId(@PathVariable Long scheduleId) {
        return commentService.readAllCommentsByScheduleId(scheduleId);
    }

    @PutMapping("/{commentId}")
    public CommentResponse updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody @Valid CommentRequest requestDto) {
        return commentService.updateComment(scheduleId, commentId, requestDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId) {
        commentService.deleteComment(scheduleId, commentId);
    }
}
