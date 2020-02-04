package com.bitc502.grapemarket.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc502.grapemarket.model.Comment;
import com.bitc502.grapemarket.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepo;

	public Comment save(Comment comment) {
		Comment newComment = new Comment();
		try {
			commentRepo.save(comment);
			newComment = commentRepo.findTop1ByOrderByIdDesc();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newComment;
	}

	public int delete(int id) {
		try {
			Optional<Comment> comment = commentRepo.findById(id);
			commentRepo.delete(comment.get());
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
