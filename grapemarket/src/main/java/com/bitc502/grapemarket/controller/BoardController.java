package com.bitc502.grapemarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board")
public class BoardController {

	//전체 페이지
	@GetMapping("/")
	public String list() {
		
		return "/board/list";
	}
		
	//상세보기 페이지
	@GetMapping("/detail")
	public String detail() {
		
		return "/board/detail";
	}
	
	//글쓰기 페이지
	@GetMapping("/writeForm")
	public String writeForm() {
		
		return "/board/write";
	}
	
}
