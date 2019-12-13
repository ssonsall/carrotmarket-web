package com.bitc502.grapemarket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.util.Script;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private BoardRepository bRepo;

	@GetMapping("/userAll")
	public @ResponseBody List<User> userAll() {
		List<User> Users = uRepo.findAll();
		return Users;
	}

	@GetMapping("/category")
	public @ResponseBody User category() {
		Optional<User> oUser = uRepo.findById(2);
		User user = oUser.get();
		return user;
	}

	@GetMapping("/board")
	public @ResponseBody List<Board> board() {
		List<Board> boards = bRepo.findAll();
		return boards;
	}
	

	@GetMapping("/boarddelete/{id}")
	public @ResponseBody String boardDelete(@PathVariable int id) {
		try {
			bRepo.deleteById(id);
			return Script.href("/test/board");
		} catch (Exception e) {
		}
		return Script.back("Fail Delete");
	}
	

	@GetMapping("/userdelete/{id}")
	public @ResponseBody String userDelete(@PathVariable int id) {
		try {
			uRepo.deleteById(id);
			return Script.href("/test/userAll");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Script.back("Fail Delete");
	}

}
