package com.unishopapp.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	private String customerName;
	private String mobileNumber;
	private String email;
	
	@Embedded
	@ElementCollection
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "customer")
	private Cart cart;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();
}
