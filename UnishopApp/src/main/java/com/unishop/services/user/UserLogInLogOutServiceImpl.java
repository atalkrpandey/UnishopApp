package com.unishop.services.user;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unishop.exception.UserException;
import com.unishop.model.user.CurrentUserSession;
import com.unishop.model.user.User;
import com.unishop.model.user.UserSignInDTO;
import com.unishop.repository.user.UserDao;
import com.unishop.repository.user.UserSessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class UserLogInLogOutServiceImpl implements UserLogInLogOutService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserSessionDao userSessionDao;

    @Override
    public String logInUser(UserSignInDTO userSignInDTO) throws UserException {

        User existingUser = userDao.findByMob(userSignInDTO.getMob());
        if(existingUser == null){
            throw new UserException("Please enter valid credentials");
        }

        Optional<CurrentUserSession> validUserSession = userSessionDao.findById(existingUser.getUserID());
        if(validUserSession.isPresent()){
            throw new UserException("User already logged in with this mobile number !");
        }

        if(existingUser.getPassword().equals(userSignInDTO.getPassword())){
            String key = RandomString.make(6);
            CurrentUserSession currentUserSession = new CurrentUserSession(existingUser.getUserID(), key, LocalDateTime.now());
            userSessionDao.save(currentUserSession);
            return "Kindly note this UUID for security purpose : "+currentUserSession.getUnqID();
        }
        throw new UserException("Kindly enter correct password");
    }

    @Override
    public String logOutUser(String key) throws UserException {
        CurrentUserSession currentUserSession = userSessionDao.findByUnqID(key);
        if(currentUserSession == null){
            throw new UserException("Please login first !");
        }

        userSessionDao.delete(currentUserSession);

        return "Logged Out Successfully !";
    }
}
