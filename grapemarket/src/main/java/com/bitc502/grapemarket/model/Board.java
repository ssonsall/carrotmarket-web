package com.bitc502.grapemarket.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity 
public class Board {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스
	private String title; //제목
	private String content; //내용
	private String price; //가격
	private String range; //범위
	private String state; // 상품 상태 (판매중, 판매완료)
	private String category; // 상품 카테고리
//	private List<Comment> comment; //댓글
//	private List<Like> like; //좋아요
	
	
	//상품 사진 시작
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	private String image5;
	private String image6;
	private String image7;
	private String image8;
	private String image9;
	private String image10;
	//상품 사진 끝
	
//	private User user; //id, username, address
	
	
	@CreationTimestamp
	private Timestamp createDate;
	@CreationTimestamp 
	private Timestamp updateDate;
}
