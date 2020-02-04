package com.bitc502.grapemarket.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.security.UserPrincipal;
import com.bitc502.grapemarket.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Value("${file.path}")
	private String fileRealPath;

	@Autowired
	private BoardService boardServ;

	// 전체 페이지
	@GetMapping({ "/", "" })
	public String list() {
		return "redirect:/board/page?page=0&category=1&userInput=&range=5";
	}

	// 리스트 페이지
	@GetMapping("/page")
	public String getList(Model model,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 8) Pageable pageable,
			@RequestParam String category, @RequestParam String userInput, @RequestParam int range,
			@AuthenticationPrincipal UserPrincipal userPrincipal) {

		int currentRange = range;
		String currentCategory = category;
		String originUserInput = userInput.trim();

		// 검색어 저장
		boardServ.saveKeyword(userInput);

		// 검색어 있는지 확인하고 board 데이터 불러오기
		Page<Board> boards = boardServ.getList(userInput, category, userPrincipal, range, pageable);

		if (pageable.getPageNumber() >= boards.getTotalPages() && boards.getTotalPages() > 0) {
			return "redirect:/board/page?page=" + (boards.getTotalPages() - 1) + "&category=" + category + "&userInput="
					+ userInput + "&range=5";
		}

		// 카운트값 받아오기
		long count = boardServ.getCount(boards.getTotalElements());

		model.addAttribute("originUserInput", originUserInput);
		model.addAttribute("currentUserInput", userInput);
		model.addAttribute("currentCategory", currentCategory);
		model.addAttribute("currentRange", currentRange);
		model.addAttribute("currentPage", pageable.getPageNumber());
		model.addAttribute("count", count);
		model.addAttribute("boards", boards.getContent());

		return "board/list";

	}

	// 글쓰기 페이지
	@GetMapping("/writeForm")
	public String writeForm(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {

		return boardServ.writeForm(userPrincipal, model);
	}

	// 글쓰기 동작
	@PostMapping("/writeProcTest")
	public String write2(@AuthenticationPrincipal UserPrincipal userPrincipal, Board board,
			@RequestParam(value = "productImage", required = true) List<MultipartFile> productImages) {

		return boardServ.write2(userPrincipal, board, productImages, fileRealPath);
	}

	// 상세보기 페이지
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable int id, Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {

		Map<String, Object> map = boardServ.detail(id, model, userPrincipal);

		if (map.get("liked") != null) {
			model.addAttribute("liked", map.get("liked"));
		}

		model.addAttribute("tradeStates", map.get("tradeStates"));
		model.addAttribute("likeCount", map.get("likeCount"));
		model.addAttribute("comments", map.get("comments"));
		model.addAttribute("board", map.get("board"));

		return "/board/detail";

	}

	// 글 삭제
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		return boardServ.delete(id);
	}

	// 카테고리
	/*
	 * @GetMapping("/category") public String category(Model model) { CategoryType[]
	 * categories = CategoryType.values(); System.out.println(categories[0]);
	 * List<String> list = new ArrayList<>(15); for (CategoryType categoryType :
	 * categories) { list.add(categoryType.NAME); } System.out.println(list);
	 * model.addAttribute("categories", list); return "/board/category"; }
	 */

	// 글 수정
	@PostMapping("/updateForm/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardServ.updateForm(id, model));
		return "board/updateForm";
	}

	// 글수정 동작
	@PostMapping("/update")
	public String update(@AuthenticationPrincipal UserPrincipal userPrincipal, Board board,
			@RequestParam(value = "productImage", required = true) List<MultipartFile> productImages,
			@RequestParam(value = "currentImage", required = true) List<String> currentImages) {

		return boardServ.update(userPrincipal, board, productImages, currentImages, fileRealPath);
	}

	// 카테고리별 리스트
	@GetMapping("/category/{id}")
	public String searchByCategory(@PathVariable int id) {
		return null;
	}

	// complete
	@PostMapping("/complete")
	public @ResponseBody void boardComplete(@AuthenticationPrincipal UserPrincipal userPrincipal, Board board) {

		boardServ.setBuyerId(userPrincipal.getUser(), board);
	}

}
