package com.unishop.repository.admin;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unishop.model.admin.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin,Integer> {
    public Admin findByMobileNumber(String mobile);
}
