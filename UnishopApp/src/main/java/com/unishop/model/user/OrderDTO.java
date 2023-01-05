package com.unishop.model.user;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.unishop.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	    private Integer orderId;
	    private LocalDateTime date;
	    @Enumerated(EnumType.STRING)
	    private OrderStatus orderStatus;
	    
}
