package com.unishop.enums;

public enum OrderStatus {

	PROCESSED("Processed"),
	RETURNED("Returned"),
	FAILED("Failed"),
	OUT_FOR_DELIVERY("OutForDevilery"),
	PENDING("Pending"),
	TO_BE_DISPATCHED("ToBeDespatched"),
	CANCELED("Canceled");
	
	
	private String val;

	OrderStatus(String string) {
		// TODO Auto-generated constructor stub
		this.val = string;
		
	}
	
	public String getVal() {
		return this.val;
	}
	
}
