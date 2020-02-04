package com.bitc502.grapemarket.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

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
import com.grum.geocalc.BoundingArea;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;

@Service
public class BoardService {

	@Autowired
	private BoardRepository bRepo;

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private SearchRepository sRepo;

	@Autowired
	private TradeStateRepository tradeStateRepo;

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private LikeRepository likeRepo;

	@Autowired
	private TradeStateService tradeStateServ;

	// 글쓰기 페이지
	public String writeForm(UserPrincipal userPrincipal, Model model) {

		Optional<User> oUser = uRepo.findById(userPrincipal.getUser().getId());
		User user = oUser.get();
		model.addAttribute("user", user);
		if (userPrincipal.getUser().getAddressAuth() == 0) {
			int authNeeded = 1;
			model.addAttribute("authNeeded", authNeeded);

			return "/user/userProfile";
		}

		return "/board/write2";

	}

	// 상세보기 페이지
	public Map<String, Object> detail(int id, Model model, UserPrincipal userPrincipal) {

		try {
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			Optional<Board> oBoard = bRepo.findById(id);
			Board board = oBoard.get();
			map.put("board", board);
			

			// 댓글 불러오기
			List<Comment> comments = commentRepo.findByBoardId(board.getId());
			map.put("comments", comments);

			// 좋아요 불러오기
			Likes check = likeRepo.findByUserIdAndBoardId(userPrincipal.getUser().getId(), board.getId());
			if (check != null) {
				map.put("liked", "liked");
			}
			int likeCount = likeRepo.countByBoardId(board.getId());
			map.put("likeCount", likeCount);

			// 구매완료 누른 사용자 불러오기
			List<TradeState> tradeStates = tradeStateRepo.findByBoardIdAndState(board.getId());
			map.put("tradeStates", tradeStates);

			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 글 삭제
	public String delete(int id) {

		try {
			bRepo.deleteById(id);
			return "redirect:/board/page?page=0&category=1&userInput=&range=5";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/detail/" + id;
	}

	// 글 수정
	public Board updateForm(int id, Model model) {
		Optional<Board> oBoard = bRepo.findById(id);
		Board board = oBoard.get();
		return board;
	}

	public void setBuyerId(User user, Board board) {

		Optional<Board> oBoard = bRepo.findById(board.getId());
		Board board2 = oBoard.get();

		board2.setBuyer(board.getBuyer());
		board2.setState("1");
		bRepo.save(board2);

		TradeState state = tradeStateRepo.findByUserIdAndBoardId(user.getId(), board.getId());
		if (state.getState().equals("판매중")) {
			state.setState("판매완료");
		}

		tradeStateRepo.save(state);

	}

	// 검색어 저장
	public void saveKeyword(String userInput) {
		if (!userInput.equals("")) {
			String[] searchContent = userInput.split(" ");
			for (String entity : searchContent) {
				Search search = new Search();
				System.out.println("저장중");
				entity.trim();
				if (!entity.equals("")) {
					search.setContent(entity);
					sRepo.save(search);
				}
			}
		}
	}

	// 리스트
	public Page<Board> getList(String userInput, String category, UserPrincipal userPrincipal, int range,
			Pageable pageable) {

		Coordinate lat = Coordinate.fromDegrees(userPrincipal.getUser().getAddressX());
		Coordinate lng = Coordinate.fromDegrees(userPrincipal.getUser().getAddressY());
		Point Mine = Point.at(lat, lng);

		BoundingArea area = EarthCalc.around(Mine, range * 1000);
		Point nw = area.northWest;
		Point se = area.southEast;
		Page<Board> boards;
		if (userInput.equals("")) {
			if (category.equals("1")) {// 입력값 공백 + 카테고리 전체 (그냥 전체 리스트)
				boards = bRepo.findAllAndGps(nw.latitude, se.latitude, nw.longitude, se.longitude, pageable);
			} else {// 입력값 공백이면 + 카테고리 (입력값조건 무시 카테고리만 걸고)
				boards = bRepo.findByCategoryAndGps(nw.latitude, se.latitude, nw.longitude, se.longitude, category,
						pageable);
			}
		} else {
			// 공백제거
			userInput = userInput.trim();
			// 정규식 형태 만들어주기
			userInput = userInput.replace(" ", ")(?=.*");
			userInput = "(?=.*" + userInput + ")";
			if (category.equals("1")) // 입력값 + 카테고리 전체 (입력값만 걸고 카테고리 조건 무시)
				category = "1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16";
			boards = bRepo.searchAndGps(nw.latitude, se.latitude, nw.longitude, se.longitude, category, userInput,
					pageable);
		}
		return boards;
	}

	public List<Board> getPopularBoard() {
		List<Board> boards = new ArrayList<Board>();
		try {
			boards = bRepo.popularBoard();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return boards;
	}

	// 카운트값 받아오기
	public long getCount(long totalElements) {
		long count = 0;
		if (totalElements % 8 == 0) {
			count = totalElements / 8;
		} else {
			count = (totalElements / 8) + 1;
		}
		return count;
	}


	// 글쓰기
	public String write2(UserPrincipal userPrincipal, Board board, List<MultipartFile> productImages,
			String fileRealPath) {

		try {
			// 파일 이름 세팅 및 쓰기

			List<String> imageFileNames = new ArrayList<String>();
			int index = 0;
			for (MultipartFile multipartFile : productImages) {
				imageFileNames.add(UUID.randomUUID() + "_" + multipartFile.getOriginalFilename());

				if (multipartFile.getSize() != 0) {
					Path filePath = Paths.get(fileRealPath + imageFileNames.get(index));
					Files.write(filePath, multipartFile.getBytes());

					if (index == 0) {
						board.setImage1(imageFileNames.get(index));
					} else if (index == 1) {
						board.setImage2(imageFileNames.get(index));
					} else if (index == 2) {
						board.setImage3(imageFileNames.get(index));
					} else if (index == 3) {
						board.setImage4(imageFileNames.get(index));
					} else if (index == 4) {
						board.setImage5(imageFileNames.get(index));
					}
				}
				index++;
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

	// 업데이트
	public String update(UserPrincipal userPrincipal, Board boardFromController, List<MultipartFile> productImages,
			List<String> currentImages, String fileRealPath) {

		try {
			// 파일 이름 세팅 및 쓰기

			Optional<Board> oBoard = bRepo.findById(boardFromController.getId());
			Board board = oBoard.get();

			List<String> imageFileNames = new ArrayList<String>();
			int index = 0;
			for (MultipartFile multipartFile : productImages) {
				imageFileNames.add(UUID.randomUUID() + "_" + multipartFile.getOriginalFilename());

				if (multipartFile.getSize() != 0) {
					Path filePath = Paths.get(fileRealPath + imageFileNames.get(index));
					Files.write(filePath, multipartFile.getBytes());

					if (index == 0) {
						board.setImage1(imageFileNames.get(index));
					} else if (index == 1) {
						board.setImage2(imageFileNames.get(index));
					} else if (index == 2) {
						board.setImage3(imageFileNames.get(index));
					} else if (index == 3) {
						board.setImage4(imageFileNames.get(index));
					} else if (index == 4) {
						board.setImage5(imageFileNames.get(index));
					}
				} else {
					if (index == 0) {
						board.setImage1(currentImages.get(index));
					} else if (index == 1) {
						board.setImage2(currentImages.get(index));
					} else if (index == 2) {
						board.setImage3(currentImages.get(index));
					} else if (index == 3) {
						board.setImage4(currentImages.get(index));
					} else if (index == 4) {
						board.setImage5(currentImages.get(index));
					}
				}
				index++;
			}

			bRepo.save(board);

			// 리스트 완성되면 바꿔야함
			return "redirect:/board/page?page=0&category=1&userInput=&range=5";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/writeForm";
	}

	// 거리값 계산후 출력될 보드데이터 불러오기
	public List<Board> getGps(UserPrincipal userPrincipal, List<Board> boardsContent, int range) {
		Coordinate lat = Coordinate.fromDegrees(userPrincipal.getUser().getAddressX());
		Coordinate lng = Coordinate.fromDegrees(userPrincipal.getUser().getAddressY());
		Point Mine = Point.at(lat, lng);

		BoundingArea area = EarthCalc.around(Mine, range * 1000);

		Point nw = area.northWest;
		Point se = area.southEast;

		List<Board> board2 = new ArrayList<Board>();
		for (int i = 0; i < boardsContent.size(); i++) {

			if (boardsContent.get(i).getUser().getAddressX() < nw.latitude
					&& boardsContent.get(i).getUser().getAddressX() > se.latitude
					&& boardsContent.get(i).getUser().getAddressY() > nw.longitude
					&& boardsContent.get(i).getUser().getAddressY() < se.longitude) {

				board2.add(boardsContent.get(i));

			}
		}

		return board2;
	}
}
