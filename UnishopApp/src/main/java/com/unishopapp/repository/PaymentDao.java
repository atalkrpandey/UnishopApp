package com.unishopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unishopapp.models.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {

}
