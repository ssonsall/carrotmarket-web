package com.bitc502.grapemarket.payload;


import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Statistics {
	
	// 방문자 수
	private List<Statistic> visitorVolume;
	// 가입자 수
	private List<Statistic> memberVolume;
	// 거래량
	private List<Statistic> dealVolume;
	// 거래완료 량
	private List<Statistic> completedDealVolume;
	// 채팅
	private List<Statistic> chatVolume;
	// 신고
	private List<Statistic> reportVolume;
}
