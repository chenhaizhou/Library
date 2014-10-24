package com.thoughtworks.dolphin.service.impl;

import com.thoughtworks.dolphin.model.UserEntity;
import com.thoughtworks.dolphin.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thoughtworks.dolphin.dao.UserDAO;
import com.thoughtworks.dolphin.service.UserService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;

    public String login(UserEntity userEntity){

        UserEntity entity = userDAO.selectUserByName(userEntity);

        if(entity ==null) return "UserNameError";
        if(entity.getPassword().equals(userEntity.getPassword()))
        {
            return "UserLoginSuccess";
        }
        return "UserLoginError";
    }

    public String signUp(UserEntity signUpData) {

        signUpData.setPassword(EncryptUtil.encrypt(signUpData.getPassword().trim()));
        try {
            userDAO.insertUserByUsername(signUpData);
            return "SignUpSuccess";
        } catch (Exception e) {
            return "SignUpError";
        }
    }

}
