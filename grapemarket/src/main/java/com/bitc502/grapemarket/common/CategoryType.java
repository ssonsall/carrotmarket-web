package com.bitc502.grapemarket.common;

public enum CategoryType {
	POPULAR(1, "인기매물"), 
	DIGITAL(2, "디지털/가전"), 
	INTERIOR(3, "가구/인테리어"), 
	KID(4, "유아동/유아도서"), 
	LIFE(5, "생활/가공식품"),
	FDRESS(6, "여성의류"), 
	FSTUFF(7, "여성잡화"), 
	BEAUTY(8, "뷰티/미용"), 
	MFASION(9, "남성패션/잡화"), 
	SPORT(10, "스포츠/레저"),
	GAME(11, "게임/취미"), 
	TICKET(12, "도서/티켓/음반"), 
	PET(13, "반려동물용품"), 
	ETC(14, "기타 중고물품"), 
	BUY(15, "삽니다");

	public final int ID;
	public final String NAME;

	private CategoryType(int id, String name) {
		this.ID = id;
		this.NAME = name;
	}
	
	public String valueOf(int id) {
		for (CategoryType ct : CategoryType.values()) {
			if (ct.ID==id) {
				return ct.NAME;
			}
		}
		return "";
	}
}