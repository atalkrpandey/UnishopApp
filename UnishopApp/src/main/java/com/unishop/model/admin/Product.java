package com.unishop.model.admin;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.unishop.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productID;
    @Size(min = 1, message = "Product name cannot be null")
    private String productName;
    private Integer productPrice;
    private String specification;
    private Integer rating;
    private String imgUrl;
    private int quantity = 1;
    @Enumerated(EnumType.STRING)
    private Category category;

   

}
