package com.unishop.repository.admin;




import org.springframework.data.jpa.repository.JpaRepository;

import com.unishop.model.admin.CurrentAdminSession;


public interface AdminSessionDao extends JpaRepository<CurrentAdminSession ,Integer> {
    public CurrentAdminSession findByUuid(String uuid);
}
