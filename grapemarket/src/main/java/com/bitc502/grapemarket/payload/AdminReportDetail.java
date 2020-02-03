package com.bitc502.grapemarket.payload;

import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.Report;

import lombok.Data;

@Data
public class AdminReportDetail {
	private Report report;
	private Object reportType;
	private Chat chat;
}
