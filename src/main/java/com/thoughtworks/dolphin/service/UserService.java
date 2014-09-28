package com.thoughtworks.dolphin.service;

import com.thoughtworks.dolphin.model.User;

import java.util.List;


public interface UserService {

	public int insertUser(User user);

    public List<User> queryAllUser();

    public User queryUserById(int id);

    public List<User> queryUserByName(String nickName);

    public int deleteUserById(int id);

}
