package com.unishop.model.user;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.unishop.enums.PaymentType;

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

public class Payment {
	
	private int paymentId;
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	private int amount;
	private LocalDateTime dateAndTime;
	
	

}
