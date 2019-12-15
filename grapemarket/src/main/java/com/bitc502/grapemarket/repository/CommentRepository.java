package com.bitc502.grapemarket.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitc502.grapemarket.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findByBoardId(int boardId);
}
