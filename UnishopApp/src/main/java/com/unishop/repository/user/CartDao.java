package com.unishop.repository.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unishop.model.user.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart,Integer> {
}
