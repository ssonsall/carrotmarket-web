package com.bitc502.grapemarket.model;

import java.sql.Timestamp;

import javax.persistence.Column;
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
public class Chat {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스
	
	//id, content, createDate
//	@OneToMany(mappedBy = "chat")
//	@JsonIgnoreProperties({ "user","chat" })
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	private List<Message> message;
	
	//누르는 사람
	//private User user; //id, username
	@ManyToOne
	@JoinColumn(name = "buyerId")
	private User buyerId;
	
	@Column(nullable=false, columnDefinition = "int default 1")
	private int buyerState;
	
	@ManyToOne
	@JoinColumn(name = "sellerId")
	@JsonIgnoreProperties({"like","comment","board"})
	private User sellerId;
	
	@Column(nullable=false, columnDefinition = "int default 1")
	private int sellerState;
	// id, title
	
	@ManyToOne
	@JoinColumn(name = "boardId")
	@JsonIgnoreProperties({"like","comment","user"})
	private Board board;
	
	@CreationTimestamp
	private Timestamp createDate;
	@CreationTimestamp 
	private Timestamp updateDate;
}
