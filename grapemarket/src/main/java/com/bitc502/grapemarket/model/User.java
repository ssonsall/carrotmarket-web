package com.bitc502.grapemarket.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.bitc502.grapemarket.common.AuthProvider;
import com.bitc502.grapemarket.common.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data // lombok
@Entity // JPA -> ORM
@DynamicInsert
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스
	@Column(unique = true)
	private String username; // 사용자 아이디
	private String password; // 암호화된 패스워드
	private String name; // 사용자 이름
	private String email; // EMAIL
	private String phone; // 핸드폰 번호
	@ColumnDefault("'defaultProfile.png'")
	private String userProfile;
	private String address; // 주소1
	@ColumnDefault("0")
	private Double addressX;
	@ColumnDefault("0")
	private Double addressY;
	@ColumnDefault("0")
	private Integer addressAuth; // 주소1 인증

	@Enumerated(EnumType.STRING)
	private Role role;

	@Enumerated(EnumType.STRING)
	private AuthProvider provider;

	private String providerId;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "user" })
	// @OnDelete(action = OnDeleteAction.CASCADE)
	private List<Board> board;

	// 댓글
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "user", "board" })/* 내가 comment 한 게시물 보기 사용할거면 "board" 지워도 무관 */
	private List<Comment> comment;

	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({ "user", "board" })
	/* like 에서 보드 유저 정보 필요 x
	 * 내가 like 한 게시물 보기 사용할거면 "board" 지워도 무관 */
	private List<Likes> like; // 좋아요

	/*
	 * 유저가 접속중인 모든 채팅방 출력안할거면 필요 x
	 * 
	 * @OneToMany(mappedBy = "user")
	 * @JsonIgnoreProperties({ "user","board" })
	 * @OnDelete(action = OnDeleteAction.CASCADE) 
	 * private List<Chat> chat;
	 */

	@CreationTimestamp // null 값으로 생성시 자동으로 현재 시간이 설정
	private Timestamp createDate;
	@CreationTimestamp
	private Timestamp updateDate;
}