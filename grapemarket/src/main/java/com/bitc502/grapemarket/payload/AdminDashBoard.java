package com.bitc502.grapemarket.payload;

import java.util.List;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Report;
import com.bitc502.grapemarket.model.Search;
import com.bitc502.grapemarket.model.User;

import lombok.Data;

@Data
public class AdminDashBoard {
	private int currentVisitorCount;
	private List<User> users;
	private List<Board> boards;
	private List<Report> reports;
	private List<Search> searchs; 
}
