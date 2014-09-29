package com.thoughtworks.dolphin.service;

import com.thoughtworks.dolphin.model.User;
import com.thoughtworks.dolphin.model.UserEntity;

import java.util.List;


public interface UserService {

	public int insertUser(User user);

    public List<User> queryAllUser();

    public User queryUserById(int id);

    public List<User> queryUserByName(String nickName);

    public int deleteUserById(int id);

    /*
    return value:
    1:user not exist;
    2:user password error;
    3:user login success.
     */
    public String login(UserEntity userEntity);

}


