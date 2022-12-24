package com.unishopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unishopapp.models.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {

}