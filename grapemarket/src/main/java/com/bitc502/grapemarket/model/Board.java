package com.bitc502.grapemarket.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스
	private String title; // 제목
	private String content; // 내용
	private String price; // 가격
	private String addressRange; // 범위
	private String state; // 상품 상태 (판매중, 판매완료)
	private String category; // 상품 카테고리

	// 댓글
	@OneToMany(mappedBy = "board")
	@JsonIgnoreProperties({ "board" })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Comment> comment;

	@OneToMany(mappedBy = "board")
	@JsonIgnoreProperties({ "board" })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Likes> like; // 좋아요
	
//	@OneToMany(mappedBy = "board")
//	@JsonIgnoreProperties({ "user","board" })
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	private List<Chat> chat;

	// id, username, address
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties({ "comment","board","like" })
	private User user;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "buyerId")
	@JsonIgnoreProperties({ "comment","board","like" })
	private User Buyer;

	// 상품 사진 시작
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	private String image5;

	// 상품 사진 끝

	@CreationTimestamp
	private Timestamp createDate;
	@CreationTimestamp
	private Timestamp updateDate;
}
