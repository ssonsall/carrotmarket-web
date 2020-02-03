package com.bitc502.grapemarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.Comment;
import com.bitc502.grapemarket.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentServ;

	@PostMapping("/write")
	public @ResponseBody Comment write(Comment comment) {
		return commentServ.save(comment);

	}

	@GetMapping("/delete/{id}")
	public @ResponseBody String delete(@PathVariable int id) {
		int result = commentServ.delete(id);
		if (result == -1) {
			return "no";
		}
		return "ok";
	}
}
