package com.bitc502.grapemarket.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.common.CategoryType;
import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Comment;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.CommentRepository;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.security.MyUserDetails;
import com.bitc502.grapemarket.util.Script;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private BoardRepository bRepo;

	@Autowired
	private CommentRepository commentRepo;
	
	// 전체 페이지
	@GetMapping({"/",""})
	public String list() {
		return "redirect:/board/page?page=0&category=1&userInput=";
	}


	@GetMapping("/page")
	public String getList(Model model,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 8) Pageable pageable,
			@RequestParam int category, @RequestParam String userInput) {

		List<User> users = uRepo.findByAddressContaining(userInput);
		List<Integer> userIds = new ArrayList<>();
		for (User u : users) {
			userIds.add(u.getId());
		}
		Page<Board> boards;

		if (userInput.equals("")) {
			if (category == 1) {// 입력값 공백 + 카테고리 전체 (그냥 전체 리스트)
				boards = bRepo.findAll(pageable);
			} else {// 입력값 공백이면 + 카테고리 (입력값조건 무시 카테고리만 걸고)
				boards = bRepo.findByCategory(category, pageable);
			}
		} else {
			if (category == 1) {// 입력값 + 카테고리 전체 (입력값만 걸고 카테고리 조건 무시)
				boards = bRepo.findByTitleContainingOrContentContainingOrUserIdIn(userInput, userInput, userIds,
						pageable);
			} else {// 입력값 + 카테고리
				boards = bRepo.findByCategoryOrTitleContainingOrContentContainingOrUserIdIn(category, userInput,
						userInput, userIds, pageable);
			}
		}


		if (pageable.getPageNumber() >= boards.getTotalPages()) {
			return "redirect:/board/page?page=" + (boards.getTotalPages() - 1);
		}
		long countRow = boards.getTotalElements();
		System.out.println("countRow >> " + countRow);
		long count = 0;
		if (countRow % 8 == 0) {
			count = countRow / 8;
		} else {
			count = (countRow / 8) + 1;
		}
		model.addAttribute("currentUserInput", userInput);
		model.addAttribute("currentCategory", category);
		model.addAttribute("currentPage", pageable.getPageNumber());
		model.addAttribute("count", count);
		model.addAttribute("boards", boards.getContent());
		return "/board/list";
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

	// 상세보기 페이지
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable int id, Model model, @AuthenticationPrincipal MyUserDetails userDetail) {
		Optional<Board> oBoard = bRepo.findById(id);
		Board board = oBoard.get();
		
		List<Comment> comments = commentRepo.findByBoardId(board.getId());
		
		model.addAttribute("comments",comments);
		model.addAttribute("board",board);
		
		
		return "/board/detail";
	}

	@GetMapping("/delete/{id}")
	public @ResponseBody String delete(@PathVariable int id) {
		try {
			bRepo.deleteById(id);
			return Script.href("/test/board");
		} catch (Exception e) {
		}
		return Script.back("Fail Delete");
	}

	@GetMapping("/category")
	public String category(Model model) {
		CategoryType[] categories = CategoryType.values();
		System.out.println(categories[0]);
		List<String> list = new ArrayList<>(15);
		for (CategoryType categoryType : categories) {
			list.add(categoryType.NAME);
		}
		System.out.println(list);
		model.addAttribute("categories", list);
		return "/board/category";
	}

	@GetMapping("/category/{id}")
	public String searchByCategory(@PathVariable int id) {
		// 카테고리별 리스트 화면
		return null;
	}

}
