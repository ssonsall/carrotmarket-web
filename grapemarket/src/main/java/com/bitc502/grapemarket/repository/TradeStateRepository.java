package com.bitc502.grapemarket.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.TradeState;
import com.bitc502.grapemarket.model.User;

public interface TradeStateRepository extends JpaRepository<TradeState, Integer>{
	
	int countByUserAndBoard(User user, Board board);

	TradeState findByUserIdAndBoardId(int userId, int boardId);
	
	@Query(value = "SELECT * FROM tradeState WHERE boardId=?1 AND state in('구매중','구매완료')",nativeQuery = true)
	List<TradeState> findByBoardIdAndState(int id);
	
	List<TradeState> findByUserId(int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE TradeState ts set ts.State = ?1 WHERE ts.boardId = ?2 AND ts.userId = ?3", nativeQuery = true)
	void updateTradeState(String state, int boardId, int userId);

	

}
