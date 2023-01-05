package com.unishop.repository.user;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unishop.model.user.CurrentUserSession;

@Repository
public interface UserSessionDao extends JpaRepository<CurrentUserSession,Integer> {
    public CurrentUserSession findByUnqID(String unqID);
}
