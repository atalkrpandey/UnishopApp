package com.unishop.repository.user;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unishop.model.user.Orders;
import com.unishop.model.user.User;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
	
    @Query("select c.orders from User c where c.userID=?1")
    public List<Orders> getAllOrderByCid(Integer userID);
    
    public User findByMob(String mobile);

    
    
}
