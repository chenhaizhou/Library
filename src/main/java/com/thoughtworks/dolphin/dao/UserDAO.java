package com.thoughtworks.dolphin.dao;

import com.thoughtworks.dolphin.model.User;

import java.util.List;


public interface UserDAO {

	public int insertUser(User user);

    public List<User>  queryAllUser();

    public User queryUserById(int id);

    public List<User> queryUserByName(String nickName);

    public int deleteUserById(int id);

    public int updateUserById(int id);

}



