package com.eun0.schedulerdevelop.controller;

import com.eun0.schedulerdevelop.dto.comment.CommentCreateRequest;
import com.eun0.schedulerdevelop.dto.comment.CommentResponse;
import com.eun0.schedulerdevelop.dto.comment.CommentUpdateRequest;
import com.eun0.schedulerdevelop.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules/{scheduleId}/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // CREATE
    @PostMapping("")
    public CommentResponse createComment(@PathVariable Long scheduleId, @RequestBody @Valid CommentCreateRequest requestDto) {
        return commentService.createComment(requestDto);
    }

    // READ
    @GetMapping("")
    public List<CommentResponse> readAllComments(@PathVariable Long scheduleId) {
        return commentService.readAllComments();
    }

    // UPDATE
    @PutMapping("/{commentId}")
    public CommentResponse updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody @Valid CommentUpdateRequest requestDto) {
        return commentService.updateComment(commentId, requestDto);
    }

    // DELETE
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
