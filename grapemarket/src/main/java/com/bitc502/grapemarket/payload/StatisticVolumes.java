package com.bitc502.grapemarket.payload;

import lombok.Data;

@Data
public class StatisticVolumes {
	// 가입자 수
	private Long memberVolume;
	// 거래량
	private Long dealVolume;
	// 채팅
	private Long chatVolume;
	// 신고
	private Long reportVolume;
}
