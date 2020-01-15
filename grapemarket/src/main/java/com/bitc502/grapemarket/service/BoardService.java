package com.bitc502.grapemarket.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.UserRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository bRepo;

	@Autowired
	private UserRepository uRepo;

	public void setBuyerId(Board board) {

			Optional<Board> oBoard = bRepo.findById(board.getId());
			Board board2 = oBoard.get();
			
			board2.setBuyer(board.getBuyer());
			board2.setState("1");
			bRepo.save(board2);

	}

}
