package com.bitc502.grapemarket.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity 
public class Chat {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스
	
//	List<Message> message; //id, content, createDate
		
	
//	private User user; //id, username, address
//	private Board board; //id, title, price
	
	@CreationTimestamp
	private Timestamp createDate;
	@CreationTimestamp 
	private Timestamp updateDate;
}
