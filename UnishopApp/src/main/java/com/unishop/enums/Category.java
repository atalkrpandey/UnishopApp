package com.unishop.enums;

public enum Category {

	CLOTH("Cloth"),
	ELECTRATIC("Electranic"),
	GROCERY("Grocery"),
	FOOTWEAR("Footwear"),
	JEWELLERY("Jewellery");
	
	
	private String val;

	Category(String string) {
		// TODO Auto-generated constructor stub
		
		this.val = string;
	}
	
	public String getVal() {
		return this.val;
	}


}
