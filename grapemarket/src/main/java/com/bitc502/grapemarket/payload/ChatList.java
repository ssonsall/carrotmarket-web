package com.bitc502.grapemarket.payload;

import java.util.List;

import com.bitc502.grapemarket.model.Chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatList {
	List<Chat> chatForBuy;
	List<Chat> chatForSell;
}
