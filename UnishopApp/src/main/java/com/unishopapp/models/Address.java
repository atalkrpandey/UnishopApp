package com.unishopapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

	
	private String streetNo;
	private String buildingNo;
	private String city;
	private String state;
	private String country;
	private String pincode;
}
