package com.thoughtworks.dolphin.service.impl;

import com.thoughtworks.dolphin.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thoughtworks.dolphin.dao.UserDAO;
import com.thoughtworks.dolphin.model.User;
import com.thoughtworks.dolphin.service.UserService;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;

	public int insertUser(User user) {
        return userDAO.insertUser(user);
    }

    public List<User> queryAllUser() {
        return userDAO.queryAllUser();
    }

    public User queryUserById(int id) {
        return userDAO.queryUserById(id);
    }

    public List<User> queryUserByName(String nickName) {
        return userDAO.queryUserByName(nickName);
    }

    public int deleteUserById(int id) {
        return userDAO.deleteUserById(id);
    }


    public String login(UserEntity userEntity){

        if(userDAO.isUserExist(userEntity) <1) return"1";

        if(userDAO.isUserInfoRight(userEntity)<1){
            return "2";
        }else{
            return "3";
        }


    }

}
