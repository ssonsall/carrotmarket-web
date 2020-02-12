package com.bitc502.grapemarket.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Search;
import com.bitc502.grapemarket.service.BoardService;
import com.bitc502.grapemarket.service.SearchService;

@Controller
public class HomeController {

	@Autowired
	private BoardService boardServ;

	@Autowired
	private SearchService searchServ;

	// 메인 페이지
	@GetMapping("/")
	public String Home(Model model, HttpServletRequest request) {
		List<Board> boards = boardServ.getPopularBoard();
		List<Search> searchs = searchServ.getPopularKeyWords();
		model.addAttribute("searchs", searchs);
		model.addAttribute("boards", boards);
		
		String path = System.getProperty("user.dir");
		System.out.println("Working Directory = " + path);
		return "index";
	}

}
