package com.unishop.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Address {
	
	private int pinCode;
	private int streetNo;
	private String city;
	private String state;
	private String country;

}
