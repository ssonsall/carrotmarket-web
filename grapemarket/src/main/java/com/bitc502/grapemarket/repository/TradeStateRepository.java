package com.bitc502.grapemarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.TradeState;
import com.bitc502.grapemarket.model.User;

public interface TradeStateRepository extends JpaRepository<TradeState, Integer>{
	
	int countByUserAndBoard(User user, Board board);

	TradeState findByUserIdAndBoardId(int userId, int boardId);

	
	@Query(value = "SELECT * FROM tradeState WHERE boardId=?1 AND state=?2",nativeQuery = true)
	List<TradeState> findByBoardIdAndState(int id, String State);
	

}
