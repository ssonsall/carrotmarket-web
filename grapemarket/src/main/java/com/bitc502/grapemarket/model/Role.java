package com.bitc502.grapemarket.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	USER("ROLE_USER", "일반 사용자"),
	ADMIN("ROLE_ADMIN", "관리자"),
	CAUTION1("ROLE_CAUTION1", "경고1"),
	CAUTION2("ROLE_CAUTION2", "경고2"),
	BANNED("ROLE_BANNED", "정지");
	
	private final String key;
	private final String title;
}
