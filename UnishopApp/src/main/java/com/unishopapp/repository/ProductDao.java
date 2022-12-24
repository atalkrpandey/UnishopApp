package com.unishopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unishopapp.models.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

}
