package com.bitc502.grapemarket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLocationSetting {
	private String address;
	private String addressX;
	private String addressY;
	private Integer addressAuth;
}
