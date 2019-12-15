package com.bitc502.grapemarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public @ResponseBody String write(Comment comment) {
		
		commentRepo.save(comment);
		
		return "ok";
		
	}

}
