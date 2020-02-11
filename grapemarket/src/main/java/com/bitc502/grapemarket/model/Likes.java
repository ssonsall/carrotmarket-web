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
public class Likes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스

	//누르는 사람
	//private User user; //id, username
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties({ "comment","board","like" })
	private User user;

	// id, title
	@ManyToOne
	@JoinColumn(name = "boardId")
	@JsonIgnoreProperties({ "user","comment","like","tradeState","chat" })
	private Board board;

	@CreationTimestamp
	private Timestamp createDate;
	@CreationTimestamp
	private Timestamp updateDate;

}
