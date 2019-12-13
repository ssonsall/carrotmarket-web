package com.bitc502.grapemarket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.util.Script;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private BoardRepository bRepo;

	@GetMapping("/userAll")
	public @ResponseBody List<User> userAll() {
		List<User> Users = uRepo.findAll();
		return Users;
	}

	@GetMapping("/category")
	public @ResponseBody User category() {
		Optional<User> oUser = uRepo.findById(2);
		User user = oUser.get();
		return user;
	}

	@GetMapping("/board")
	public @ResponseBody List<Board> board() {
		List<Board> boards = bRepo.findAll();
		return boards;
	}

	@GetMapping("/boarddelete/{id}")
	public @ResponseBody String boardDelete(@PathVariable int id) {
		try {
			bRepo.deleteById(id);
			return Script.href("/test/board");
		} catch (Exception e) {
		}
		return Script.back("Fail Delete");
	}

	@GetMapping("/userdelete/{id}")
	public @ResponseBody String userDelete(@PathVariable int id) {
		try {
			uRepo.deleteById(id);
			return Script.href("/test/userAll");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Script.back("Fail Delete");
	}

	@GetMapping("/pagetest/{page}")
	public @ResponseBody Page<Board> getAccounts(Model model,
			@PageableDefault(sort = { "id" }, direction = Direction.DESC, size = 8) Pageable pageable,
			@PathVariable(value = "page", name = "page") int page) {
		Page<Board> boards = bRepo.findAll(pageable);
		return boards;
	}

	@GetMapping("/pagetest")
	public @ResponseBody Page<Board> getAccountsj(Model model,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 8) Pageable pageable) {
		Page<Board> boards = bRepo.findAll(pageable);
		return boards;
	}
//	direction = Direction.DESC,

//	@GetMapping("/page")
//	public @ResponseBody Page<Board> getlist(Model model, @PageableDefault(sort = { "id" }, direction = Direction.DESC, size = 8) Pageable pageable) {
//		Page<Board> boards = bRepo.findAll(pageable);
//		model.addAttribute("boards", boards.getContent());
//		return boards;
//	}

	@GetMapping("/page")
	public String getlist(Model model,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 8) Pageable pageable) {
		Page<Board> boards = bRepo.findAll(pageable);
		if (pageable.getPageNumber() >= boards.getTotalPages()) {
			return "redirect:/test/page?page=" + (boards.getTotalPages() - 1);
		}
		int category = 4;
		int countRow = bRepo.countFindByCategory(category);
		int count = 0;
		if (countRow % 8 == 0) {
			count = countRow / 8;
		} else {
			count = (countRow / 8) + 1;
		}
		System.out.println("count >>" + count);
		model.addAttribute("count", count);
		model.addAttribute("boards", boards.getContent());
		return "/board/list";
	}

	@GetMapping("/pageres")
	public @ResponseBody Page<Board> getlistres(Model model,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 8) Pageable pageable) {
		Page<Board> boards = bRepo.findAll(pageable);
		if (pageable.getPageNumber() >= boards.getTotalPages()) {
			return null;
		}
		int category = 4;
		int countRow = bRepo.countFindByCategory(category);
		int count = 0;
		if (countRow % 8 == 0) {
			count = countRow / 8;
		} else {
			count = (countRow / 8) + 1;
		}
		
		System.out.println("count >>" + count);
		model.addAttribute("count", count);
		model.addAttribute("boards", boards.getContent());
		return boards;
	}
}
