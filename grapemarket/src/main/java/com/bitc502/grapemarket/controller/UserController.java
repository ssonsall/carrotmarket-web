package com.bitc502.grapemarket.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.security.MyUserDetails;
import com.bitc502.grapemarket.util.Script;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository uRepo;

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
			uRepo.save(user);
			return "/user/login";
		} catch (Exception e) {
			return "/user/join";
		}
	}

	@GetMapping("/userProfile")
	public String userProfile(@AuthenticationPrincipal MyUserDetails userDetail, Model model) {
		Optional<User> oUser = uRepo.findById(userDetail.getUser().getId());
		User user = oUser.get();
		model.addAttribute("user", user);
		return "/user/userProfile";
	}

	@PostMapping("/update")
	public String update(User user) {
		try {
			uRepo.update(user.getPassword(),user.getEmail(),user.getPhone(),user.getId());
			return "redirect:/";
		} catch (Exception e) {
		}
		return "/user/userProfile";
	}

	@PostMapping("/addupdate")
	public String addUpdate(User user) {
		try {
			uRepo.addUpdate(user.getAddress(), user.getAddressX(), user.getAddressY(), user.getId());
			return "redirect:/";
		} catch (Exception e) {
		}
		return "/user/userProfile";
	}
	
	@PostMapping("/authupdate")
	public String authUpdate(User user) {
		try {
			uRepo.authUpdate(user.getId());
			return "redirect:/";
		} catch (Exception e) {
		}
		return "/user/userProfile";
	}

	@GetMapping("/delete/{id}")
	public @ResponseBody String delete(@PathVariable int id) {
		try {
			uRepo.deleteById(id);
			return Script.href("/test/userAll");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Script.back("Fail Delete");
	}

}
