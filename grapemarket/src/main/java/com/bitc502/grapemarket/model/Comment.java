package com.bitc502.grapemarket.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity 
public class Comment {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스
	private String content; //내용
		
	// 쓴사람
//	private User user; //id, username
	@ManyToOne
	@JoinColumn(name="userId")
	@JsonIgnoreProperties({ "comment","board","like" })
	//comment를 통해서 user가 가진 정보를 필요로 하는 로직 X
	//comment를 작성한 유저 클릭시 그 유저가 like한 데이터, 그 유저가 작성한 글 보기 기능 구현시 "board", "like" 지워도 무관
	private User user;
	
//	private Board board; //id
	@ManyToOne
	@JoinColumn(name="boardId")
	@JsonIgnoreProperties({ "user","comment","like","tradeState","chat" })
	//comment는 board 의 자식개체 이기 때문에 board는 연관관계 매핑 이상의 정보는 필요 x
	private Board board; //id
	
	@CreationTimestamp
	private Timestamp createDate;
	@CreationTimestamp 
	private Timestamp updateDate;
}
