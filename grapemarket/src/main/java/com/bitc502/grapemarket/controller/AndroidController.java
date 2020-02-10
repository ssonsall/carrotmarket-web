package com.bitc502.grapemarket.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bitc502.grapemarket.common.AuthProvider;
import com.bitc502.grapemarket.common.Role;
import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.Comment;
import com.bitc502.grapemarket.model.Likes;
import com.bitc502.grapemarket.model.Search;
import com.bitc502.grapemarket.model.TradeState;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.payload.ChatList;
import com.bitc502.grapemarket.payload.UserLocationSetting;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.ChatRepository;
import com.bitc502.grapemarket.repository.CommentRepository;
import com.bitc502.grapemarket.repository.LikeRepository;
import com.bitc502.grapemarket.repository.SearchRepository;
import com.bitc502.grapemarket.repository.TradeStateRepository;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.security.UserPrincipal;
import com.bitc502.grapemarket.service.BoardService;
import com.google.gson.Gson;
import com.grum.geocalc.BoundingArea;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;

@RequestMapping("/android")
@RestController
public class AndroidController {

	@Value("${file.path}")
	private String fileRealPath;

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private BoardRepository bRepo;

	@Autowired
	private CommentRepository cRepo;

	@Autowired
	private ChatRepository chatRepo;

	@Autowired
	private SearchRepository sRepo;

	@Autowired
	private LikeRepository lRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private TradeStateRepository tradeStateRepo;

	@Autowired
	private BoardService boardServ;

	@PostMapping("/join")
	public String join(@RequestParam("username") String username, @RequestParam("name") String name,
			@RequestParam("password") String password, @RequestParam("email") String email,
			@RequestParam("phone") String phone, @RequestParam("userProfile") MultipartFile userProfile) {

		try {
			String rawPassword = password;
			String encPassword = passwordEncoder.encode(rawPassword);
			User user = new User();
			user.setUsername(username);
			user.setName(name);
			user.setPassword(encPassword);
			user.setEmail(email);
			user.setPhone(phone);
			user.setProvider(AuthProvider.local);
			user.setRole(Role.valueOf("USER"));

			if (userProfile.getSize() != 0) {
				String UUIDFileName = UUID.randomUUID() + "_" + userProfile.getOriginalFilename();
				user.setUserProfile(UUIDFileName);
				Path filePath = Paths.get(fileRealPath + UUIDFileName);
				Files.write(filePath, userProfile.getBytes());
			}
			uRepo.save(user);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@PostMapping("/tradeComplete")
	public String updateTradeComplete(@AuthenticationPrincipal UserPrincipal userPrincipal,@RequestParam("boardId") String boardId,
			@RequestParam("buyerId") String buyerId) {
		try {
			bRepo.updateState("1", Integer.parseInt(buyerId) ,Integer.parseInt(boardId));
			tradeStateRepo.updateTradeState("판매완료", Integer.parseInt(boardId), userPrincipal.getUser().getId());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	@GetMapping("/tradeList")
	public List<TradeState> getTradeList(@AuthenticationPrincipal UserPrincipal userPrincipal){
		try {
			List<TradeState> tradeStates = tradeStateRepo.findByUserId(userPrincipal.getUser().getId());
			return tradeStates;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/getBuyerList")
	public List<TradeState> getBuyerList(@RequestParam("boardId") String boardId) {
		try {
			// 구매중, 구매완료 가져옴
			List<TradeState> tradeStateList = new ArrayList<>();
			tradeStateList = tradeStateRepo.findByBoardIdAndState(Integer.parseInt(boardId));
			System.out.println("State >> " + tradeStateList.get(0).getState());
			return tradeStateList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 안씀
	@GetMapping("/allList")
	public List<Board> allList() {
		System.out.println("올보드리스트");
		return bRepo.findAll();
	}

	// 주소 설정 안된 놈 접속하면 이 컨트롤러
	@GetMapping("/allListPageable")
	public List<Board> allListPageable(
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 8) Pageable pageable) {
		try {
			Page<Board> pBoard = bRepo.findAll(pageable);
			return pBoard.getContent();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("/allListPageableWithRange")
	public List<Board> allListPageableWithRange(
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 8) Pageable pageable,
			@RequestParam("range") String range, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		try {
			System.out.println("들어온 Range >> " + range);
			Integer rangeInt = Integer.parseInt(range);
			Coordinate lat = Coordinate.fromDegrees(userPrincipal.getUser().getAddressX());
			Coordinate lng = Coordinate.fromDegrees(userPrincipal.getUser().getAddressY());
			Point Mine = Point.at(lat, lng);

			BoundingArea area = EarthCalc.around(Mine, rangeInt * 1000);
			Point nw = area.northWest;
			Point se = area.southEast;

			Page<Board> pBoard = bRepo.findAllAndGps(nw.latitude, se.latitude, nw.longitude, se.longitude, pageable);

			return pBoard.getContent();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/saveLike")
	public Likes saveLike(@AuthenticationPrincipal UserPrincipal userPrincipal,
			@RequestParam("boardId") String boardId) {
		try {
			Likes likes = new Likes();
			if (lRepo.findByUserIdAndBoardId(userPrincipal.getUser().getId(), Integer.parseInt(boardId)) == null) {
				Optional<Board> oBoard = bRepo.findById(Integer.parseInt(boardId));
				likes.setBoard(oBoard.get());
				likes.setUser(userPrincipal.getUser());
				lRepo.save(likes);
			}
			return likes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/deleteLike")
	public String deleteLike(@AuthenticationPrincipal UserPrincipal userPrincipal,
			@RequestParam("likeId") String likeId) {
		try {
			lRepo.deleteById(Integer.parseInt(likeId));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@GetMapping("/detail/{id}")
	public Board detail(@PathVariable int id) {
		System.out.println("디테일보드");
		Optional<Board> oBoard = bRepo.findById(id);
		return oBoard.get();
	}

	@PostMapping("/loginSuccess")
	public String loginSuccess(HttpServletRequest request, HttpServletResponse response) {
		String test = (String) request.getAttribute("testSession");
		System.out.println(">> test >> " + test);
		return "ok";
	}

	@PostMapping("/getUserInfo")
	public User loginUserInfo(@RequestParam("username") String username) {
		User user = uRepo.findByUsername(username);
		return user;
	}

	@GetMapping("/loginFailure")
	public String loginFailure() {
		return "fail";
	}

	@PostMapping("/modifyBoard")
	public String modifyBoard(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam("state") String state,
			@RequestParam("category") String category, @RequestParam("title") String title,
			@RequestParam("price") String price, @RequestParam("content") String content,
			@RequestParam("productImage1") MultipartFile productImage1,
			@RequestParam("productImage2") MultipartFile productImage2,
			@RequestParam("productImage3") MultipartFile productImage3,
			@RequestParam("productImage4") MultipartFile productImage4,
			@RequestParam("productImage5") MultipartFile productImage5,
			@RequestParam("currentImage1") String currentImage1, @RequestParam("currentImage2") String currentImage2,
			@RequestParam("currentImage3") String currentImage3, @RequestParam("currentImage4") String currentImage4,
			@RequestParam("currentImage5") String currentImage5, @RequestParam("boardId") String boardId) {
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

			board.setId(Integer.parseInt(boardId));
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
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@PostMapping("/write")
	public String write(@RequestParam("state") String state, @AuthenticationPrincipal UserPrincipal userPrincipal,
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

			board.setUser(new User());
			board.getUser().setId(userPrincipal.getUser().getId());
			board.setCategory(category);
			board.setState(state);
			board.setTitle(title);
			board.setPrice(price);
			board.setContent(content);
			bRepo.save(board);
			return "success";
			// 리스트 완성되면 바꿔야함
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fail";
		}
	}

	// @RequestParam("content") String content, @RequestParam("user") String userId,
	// @RequestParam("board") String boardId
	@PostMapping("/commentWrite")
	public String commentWrite(Comment comment, @AuthenticationPrincipal UserPrincipal userPrincipal,
			@RequestParam("board") String board) {
		try {
			comment.setUser(new User());
			comment.getUser().setId(userPrincipal.getUser().getId());
			comment.setBoard(new Board());
			comment.getBoard().setId(Integer.parseInt(board));
			cRepo.save(comment);
			return "success";
		} catch (Exception e) {
			System.out.println(e.toString());
			return "fail";
		}
	}

	@GetMapping("/juso")
	public UserLocationSetting addressSetting(@RequestParam("address") String address,
			@RequestParam("addressX") String addressX, @RequestParam("addressY") String addressY,
			@AuthenticationPrincipal UserPrincipal userPrincipal) {
		return new UserLocationSetting(address, addressX, addressY, userPrincipal.getUser().getAddressAuth());
	}

	@PostMapping("/saveUserAddress")
	public String saveUserAddress(@RequestParam("address") String address, @RequestParam("addressX") String addressX,
			@RequestParam("addressY") String addressY, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		// uRepo.addUpdate(user.getAddress(), user.getAddressX(), user.getAddressY(),
		// user.getId());
		try {
			uRepo.addUpdate(address, Double.parseDouble(addressX), Double.parseDouble(addressY),
					userPrincipal.getUser().getId());
			userPrincipal.getUser().setAddress(address);
			userPrincipal.getUser().setAddressX(Double.parseDouble(addressX));
			userPrincipal.getUser().setAddressY(Double.parseDouble(addressY));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}

	@PostMapping("/requestChat")
	public Chat requestChat(@AuthenticationPrincipal UserPrincipal userPrincipal,
			@RequestParam("boardId") String boardId) {
		try {
			Chat chat = new Chat();
			Optional<User> oUser = uRepo.findById(userPrincipal.getUser().getId());
			Optional<Board> oBoard = bRepo.findById(Integer.parseInt(boardId));
			chat.setBuyerId(oUser.get());
			chat.setSellerId(oBoard.get().getUser());
			chat.setBoard(oBoard.get());

			// 이미 생성된 구매목록이 있는지 확인
			int checkTradeState = tradeStateRepo.countByUserAndBoard(chat.getBuyerId(), chat.getBoard());
			if (checkTradeState == 0) {

				TradeState tradeState = new TradeState();
				tradeState.setUser(chat.getBuyerId());
				tradeState.setBoard(chat.getBoard());
				tradeState.setState("구매중");

				tradeStateRepo.save(tradeState);
			}
			System.out.println("null check111");
			// 생성된 채팅방이 있는지 확인
			Chat checkChateState = chatRepo.findByBoardIdAndBuyerIdAndSellerId(chat.getBoard().getId(),
					chat.getBuyerId().getId(), chat.getSellerId().getId());
			System.out.println("null check222");
			// 채팅방이 있으면 활성화를 시키고 없으면 새로 생성
			if (checkChateState == null) {
				chat.setBuyerState(1);
				chatRepo.save(chat);
				return chat;
			} else {
				checkChateState.setBuyerState(1);
				chatRepo.save(checkChateState);
				return checkChateState;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("/chatList")
	public ChatList chatList(@AuthenticationPrincipal UserPrincipal userPrincipal) {
		List<Chat> chatForBuy = chatRepo.findByBuyerId(userPrincipal.getUser());
		List<Chat> chatForSell = chatRepo.findBySellerId(userPrincipal.getUser());
		ChatList chatList = new ChatList();
		chatList.setChatForBuy(chatForBuy);
		chatList.setChatForSell(chatForSell);
		System.out.println("Android ChatList 접근");
		return chatList;
	}

	@GetMapping("/searchWithRange")
	public List<Board> searchWithRange(
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 8) Pageable pageable,
			@RequestParam("category") String category, @RequestParam("userInput") String userInput,
			@RequestParam("range") String range, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		try {
			Integer rangeInt = Integer.parseInt(range);

			Coordinate lat = Coordinate.fromDegrees(userPrincipal.getUser().getAddressX());
			Coordinate lng = Coordinate.fromDegrees(userPrincipal.getUser().getAddressY());
			Point Mine = Point.at(lat, lng);

			BoundingArea area = EarthCalc.around(Mine, rangeInt * 1000);
			Point nw = area.northWest;
			Point se = area.southEast;

			// 검색어 저장
			boardServ.saveKeyword(userInput);

			// 검색어 있는지 확인하고 board 데이터 불러오기
			Page<Board> boards = boardServ.getList(userInput, category, userPrincipal, rangeInt, pageable);
			return boards.getContent();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 안씀
	@PostMapping("/search")
	public List<Board> search(@RequestParam("category") String category, @RequestParam("userInput") String userInput,
			@AuthenticationPrincipal UserPrincipal userPrincipal) {

		List<User> users = uRepo.findByAddressContaining(userInput);
		List<Integer> userIds = new ArrayList<>();
		for (User u : users) {
			userIds.add(u.getId());
		}
		List<Board> boards;
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
				boards = bRepo.findAll();
			} else {// 입력값 공백이면 + 카테고리 (입력값조건 무시 카테고리만 걸고)
				boards = bRepo.findByCategory(category);
			}
		} else {
			// 공백제거
			userInput = userInput.trim();
			// 정규식 형태 만들어주기
			userInput = userInput.replace(" ", ")(?=.*");
			userInput = "(?=.*" + userInput + ")";
			if (category.equals("1")) // 입력값 + 카테고리 전체 (입력값만 걸고 카테고리 조건 무시)
				category = "1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16";
			boards = bRepo.search(category, userInput);
		}

		return boards;
	}

	@GetMapping("/getSavedAddress")
	public UserLocationSetting getSavedAddress(@AuthenticationPrincipal UserPrincipal userPrincipal) {
		try {
			UserLocationSetting userLocationSetting = new UserLocationSetting(userPrincipal.getUser().getAddress(),
					userPrincipal.getUser().getAddressX().toString(), userPrincipal.getUser().getAddressY().toString(),
					userPrincipal.getUser().getAddressAuth());
			return userLocationSetting;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("/saveAddressAuth")
	public String saveAddressAuth(@AuthenticationPrincipal UserPrincipal userPrincipal) {
		try {
			uRepo.authUpdate(userPrincipal.getUser().getId());
			userPrincipal.getUser().setAddressAuth(1);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@GetMapping("/currentmyinfo")
	public User getCunnretMyInfo(@AuthenticationPrincipal UserPrincipal userPrincipal) {
		try {
			return uRepo.findById(userPrincipal.getUser().getId()).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/changepassword")
	public String changePassword(@AuthenticationPrincipal UserPrincipal userPrincipal,
			@RequestParam("newPassword") String newPassword) {
		try {
			uRepo.androidPasswordUpdate(passwordEncoder.encode(newPassword), userPrincipal.getUser().getId());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@PostMapping("/changeprofile")
	public String chageProfile(@RequestParam("user") String userJson) {
		User user = new Gson().fromJson(userJson, User.class);

		return null;
	}

	@GetMapping("/deleteBoard/{boardId}")
	public String deleteBoard(@PathVariable int boardId) {
		try {
			bRepo.deleteById(boardId);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@PostMapping("/completeTrade")
	public String completeTrade(@RequestParam("boardId") int boardId) {
		try {

//			Optional<Board> oBoard = bRepo.findById(board.getId());
//			Board board2 = oBoard.get();
//			
//			board2.setBuyer(board.getBuyer());
//			board2.setState("1");
//			bRepo.save(board2);

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
