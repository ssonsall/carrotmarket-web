package com.bitc502.grapemarket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Category;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.CategoryRepository;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.security.MyUserDetails;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	private UserRepository uRepo;

	@Autowired
	private BoardRepository bRepo;

	@Autowired
	private CategoryRepository cRepo;

	// 전체 페이지
	@GetMapping("/")
	public String list() {
		return "/board/list";
	}

	// 상세보기 페이지
	@GetMapping("/detail")
	public String detail() {
		return "/board/detail";
	}

	// 글쓰기 페이지
	@GetMapping("/writeForm")
	public String writeForm(@AuthenticationPrincipal MyUserDetails userDetail, Model model) {
		Optional<User> oUser = uRepo.findById(userDetail.getUser().getId());
		User user = oUser.get();
		model.addAttribute("user", user);
		return "/board/write";
	}
	
	@PostMapping("/writeProc")
	public String write(Board board) {
		try {
			System.out.println(board);
			bRepo.save(board);
//			리스트 완성되면 바꿔야함
			return "/board/category";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/board/writeForm";
	}

	@GetMapping("/category")
	public String category(Model model) {
		List<Category> categories = cRepo.findAll();
		model.addAttribute("categories", categories);
		return "/board/category";
	}

}
