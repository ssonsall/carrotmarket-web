package com.bitc502.grapemarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitc502.grapemarket.model.Category;
import com.bitc502.grapemarket.repository.CategoryRepository;

@Controller
@RequestMapping("board")
public class BoardController {

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
	public String writeForm() {
		return "/board/write";
	}

	@GetMapping("/category")
	public String category(Model model) {
		List<Category> categories = cRepo.findAll();
		model.addAttribute("categories", categories);
		return "/board/category";
	}

}
