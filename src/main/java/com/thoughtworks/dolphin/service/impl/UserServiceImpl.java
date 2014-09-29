package com.thoughtworks.dolphin.service.impl;

import com.thoughtworks.dolphin.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thoughtworks.dolphin.dao.UserDAO;
import com.thoughtworks.dolphin.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;


    public String login(UserEntity userEntity){

        System.out.println("------------UserName: "+ userEntity.getUsername()+ "-----------");

        if(userDAO.isUserExist(userEntity) <1) return"1";

        if(userDAO.isUserInfoRight(userEntity)<1){
            return "2";
        }else{
            return "3";
        }
    }

}
