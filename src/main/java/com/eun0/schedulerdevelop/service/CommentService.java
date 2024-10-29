package com.eun0.schedulerdevelop.service;

import com.eun0.schedulerdevelop.dto.comment.CommentRequest;
import com.eun0.schedulerdevelop.dto.comment.CommentResponse;
import com.eun0.schedulerdevelop.entity.Comment;
import com.eun0.schedulerdevelop.entity.Schedule;
import com.eun0.schedulerdevelop.entity.User;
import com.eun0.schedulerdevelop.repository.CommentRepository;
import com.eun0.schedulerdevelop.repository.ScheduleRepository;
import com.eun0.schedulerdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    UserRepository userRepository;
    ScheduleRepository scheduleRepository;
    CommentRepository commentRepository;

    @Transactional
    public CommentResponse createComment(Long userId, Long scheduleId, CommentRequest requestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 DB에 존재하지 않습니다."));
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("해당 일정이 DB에 존재하지 않습니다."));

        Comment savedComment = commentRepository.save(Comment.from(requestDto, user, schedule));

        return CommentResponse.from(savedComment);
    }

    @Transactional
    public List<CommentResponse> readAllCommentsByScheduleId(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("해당 일정이 DB에 존재하지 않습니다."));
        List<Comment> comments = schedule.getCommentList();
        return comments.stream().map(CommentResponse::from).toList();
    }

    @Transactional
    public CommentResponse updateComment(Long scheduleId, Long commentId, CommentRequest requestDto) {
        scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("해당 일정이 DB에 존재하지 않습니다."));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 DB에 존재하지 않습니다."));

        comment.update(requestDto.getContent());
        return CommentResponse.from(comment);
    }

    @Transactional
    public void deleteComment(Long scheduleId, Long commentId) {
        scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("해당 일정이 DB에 존재하지 않습니다."));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 DB에 존재하지 않습니다."));

        commentRepository.delete(comment);
    }
}
