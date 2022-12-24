package com.unishopapp.models;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Cat_Enum {

	MEN("Men"),
	OWMEN("women"),
	KIDS("kids");

	String value;
	Cat_Enum(String value) {
		this.value = value;
	
	}
	
	@JsonCreator
	public static Cat_Enum decode(final String code) {
		return Stream.of(Cat_Enum.values()).filter(targetEnum -> targetEnum.value.equals(code)).findFirst().orElse(null);
	}
	
	@JsonValue
	public String getCode() {
		return value;
	}
}
