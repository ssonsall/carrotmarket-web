package com.bitc502.grapemarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bitc502.grapemarket.model.Search;
import com.bitc502.grapemarket.repository.SearchRepository;

@Controller
public class HomeController {

	@Autowired
	private SearchRepository sRepo;
	
	
	//메인 페이지
	@GetMapping("/")
	public String Home(Model model) {
		List <Search> searchs = sRepo.popularKeyword();
		model.addAttribute("searchs",searchs);
		return "index";
	}
	
}
