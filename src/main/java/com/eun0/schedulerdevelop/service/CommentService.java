package com.eun0.schedulerdevelop.service;

import com.eun0.schedulerdevelop.dto.comment.CommentRequest;
import com.eun0.schedulerdevelop.dto.comment.CommentResponse;
import com.eun0.schedulerdevelop.entity.Comment;
import com.eun0.schedulerdevelop.entity.Schedule;
import com.eun0.schedulerdevelop.entity.User;
import com.eun0.schedulerdevelop.exception.ApplicationException;
import com.eun0.schedulerdevelop.exception.ErrorCode;
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
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponse createComment(Long userId, Long scheduleId, CommentRequest requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.SCHEDULE_NOT_FOUND));

        Comment savedComment = commentRepository.save(Comment.of(requestDto, user, schedule));

        return CommentResponse.from(savedComment);
    }

    @Transactional
    public List<CommentResponse> readAllCommentsByScheduleId(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.SCHEDULE_NOT_FOUND));
        List<Comment> comments = schedule.getCommentList();
        return comments.stream().map(CommentResponse::from).toList();
    }

    @Transactional
    public CommentResponse updateComment(Long scheduleId, Long commentId, CommentRequest requestDto, Long userId) {
        scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.SCHEDULE_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.COMMENT_NOT_FOUND));

        if (!scheduleId.equals(comment.getSchedule().getId())) {
            throw new ApplicationException(ErrorCode.COMMENT_NOT_FOUND_IN_SCHEDULE);
        }

        if (!userId.equals(comment.getUser().getId())) {
            throw new ApplicationException(ErrorCode.COMMENT_NO_PERMISSION);
        }

        comment.update(requestDto.getContent());
        return CommentResponse.from(comment);
    }

    @Transactional
    public void deleteComment(Long scheduleId, Long commentId, Long userId) {
        scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.SCHEDULE_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.COMMENT_NOT_FOUND));

        if (!scheduleId.equals(comment.getSchedule().getId())) {
            throw new ApplicationException(ErrorCode.COMMENT_NOT_FOUND_IN_SCHEDULE);
        }

        if (!userId.equals(comment.getUser().getId())) {
            throw new ApplicationException(ErrorCode.COMMENT_NO_PERMISSION);
        }

        commentRepository.delete(comment);
    }
}
