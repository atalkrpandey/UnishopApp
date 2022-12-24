package com.unishopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unishopapp.models.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

}
