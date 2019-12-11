package com.bitc502.grapemarket.model;

import java.awt.Image;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	private String address1;  //주소1
	private String address1Auth; //주소1 인증
	
//	private List<Board> board;
//	private List<Chat> chat;
	
	
	@CreationTimestamp //null 값으로 생성시 자동으로 현재 시간이 설정
	private Timestamp createDate;
	@CreationTimestamp 
	private Timestamp updateDate;
}