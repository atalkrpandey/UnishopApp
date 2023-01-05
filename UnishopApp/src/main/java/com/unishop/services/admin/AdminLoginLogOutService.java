package com.unishop.services.admin;

import com.unishop.exception.AdminException;
import com.unishop.model.admin.AdminSignInDTO;

public interface AdminLoginLogOutService {

    public String logInAdmin(AdminSignInDTO adminSignInDTO) throws AdminException;
    
    public String logOutAdmin(String key) throws AdminException;
    
}
