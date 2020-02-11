package com.bitc502.grapemarket.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Chat {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스
	
	//누르는 사람
	//private User user; //id, username
	@ManyToOne
	@JoinColumn(name = "buyerId")
	@JsonIgnoreProperties({"like","comment","board","tradeState"})
	private User buyerId;
	
	@Column(nullable=false, columnDefinition = "int default 1")
	private int buyerState;
	
	@ManyToOne
	@JoinColumn(name = "sellerId")
	@JsonIgnoreProperties({"like","comment","board","tradeState"})
	private User sellerId;
	
	@Column(nullable=false, columnDefinition = "int default 1")
	private int sellerState;
	// id, title
	
	@ManyToOne
	@JoinColumn(name = "boardId")
	@JsonIgnoreProperties({"like","comment","user"})
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Board board;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ChatId")
	@JsonIgnoreProperties({"chat","user"})
	//chat table 에서는 이미 대화중인 상대 데이터를 가지고 있으므로 user 데이터 불러올 필요 x
	private List<Message> messages;
	
	@CreationTimestamp
	private Timestamp createDate;
	@CreationTimestamp 
	private Timestamp updateDate;
}
