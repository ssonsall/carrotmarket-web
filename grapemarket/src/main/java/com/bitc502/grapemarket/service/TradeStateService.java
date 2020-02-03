package com.bitc502.grapemarket.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.TradeState;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.TradeStateRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TradeStateService {

	@Autowired
	private TradeStateRepository tradeStateRepo;

	// 구매, 판매 상태 확인
	public String checkState(String json) {

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode;

		try {
			jsonNode = mapper.readTree(json);
			JsonNode nodeUserId = jsonNode.get("userId");
			JsonNode nodeBoardId = jsonNode.get("boardId");

			int userId = Integer.parseInt(nodeUserId.toString());
			int boardId = Integer.parseInt(nodeBoardId.toString());

			TradeState ts = tradeStateRepo.findByUserIdAndBoardId(userId, boardId);

			return ts.getState();

		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("ERROR : TradeStateRepository/checkState");

			return null;
		}

	}

	// 게시물 작성시 판매상태 생성
	public void insertSellState(User user, Board board) {

		TradeState tradeState = new TradeState();
		tradeState.setUser(user);
		tradeState.setBoard(board);
		tradeState.setState("판매중");

		tradeStateRepo.save(tradeState);
	}

	// 채팅으로 거래하기 입력시 구매상태 생성
	public void insertBuyState(User user, Board board) {

		int check = tradeStateRepo.countByUserAndBoard(user, board);
		if (check == 0) {

			TradeState tradeState = new TradeState();
			tradeState.setUser(user);
			tradeState.setBoard(board);
			tradeState.setState("구매중");

			tradeStateRepo.save(tradeState);
		}
	}

	// 구매,판매 완료 처리
	public void setStateComplete(String json) {

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = mapper.readTree(json);
			JsonNode nodeUserId = jsonNode.get("userId");
			JsonNode nodeBoardId = jsonNode.get("boardId");

			int userId = Integer.parseInt(nodeUserId.toString());
			int boardId = Integer.parseInt(nodeBoardId.toString());

			TradeState ts = tradeStateRepo.findByUserIdAndBoardId(userId, boardId);

			if (ts.getState().equals("구매중")) {
				ts.setState("구매완료");
			} 

			tradeStateRepo.save(ts);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<TradeState> getListByUserId(int userId) {

		List<TradeState> tradeStates = tradeStateRepo.findByUserId(userId);

		return tradeStates;

	}

}
