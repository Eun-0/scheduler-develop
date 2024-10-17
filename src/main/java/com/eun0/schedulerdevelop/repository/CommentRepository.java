package com.eun0.schedulerdevelop.repository;

import com.eun0.schedulerdevelop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
