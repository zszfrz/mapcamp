package com.mapcamp.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapcamp.domain.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
