package com.bitc502.grapemarket.model;

import lombok.Data;

@Data
public class OAuthToken {
	String access_token;
	String refresh_token;
	String token_type;
	String expires_in;
}
