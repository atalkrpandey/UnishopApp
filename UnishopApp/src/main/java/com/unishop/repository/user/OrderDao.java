package com.unishop.repository.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unishop.model.user.Orders;

@Repository
public interface OrderDao extends JpaRepository<Orders,Integer> {
	

}
