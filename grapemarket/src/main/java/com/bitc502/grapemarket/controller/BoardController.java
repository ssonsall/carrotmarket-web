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

	// 리스트 페이지
	@GetMapping("/page")
	public String getList(Model model,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 8) Pageable pageable,
			@RequestParam String category, @RequestParam String userInput, @RequestParam int range,
			@AuthenticationPrincipal UserPrincipal userPrincipal) {

		String currentCategory = category;
		String originUserInput = userInput.trim();

		// 검색어 저장
		boardServ.saveKeyword(userInput);

		// 검색어 있는지 확인하고 board 데이터 불러오기
		Page<Board> boards = boardServ.getBoard(userInput, category, pageable);

		if (pageable.getPageNumber() >= boards.getTotalPages() && boards.getTotalPages() > 0) {
			return "redirect:/board/page?page=" + (boards.getTotalPages() - 1) + "&category=" + category + "&userInput="
					+ userInput;
		}

		// 카운트값 받아오기
		long count = boardServ.getCount(boards.getTotalElements());

		// 거리값 계산후 출력될 보드데이터 불러오기
		List<Board> board2 = boardServ.getGps(userPrincipal, boards.getContent(), range);

		model.addAttribute("originUserInput", originUserInput);
		model.addAttribute("currentUserInput", userInput);
		model.addAttribute("currentCategory", currentCategory);
		model.addAttribute("currentPage", pageable.getPageNumber());
		model.addAttribute("count", count);
		model.addAttribute("boards", board2);

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

	/*
	 * @PostMapping("/writeProcTest") public String write(@AuthenticationPrincipal
	 * UserPrincipal userPrincipal, Board board,
	 * 
	 * @RequestParam(value="productImage", required=true) List<MultipartFile>
	 * productImage) {
	 * 
	 * System.out.println("productImage : "+productImage.size());
	 * 
	 * 
	 * for (MultipartFile multipartFile : productImage) {
	 * System.out.println(multipartFile.getOriginalFilename()); }
	 * 
	 * 
	 * for (int i = 0; i < productImage.size(); i++) {
	 * 
	 * if(!productImage.get(i).getOriginalFilename().equals(""))
	 * System.out.println(i + ": "+ productImage.get(i)); }
	 * 
	 * return "redirect:/board/page?page=0&category=1&userInput=&range=5"; }
	 */

	// 글쓰기 동작
	@PostMapping("/writeProc")
	public String write(@AuthenticationPrincipal UserPrincipal userPrincipal, Board board,
			@RequestParam("productImage1") MultipartFile productImage1,
			@RequestParam("productImage2") MultipartFile productImage2,
			@RequestParam("productImage3") MultipartFile productImage3,
			@RequestParam("productImage4") MultipartFile productImage4,
			@RequestParam("productImage5") MultipartFile productImage5) {

		try {
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

			bRepo.save(board);

			// 거래 상태 추가
			tradeStateServ.insertSellState(userPrincipal.getUser(), board);

			// 리스트 완성되면 바꿔야함
			return "redirect:/board/page?page=0&category=1&userInput=&range=5";

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

		// 댓글 불러오기
		List<Comment> comments = commentRepo.findByBoardId(board.getId());

		// 좋아요 불러오기
		Likes check = likeRepo.findByUserIdAndBoardId(userPrincipal.getUser().getId(), board.getId());
		if (check != null) {
			model.addAttribute("liked", "liked");
		}
		int likeCount = likeRepo.countByBoardId(board.getId());

		// 구매완료 누른 사용자 불러오기
		List<TradeState> tradeStates = tradeStateRepo.findByBoardIdAndState(board.getId());

		model.addAttribute("tradeStates", tradeStates);
		model.addAttribute("likeCount", likeCount);
		model.addAttribute("comments", comments);
		model.addAttribute("board", board);

		return "/board/detail";

	}

	// 글 삭제
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		try {
			bRepo.deleteById(id);
			return "redirect:/board/page?page=0&category=1&userInput=&range=5";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/detail/" + id;
	}

	// 카테고리
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

	// 글 수정
	@PostMapping("/updateForm/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		// 이미지 파일때문에 생각 좀 해봐야 함.
		Optional<Board> oBoard = bRepo.findById(id);
		Board board = oBoard.get();
		model.addAttribute("board", board);
		return "board/updateForm";
	}

	// 글수정 동작
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
			return "redirect:/board/page?page=0&category=1&userInput=&range=5";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/writeForm";
	}

	// 카테고리별 리스트
	@GetMapping("/category/{id}")
	public String searchByCategory(@PathVariable int id) {
		return null;
	}

	// complete
	@PostMapping("/complete")
	public @ResponseBody void boardComplete(Board board) {

		boardServ.setBuyerId(board);
	}

}
