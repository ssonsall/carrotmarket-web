package com.bitc502.grapemarket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.repository.BoardRepository;
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

	public void setBuyerId(Board board) {

			Optional<Board> oBoard = bRepo.findById(board.getId());
			Board board2 = oBoard.get();
			
			board2.setBuyer(board.getBuyer());
			board2.setState("1");
			bRepo.save(board2);

	}
	
	public List<Board> getGps(UserPrincipal userPrincipal,List<Board> boardsContent,int range) {
		Coordinate lat = Coordinate.fromDegrees(userPrincipal.getUser().getAddressX());
		Coordinate lng = Coordinate.fromDegrees(userPrincipal.getUser().getAddressY());
		Point Mine = Point.at(lat, lng);

		BoundingArea area = EarthCalc.around(Mine, range*1000);

		Point nw = area.northWest;
		Point se = area.southEast;
		
		List<Board> board2 = new ArrayList<Board>();
		for (int i = 0; i < boardsContent.size(); i++) {

			if (boardsContent.get(i).getUser().getAddressX() < nw.latitude
					&& boardsContent.get(i).getUser().getAddressX() > se.latitude
					&& boardsContent.get(i).getUser().getAddressY() > nw.longitude
					&& boardsContent.get(i).getUser().getAddressY() < se.longitude) {

				System.out.println(
						"title : " + boardsContent.get(i).getTitle() + "user : " + boardsContent.get(i).getUser().getUsername());
				
				board2.add(boardsContent.get(i));
				
			}
		}
		
		return board2;
	}

}
