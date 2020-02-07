package com.bitc502.grapemarket.common;

public enum CategoryType {
	ALL(1,"전체"),
	POPULAR(2, "인기매물"), 
	DIGITAL(3, "디지털/가전"), 
	INTERIOR(4, "가구/인테리어"), 
	KID(5, "유아동/유아도서"), 
	LIFE(6, "생활/가공식품"),
	FDRESS(7, "여성의류"), 
	FSTUFF(8, "여성잡화"), 
	BEAUTY(9, "뷰티/미용"), 
	MFASION(10, "남성패션/잡화"), 
	SPORT(11, "스포츠/레저"),
	GAME(12, "게임/취미"), 
	TICKET(13, "도서/티켓/음반"), 
	PET(14, "반려동물용품"), 
	ETC(15, "기타 중고물품"), 
	BUY(16, "삽니다");

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