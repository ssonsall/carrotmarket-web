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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bitc502.grapemarket.common.CategoryType;
import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Comment;
import com.bitc502.grapemarket.model.Likes;
import com.bitc502.grapemarket.model.Search;
import com.bitc502.grapemarket.model.TradeState;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.CommentRepository;
import com.bitc502.grapemarket.repository.LikeRepository;
import com.bitc502.grapemarket.repository.SearchRepository;
import com.bitc502.grapemarket.repository.TradeStateRepository;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.security.UserPrincipal;
import com.bitc502.grapemarket.service.BoardService;
import com.bitc502.grapemarket.service.TradeStateService;

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

	@Autowired
	private LikeRepository likeRepo;

	@Autowired
	private SearchRepository sRepo;

	@Autowired
	private TradeStateRepository tradeStateRepo;

	@Autowired
	private TradeStateService tradeStateServ;

	@Autowired
	private BoardService boardServ;

	// 전체 페이지
	@GetMapping({ "/", "" })
	public String list() {
		return "redirect:/board/page?page=0&category=1&userInput=";
	}

	@GetMapping("/page")
	public String getList(Model model,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 8) Pageable pageable,
			@RequestParam String category, @RequestParam String userInput,
			@AuthenticationPrincipal UserPrincipal userPrincipal) {

		String currentCategory = category;
		List<User> users = uRepo.findByAddressContaining(userInput);
		List<Integer> userIds = new ArrayList<>();
		for (User u : users) {
			userIds.add(u.getId());
		}
		Page<Board> boards;
		if (!userInput.equals("")) {
			String[] searchContent = userInput.split(" ");
			for (String entity : searchContent) {
				Search search = new Search();
				search.setContent(entity);
				sRepo.save(search);
			}

		}

		if (userInput.equals("")) {
			if (category.equals("1")) {// 입력값 공백 + 카테고리 전체 (그냥 전체 리스트)
				boards = bRepo.findAll(pageable);
			} else {// 입력값 공백이면 + 카테고리 (입력값조건 무시 카테고리만 걸고)
				boards = bRepo.findByCategory(category, pageable);
			}
		} else {
			// 공백제거
			userInput = userInput.trim();
			// 정규식 형태 만들어주기
			userInput = userInput.replace(" ", ")(?=.*");
			userInput = "(?=.*" + userInput + ")";
			if (category.equals("1")) // 입력값 + 카테고리 전체 (입력값만 걸고 카테고리 조건 무시)
				category = "1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16";
			boards = bRepo.search(category, userInput, pageable);
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
	public String writeForm(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
		Optional<User> oUser = uRepo.findById(userPrincipal.getUser().getId());
		User user = oUser.get();
		model.addAttribute("user", user);

		if (userPrincipal.getUser().getAddressAuth() == 0) {
			int authNeeded = 1;
			model.addAttribute("authNeeded", authNeeded);

			return "/user/userProfile";
		}

		return "/board/write";

	}

	@PostMapping("/writeProc")
	public String write(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam("state") String state,
			@RequestParam("category") String category, @RequestParam("title") String title,
			@RequestParam("price") String price, @RequestParam("content") String content,
			@RequestParam("productImage1") MultipartFile productImage1,
			@RequestParam("productImage2") MultipartFile productImage2,
			@RequestParam("productImage3") MultipartFile productImage3,
			@RequestParam("productImage4") MultipartFile productImage4,
			@RequestParam("productImage5") MultipartFile productImage5) {
		try {
			Board board = new Board();
			// 파일 이름 세팅 및 쓰기

			String imageFileName1 = UUID.randomUUID() + "_" + productImage1.getOriginalFilename();
			String imageFileName2 = UUID.randomUUID() + "_" + productImage2.getOriginalFilename();
			String imageFileName3 = UUID.randomUUID() + "_" + productImage3.getOriginalFilename();
			String imageFileName4 = UUID.randomUUID() + "_" + productImage4.getOriginalFilename();
			String imageFileName5 = UUID.randomUUID() + "_" + productImage5.getOriginalFilename();

			if (productImage1.getSize() != 0) {
				Path filePath = Paths.get(fileRealPath + imageFileName1);
				Files.write(filePath, productImage1.getBytes());
				board.setImage1(imageFileName1);
			}
			if (productImage2.getSize() != 0) {
				Path filePath = Paths.get(fileRealPath + imageFileName2);
				Files.write(filePath, productImage2.getBytes());
				board.setImage2(imageFileName2);
			}
			if (productImage3.getSize() != 0) {
				Path filePath = Paths.get(fileRealPath + imageFileName3);
				Files.write(filePath, productImage3.getBytes());
				board.setImage3(imageFileName3);
			}
			if (productImage4.getSize() != 0) {
				Path filePath = Paths.get(fileRealPath + imageFileName4);
				Files.write(filePath, productImage4.getBytes());
				board.setImage4(imageFileName4);
			}
			if (productImage5.getSize() != 0) {
				Path filePath = Paths.get(fileRealPath + imageFileName5);
				Files.write(filePath, productImage5.getBytes());
				board.setImage5(imageFileName5);
			}

			board.setUser(userPrincipal.getUser());
			board.setCategory(category);
			board.setState(state);
			board.setTitle(title);
			board.setPrice(price);
			board.setContent(content);

			bRepo.save(board);

			// 거래 상태 추가
			tradeStateServ.insertSellState(userPrincipal.getUser(), board);

			// 리스트 완성되면 바꿔야함
			return "redirect:/board/page?page=0&category=1&userInput=";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/writeForm";
	}

	// 상세보기 페이지
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable int id, Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		Optional<Board> oBoard = bRepo.findById(id);
		Board board = oBoard.get();

		List<Comment> comments = commentRepo.findByBoardId(board.getId());

		Likes check = likeRepo.findByUserIdAndBoardId(userPrincipal.getUser().getId(), board.getId());
		if (check != null) {
			model.addAttribute("liked", "liked");
		}

		int likeCount = likeRepo.countByBoardId(board.getId());
		String state = "구매완료";
		List<TradeState> tradeStates = tradeStateRepo.findByBoardIdAndState(board.getId(), state);

		model.addAttribute("tradeStates", tradeStates);
		model.addAttribute("likeCount", likeCount);
		model.addAttribute("comments", comments);
		model.addAttribute("board", board);

		return "/board/detail";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		try {
			bRepo.deleteById(id);
			return "redirect:/board/page?page=0&category=1&userInput=";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/detail/" + id;
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

	@PostMapping("/updateForm/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		// 이미지 파일때문에 생각 좀 해봐야 함.
		Optional<Board> oBoard = bRepo.findById(id);
		Board board = oBoard.get();
		model.addAttribute("board", board);
		return "board/updateForm";
		// return "board/updateForm";
	}

	@PostMapping("/update")
	public String update(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam("state") String state,
			@RequestParam("category") String category, @RequestParam("title") String title,
			@RequestParam("price") String price, @RequestParam("content") String content,
			@RequestParam("productImage1") MultipartFile productImage1,
			@RequestParam("productImage2") MultipartFile productImage2,
			@RequestParam("productImage3") MultipartFile productImage3,
			@RequestParam("productImage4") MultipartFile productImage4,
			@RequestParam("productImage5") MultipartFile productImage5,
			@RequestParam("currentImage1") String currentImage1, @RequestParam("currentImage2") String currentImage2,
			@RequestParam("currentImage3") String currentImage3, @RequestParam("currentImage4") String currentImage4,
			@RequestParam("currentImage5") String currentImage5, @RequestParam("id") int id) {
		try {
			Board board = new Board();
			// 파일 이름 세팅 및 쓰기

			String imageFileName1 = UUID.randomUUID() + "_" + productImage1.getOriginalFilename();
			String imageFileName2 = UUID.randomUUID() + "_" + productImage2.getOriginalFilename();
			String imageFileName3 = UUID.randomUUID() + "_" + productImage3.getOriginalFilename();
			String imageFileName4 = UUID.randomUUID() + "_" + productImage4.getOriginalFilename();
			String imageFileName5 = UUID.randomUUID() + "_" + productImage5.getOriginalFilename();

			if (productImage1.getSize() != 0) {
				Path filePath = Paths.get(fileRealPath + imageFileName1);
				Files.write(filePath, productImage1.getBytes());
				board.setImage1(imageFileName1);
			} else {
				board.setImage1(currentImage1);
			}

			if (productImage2.getSize() != 0) {
				Path filePath = Paths.get(fileRealPath + imageFileName2);
				Files.write(filePath, productImage2.getBytes());
				board.setImage2(imageFileName2);
			} else {
				board.setImage2(currentImage2);
			}

			if (productImage3.getSize() != 0) {
				Path filePath = Paths.get(fileRealPath + imageFileName3);
				Files.write(filePath, productImage3.getBytes());
				board.setImage3(imageFileName3);
			} else {
				board.setImage3(currentImage3);
			}

			if (productImage4.getSize() != 0) {
				Path filePath = Paths.get(fileRealPath + imageFileName4);
				Files.write(filePath, productImage4.getBytes());
				board.setImage4(imageFileName4);
			} else {
				board.setImage4(currentImage4);
			}

			if (productImage5.getSize() != 0) {
				Path filePath = Paths.get(fileRealPath + imageFileName5);
				Files.write(filePath, productImage5.getBytes());
				board.setImage5(imageFileName5);
			} else {
				board.setImage5(currentImage5);
			}

			board.setId(id);
			board.setUser(userPrincipal.getUser());
			board.setCategory(category);
			board.setState(state);
			board.setTitle(title);
			board.setPrice(price);
			board.setContent(content);

			bRepo.update(board.getState(), board.getCategory(), board.getTitle(), board.getPrice(), board.getContent(),
					board.getImage1(), board.getImage2(), board.getImage3(), board.getImage4(), board.getImage5(),
					board.getId());

			// 리스트 완성되면 바꿔야함
			return "redirect:/board/page?page=0&category=1&userInput=";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/writeForm";
	}

	@GetMapping("/category/{id}")
	public String searchByCategory(@PathVariable int id) {
		// 카테고리별 리스트 화면
		return null;
	}

	@PostMapping("/complete")
	public @ResponseBody String boardComplete(Board board) {

		boardServ.setBuyerId(board);

		return "";
	}

}
