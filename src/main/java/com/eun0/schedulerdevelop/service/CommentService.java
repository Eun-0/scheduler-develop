package com.eun0.schedulerdevelop.service;

import com.eun0.schedulerdevelop.dto.comment.CommentCreateRequest;
import com.eun0.schedulerdevelop.dto.comment.CommentResponse;
import com.eun0.schedulerdevelop.dto.comment.CommentUpdateRequest;
import com.eun0.schedulerdevelop.entity.Comment;
import com.eun0.schedulerdevelop.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentResponse createComment(CommentCreateRequest requestDto) {
        // RequestDTO -> Entity
        Comment comment = requestDto.toEntity();

        // DB 저장
        Comment savedComment = commentRepository.save(comment);

        // Entity -> ResponseDTO
        return CommentResponse.from(savedComment);
    }

    public List<CommentResponse> readAllComments() {
        return commentRepository.findAll().stream().map(CommentResponse::from).toList();
    }

    @Transactional
    public CommentResponse updateComment(Long id, CommentUpdateRequest requestDto) {
        // 해당 댓글이 DB에 존재하는지 확인
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 DB에 존재하지 않습니다."));

        // RequestDTO -> Entity
        comment.update(requestDto.getContent());

        // Entity -> ResponseDTO
        return CommentResponse.from(comment);
    }

    public void deleteComment(Long id) {
        // 해당 댓글이 DB에 존재하는지 확인
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 DB에 존재하지 않습니다."));

        commentRepository.delete(comment);
    }
}
