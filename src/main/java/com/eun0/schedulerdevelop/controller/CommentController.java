package com.eun0.schedulerdevelop.controller;

import com.eun0.schedulerdevelop.dto.comment.CommentCreateRequest;
import com.eun0.schedulerdevelop.dto.comment.CommentResponse;
import com.eun0.schedulerdevelop.dto.comment.CommentUpdateRequest;
import com.eun0.schedulerdevelop.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules/{schedule_id}/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // CREATE
    @PostMapping("")
    public CommentResponse createComment(@PathVariable("schedule_id") Long schedule_id, @RequestBody @Valid CommentCreateRequest requestDto) {
        return commentService.createComment(requestDto);
    }

    // READ
    @GetMapping("")
    public List<CommentResponse> readAllComments(@PathVariable("schedule_id") Long schedule_id) {
        return commentService.readAllComments();
    }

    // UPDATE
    @PutMapping("/{id}")
    public CommentResponse updateComment(@PathVariable("schedule_id") Long schedule_id, @PathVariable("id") Long id, @RequestBody @Valid CommentUpdateRequest requestDto) {
        return commentService.updateComment(id, requestDto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("schedule_id") Long schedule_id, @PathVariable("id") Long id) {
        commentService.deleteComment(id);
    }
}
