package com.unishop.services.user;

import com.unishop.exception.UserException;
import com.unishop.model.user.UserSignInDTO;

public interface UserLogInLogOutService {
    public String logInUser(UserSignInDTO userSignInDTO) throws UserException;
    public String logOutUser(String key) throws UserException;
}
