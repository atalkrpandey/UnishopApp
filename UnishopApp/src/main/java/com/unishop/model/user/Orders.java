package com.unishop.model.user;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unishop.enums.OrderStatus;
import com.unishop.model.admin.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    
    @Embedded
    private Payment payment;
    
    @Embedded
    private Address address;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    
    @JsonIgnore
    @Embedded
    @ElementCollection
    private List<Product> productList = new ArrayList<>();
}
