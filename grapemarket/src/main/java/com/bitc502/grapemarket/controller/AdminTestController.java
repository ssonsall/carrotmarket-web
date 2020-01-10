package com.bitc502.grapemarket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.Role;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.util.Script;

@Controller
@RequestMapping("/admin")
public class AdminTestController {

	@Autowired
	private UserRepository uRepo;

	@GetMapping({ "/", "" })
	public String dashboard() {
		return "admin/dashboard";
	}

	@GetMapping({ "/user" })
	public String userTable(Model model) {
		List<User> users = uRepo.findAll();
		model.addAttribute("users", users);
		return "admin/userTable";
	}

	@GetMapping({ "/stats" })
	public String stats() {
		return "admin/stats";
	}

	@GetMapping({ "/detail/{id}" })
	public String stats(@PathVariable int id, Model model) {
		Optional<User> oUser = uRepo.findById(id);
		User user = oUser.get();
		model.addAttribute("user", user);
		return "admin/userDetail";
	}

	@GetMapping({ "/userDelete/{id}" })
	public String userDelete(@PathVariable int id, Model model) {
		uRepo.deleteById(id);
		return "redirect:/admin/user";
	}

	@GetMapping({ "/changeRole/{id}/{role}" })
	public @ResponseBody String changeRole(@PathVariable int id, @PathVariable String role, Model model) {
		Optional<User> oUser = uRepo.findById(id);
		User user = oUser.get();
		if (role.equals("ADMIN")) {
			user.setRole(Role.valueOf("ADMIN"));
		} else if (role.equals("USER")) {
			user.setRole(Role.valueOf("USER"));
		}
		uRepo.save(user);
		return Script.hrefAndAlert("/admin/detail/" + id, "권한 변경완료");
	}

}
