package com.unishop.repository.admin;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unishop.model.admin.Product;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {

}
