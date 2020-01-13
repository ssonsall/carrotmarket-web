package com.bitc502.grapemarket.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Search;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.SearchRepository;

@Controller
public class HomeController {

	@Autowired
	private SearchRepository sRepo;

	@Autowired
	private BoardRepository bRepo;
	
	//메인 페이지
	@GetMapping("/")
	public String Home(Model model, HttpServletRequest request) {
			List <Search> searchs = sRepo.popularKeyword();
			List <Board> boards = bRepo.popularBoard();
			model.addAttribute("searchs",searchs);
			model.addAttribute("boards",boards);
			return "index";
		
		
	}
	
}
