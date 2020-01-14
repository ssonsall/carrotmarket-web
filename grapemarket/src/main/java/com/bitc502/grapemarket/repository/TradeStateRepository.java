package com.bitc502.grapemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.TradeState;
import com.bitc502.grapemarket.model.User;

public interface TradeStateRepository extends JpaRepository<TradeState, Integer>{
	
	int countByUserAndBoard(User user, Board board);

	TradeState findByUserIdAndBoardId(int userId, int boardId);
	

}
