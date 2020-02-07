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
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.LastModifiedDate;

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
	private String state; // 상품 상태 (판매중, 판매완료)
	private String category; // 상품 카테고리

	// 댓글
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "board" })
	private List<Comment> comment;

	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "board" })
	private List<Likes> like; // 좋아요

	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "buyerId", "sellerId", "board" })
	private List<Chat> chat;

	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "user", "board" })
	private List<TradeState> tradeState;

	// id, username, address
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties({ "comment", "board", "like" })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "buyerId")
	@JsonIgnoreProperties({ "comment", "board", "like" })
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
