package com.unishop.enums;

public enum PaymentType {

	CASH_ON_DELIVERY("Cash"),
	UPI("Upi"),
	ATM("Atm"),
	WALLET("Wallet"),
	CREDITCARD("Creditcard");
	

	
	private String val;

	PaymentType(String string) {
		// TODO Auto-generated constructor stub
		this.val = string;
	}
	
	public String getVal() {
		
		return this.val;
	}
	
}
