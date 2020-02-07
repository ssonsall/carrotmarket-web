package com.bitc502.grapemarket.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.Comment;
import com.bitc502.grapemarket.repository.CommentRepository;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentRepository commentRepo;

	@PostMapping("/write")
	public @ResponseBody Comment write(Comment comment) {

		commentRepo.save(comment);
		Comment newComment = commentRepo.findTop1ByOrderByIdDesc();
		System.out.println(newComment.getId());

		return newComment;

	}

	@GetMapping("/delete/{id}")
	public @ResponseBody String delete(@PathVariable int id) {
		
		Optional<Comment> comment = commentRepo.findById(id);
		commentRepo.delete(comment.get());

		return "ok";
	}
}
