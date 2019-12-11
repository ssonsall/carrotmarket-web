package com.bitc502.grapemarket.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.security.MyUserDetails;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository mUserRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String loginForm() {

		return "/user/login";
	}

	@GetMapping("/join")
	public String joinForm() {

		return "/user/join";
	}

	@PostMapping("/joinProc")
	public String join(User user) {
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		try {
			mUserRepo.save(user);
			return "/user/login";
		} catch (Exception e) {
			return "/user/join";
		}
	}

	@GetMapping("/userProfile")
	public String userProfile(@AuthenticationPrincipal MyUserDetails userDetail, Model model) {
		Optional<User> oUser = mUserRepo.findById(userDetail.getUser().getId());
		User user = oUser.get();
		model.addAttribute("user", user);
		return "/user/userProfile";
	}

}
