package com.bitc502.grapemarket.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

	@Value("${file.path}")
	private String fileRealPath;

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private BoardRepository bRepo;

	@Autowired
	private CommentRepository commentRepo;

	// 전체 페이지
	@GetMapping({ "/", "" })
	public String list() {
		return "redirect:/board/page?page=0&category=1&userInput=";
	}

	@GetMapping("/page")
	public String getList(Model model,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 8) Pageable pageable,
			@RequestParam String category, @RequestParam String userInput) {
		String currentCategory = category;
		List<User> users = uRepo.findByAddressContaining(userInput);
		List<Integer> userIds = new ArrayList<>();
		for (User u : users) {
			userIds.add(u.getId());
		}
		Page<Board> boards;

		if (userInput.equals("")) {
			if (category.equals("1")) {// 입력값 공백 + 카테고리 전체 (그냥 전체 리스트)
				boards = bRepo.findAll(pageable);
			} else {// 입력값 공백이면 + 카테고리 (입력값조건 무시 카테고리만 걸고)
				category = "1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16";
				boards = bRepo.findByCategory(category, pageable);
			}
		} else {
			if (category.equals("1")) {// 입력값 + 카테고리 전체 (입력값만 걸고 카테고리 조건 무시)
				// 공백제거
				userInput = userInput.trim();
				// 정규식 형태 만들어주기
				userInput = userInput.replace(" ", ")(?=.*");
				userInput = "(?=.*" + userInput + ")";
				if (category.equals("1"))
					category = "1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16";
				boards = bRepo.search(category, userInput, pageable);
			} else {// 입력값 + 카테고리
				// 공백제거
				userInput = userInput.trim();
				// 정규식 형태 만들어주기
				userInput = userInput.replace(" ", ")(?=.*");
				userInput = "(?=.*" + userInput + ")";
				boards = bRepo.search(category, userInput, pageable);
			}
		}

		if (pageable.getPageNumber() >= boards.getTotalPages() && boards.getTotalPages() > 0) {
			return "redirect:/board/page?page=" + (boards.getTotalPages() - 1) + "&category=" + category + "&userInput="
					+ userInput;
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
		model.addAttribute("currentCategory", currentCategory);
		model.addAttribute("currentPage", pageable.getPageNumber());
		model.addAttribute("count", count);
		model.addAttribute("boards", boards.getContent());

		return "board/list";

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
	public String write(@AuthenticationPrincipal MyUserDetails userDetail, @RequestParam("state") String state,
			@RequestParam("category") int category, @RequestParam("title") String title,
			@RequestParam("price") String price, @RequestParam("content") String content,
			@RequestParam("productImages") List<MultipartFile> productImages) {
		try {
			Board board = new Board();
			// 파일 이름 세팅 및 쓰기
			List<String> uuidFileNames = new ArrayList<String>();

			for (int i = 0; i < productImages.size(); i++) {
				uuidFileNames.add(UUID.randomUUID() + "_" + productImages.get(i).getOriginalFilename());
				Path filePath = Paths.get(fileRealPath + uuidFileNames.get(i));
				Files.write(filePath, productImages.get(i).getBytes());
			}

			board.setUser(userDetail.getUser());
			board.setCategory(category);
			board.setState(state);
			board.setTitle(title);
			board.setPrice(price);
			board.setContent(content);
			if (productImages.size() >= 1) {
				board.setImage1(uuidFileNames.get(0));
				if (productImages.size() >= 2) {
					board.setImage2(uuidFileNames.get(1));
					if (productImages.size() >= 3) {
						board.setImage3(uuidFileNames.get(2));
						if (productImages.size() >= 4) {
							board.setImage4(uuidFileNames.get(3));
							if (productImages.size() >= 5) {
								board.setImage5(uuidFileNames.get(4));
							}
						}
					}
				}
			}
			bRepo.save(board);
//			리스트 완성되면 바꿔야함
			return "redirect:/board/page?page=0&category=1&userInput=";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "redirect:/board/writeForm";
	}

	// 상세보기 페이지
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable int id, Model model, @AuthenticationPrincipal MyUserDetails userDetail) {
		Optional<Board> oBoard = bRepo.findById(id);
		Board board = oBoard.get();

		List<Comment> comments = commentRepo.findByBoardId(board.getId());

		model.addAttribute("comments", comments);
		model.addAttribute("board", board);

		return "/board/detail";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		try {
			bRepo.deleteById(id);
			return "redirect:/board/page?page=0&category=1&userInput=";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/detail/"+id;
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
	
	@PostMapping("/update")
	public String update(Board board) {
		//이미지 파일때문에 생각 좀 해봐야 함.
		return "redirect:/board/";
		//return "board/writeForm";
	}

	@GetMapping("/category/{id}")
	public String searchByCategory(@PathVariable int id) {
		// 카테고리별 리스트 화면
		return null;
	}

}
