package com.bitc502.grapemarket.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data //lombok
@Entity //JPA -> ORM
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스
	private String username; // 사용자 아이디
	private String password; // 암호화된 패스워드
	private String name; // 사용자 이름
	private String email; //EMAIL
	private String phone; //핸드폰 번호
	private String address;  //주소1
    @ColumnDefault("0")
	private long addressX;
    @ColumnDefault("0")
	private long addressY;
    @ColumnDefault("0")
	private Integer addressAuth; //주소1 인증

	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({ "user" })
	private List<Board> board;
	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({ "user","board" })
	private List<Chat> chat;
	
	
	@CreationTimestamp //null 값으로 생성시 자동으로 현재 시간이 설정
	private Timestamp createDate;
	@CreationTimestamp 
	private Timestamp updateDate;
}