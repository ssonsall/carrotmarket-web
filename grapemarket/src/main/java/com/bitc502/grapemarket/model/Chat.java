package com.bitc502.grapemarket.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	@OneToMany(mappedBy = "chat")
	@JsonIgnoreProperties({ "user","chat" })
	private List<Message> message;
	
	//id, username, address
	@ManyToOne
	@JoinColumn(name="userId")
	private User user; //id, username, address
	
//	private Board board; //id, title, price
	// id, title
	@ManyToOne
	@JoinColumn(name = "boardId")
	private Board board;
	
	@CreationTimestamp
	private Timestamp createDate;
	@CreationTimestamp 
	private Timestamp updateDate;
}
