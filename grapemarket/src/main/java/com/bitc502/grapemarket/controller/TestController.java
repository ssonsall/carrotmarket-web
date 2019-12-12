package com.bitc502.grapemarket.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.UserRepository;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private UserRepository uRepo;
	
	@GetMapping("/category")
	public @ResponseBody User category() {
		Optional<User> oUser = uRepo.findById(2);
		User user = oUser.get();
		return user;
	}

}
