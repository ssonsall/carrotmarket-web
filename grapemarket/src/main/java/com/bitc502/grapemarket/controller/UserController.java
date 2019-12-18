package com.bitc502.grapemarket.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.security.MyUserDetails;
import com.bitc502.grapemarket.util.Script;

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
	public String loginForm() {

		return "/user/login";
	}

	@GetMapping("/join")
	public String joinForm() {

		return "/user/join";
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
			
			String UUIDFileName = UUID.randomUUID()+"_"+userProfile.getOriginalFilename();
			user.setUserProfile(UUIDFileName);
			Path filePath = Paths.get(fileRealPath + UUIDFileName);
			Files.write(filePath, userProfile.getBytes());
			uRepo.save(user);
			return "/user/login";
		} catch (Exception e) {
			e.printStackTrace();
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
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
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
