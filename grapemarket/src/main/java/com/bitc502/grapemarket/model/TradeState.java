package com.bitc502.grapemarket.model;

import java.sql.Timestamp;

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
public class TradeState {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스

	
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties({ "like", "comment", "board" })
	private User user;

	//	|0:판매취소|1:판매중|2:판매 완료|10:구매취소|11:구매중|12:구매 완료|
	private String State;

	// id, title
	@ManyToOne
	@JoinColumn(name = "boardId")
	@JsonIgnoreProperties({ "like", "comment", "user" })
	private Board board;

	@CreationTimestamp
	private Timestamp createDate;
	@CreationTimestamp
	private Timestamp updateDate;
}
