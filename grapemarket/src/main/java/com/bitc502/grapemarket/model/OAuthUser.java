package com.bitc502.grapemarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAuthUser {
	private String resultcode;
	private String message;
	private response response;
	
	@Data
	public class response {
		private String id;
		private String nickname;
		private String profile_image;
		private String email;
	}
	
}
