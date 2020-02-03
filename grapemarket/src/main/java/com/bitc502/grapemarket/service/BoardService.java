package com.bitc502.grapemarket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Search;
import com.bitc502.grapemarket.model.TradeState;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.BoardRepository;
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
	private TradeStateRepository tRepo;

	public void setBuyerId(User user, Board board) {

		Optional<Board> oBoard = bRepo.findById(board.getId());
		Board board2 = oBoard.get();

		board2.setBuyer(board.getBuyer());
		board2.setState("1");
		bRepo.save(board2);
		
		TradeState state = tRepo.findByUserIdAndBoardId(user.getId(),board.getId());
		if(state.getState().equals("판매중")) {
			state.setState("판매완료");
		}
		
		tRepo.save(state);
		

	}
	
	//검색어 저장
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

	
	//검색어 있는지 확인하고 board 데이터 불러오기
	public Page<Board> getBoard(String userInput, String category, double latitude, double latitude2,
			double longitude, double longitude2,Pageable pageable) {
		Page<Board> boards;
		if (userInput.equals("")) {
			if (category.equals("1")) {// 입력값 공백 + 카테고리 전체 (그냥 전체 리스트)
				boards = bRepo.findAllAndGps(latitude, latitude2, longitude, longitude2, pageable);
			} else {// 입력값 공백이면 + 카테고리 (입력값조건 무시 카테고리만 걸고)
				boards = bRepo.findByCategoryAndGps(latitude, latitude2, longitude, longitude2, category, pageable);
			}
		} else {
			// 공백제거
			userInput = userInput.trim();
			// 정규식 형태 만들어주기
			userInput = userInput.replace(" ", ")(?=.*");
			userInput = "(?=.*" + userInput + ")";
			if (category.equals("1")) // 입력값 + 카테고리 전체 (입력값만 걸고 카테고리 조건 무시)
				category = "1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16";
			boards = bRepo.searchAndGps(latitude, latitude2, longitude, longitude2,category, userInput, pageable);
		}
		return boards;
	}

	//카운트값 받아오기
	public long getCount(long totalElements) {
		long count = 0 ;
		if (totalElements % 8 == 0) {
			count = totalElements / 8;
		} else {
			count = (totalElements / 8) + 1;
		}
		return count;
	}

	//거리값 계산후 출력될 보드데이터 불러오기
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
