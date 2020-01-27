package com.bitc502.grapemarket.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bitc502.grapemarket.common.ReportType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스
	private String content; // 내용
	private String state; // 신고상태 (미해결: 0, 해결: 1)
	private ReportType reportType;
	private int reportId;
	//신고자
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties({ "board","comment","like","address","addressX","addressY" })
	private User user;
}
