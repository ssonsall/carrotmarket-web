package com.bitc502.grapemarket.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bitc502.grapemarket.model.AuthProvider;
import com.bitc502.grapemarket.model.Role;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.security.UserPrincipal;
import com.bitc502.grapemarket.util.Script;
import com.bitc502.grapemarket.util.VisitorCounter;

@Controller
@RequestMapping("/user")
public class UserController {

	@Value("${file.path}")
	private String fileRealPath;

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String loginForm(HttpServletRequest request) {
		if (request.getHeader("DeviceType") != null && request.getHeader("DeviceType").equals("android")) {
			System.out.println("안드로이드 접속");
			VisitorCounter.currentVisitorCount--;
			return "redirect:/android/loginFailure";
		} else {
			System.out.println("Web 접속");
			return "/user/login";
		}

	}

	@GetMapping("/join")
	public String joinForm() {

		return "/user/join";
	}

	@PostMapping("/usernameCheck")
	public @ResponseBody String usernameCheck(@RequestBody String username) {
		if (uRepo.existsByUsername(username)) {
			return "no";
		} else {
			return "ok";
		}
	}

	@PostMapping("/joinProc")
	public String join(@RequestParam("username") String username, @RequestParam("name") String name,
			@RequestParam("password") String password, @RequestParam("email") String email,
			@RequestParam("phone") String phone, @RequestParam("userProfile") MultipartFile userProfile) {

		try {
			String rawPassword = password;
			String encPassword = passwordEncoder.encode(rawPassword);
			User user = new User();
			user.setUsername(username);
			user.setName(name);
			user.setPassword(encPassword);
			user.setEmail(email);
			user.setPhone(phone);
			user.setProvider(AuthProvider.local);
			user.setRole(Role.valueOf("USER"));

			if (userProfile.getSize() != 0) {
				String UUIDFileName = UUID.randomUUID() + "_" + userProfile.getOriginalFilename();
				user.setUserProfile(UUIDFileName);
				Path filePath = Paths.get(fileRealPath + UUIDFileName);
				Files.write(filePath, userProfile.getBytes());
			}
			uRepo.save(user);
			return "/user/login";
		} catch (Exception e) {
			e.printStackTrace();
			return "/user/join";
		}
	}

	@GetMapping("/userProfile")
	public String userProfile(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
		Optional<User> oUser = uRepo.findById(userPrincipal.getUser().getId());
		User user = oUser.get();
		model.addAttribute("user", user);
		return "/user/userProfile";
	}

	@PostMapping("/update")
	public String update(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam("id") int id,
			@RequestParam("password") String password, @RequestParam("email") String email,
			@RequestParam("phone") String phone, @RequestParam("currentUserProfile") String currentUserProfile,
			@RequestParam("userProfile") MultipartFile userProfile) {
		try {
			String rawPassword = password;
			String encPassword = passwordEncoder.encode(rawPassword);
			User user = new User();
			user.setId(id);
			user.setPassword(encPassword);
			user.setEmail(email);
			user.setPhone(phone);

			if (userProfile.getSize() != 0) {
				String UUIDFileName = UUID.randomUUID() + "_" + userProfile.getOriginalFilename();
				user.setUserProfile(UUIDFileName);
				Path filePath = Paths.get(fileRealPath + UUIDFileName);
				Files.write(filePath, userProfile.getBytes());
			} else {
				user.setUserProfile(currentUserProfile);
			}

			uRepo.update(user.getPassword(), user.getEmail(), user.getPhone(), user.getUserProfile(), user.getId());
			userPrincipal.getUser().setUserProfile(user.getUserProfile());
			return "redirect:/user/userProfile";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/user/userProfile";
		}
	}

	@PostMapping("/addupdate")
	public String addUpdate(User user, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		try {
			uRepo.addUpdate(user.getAddress(), user.getAddressX(), user.getAddressY(), user.getId());
			userPrincipal.getUser().setAddressAuth(0);
			return "redirect:/user/userProfile";
		} catch (Exception e) {
		}
		return "redirect:/user/userProfile";
	}

	@PostMapping("/authupdate")
	public String authUpdate(User user, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		try {
			uRepo.authUpdate(user.getId());
			userPrincipal.getUser().setAddressAuth(1);
			return "redirect:/user/userProfile";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/user/userProfile";
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
