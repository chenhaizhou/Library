package com.thoughtworks.dolphin.dao;

import com.thoughtworks.dolphin.model.UserEntity;

public interface UserDAO {

    public UserEntity selectUserByName(UserEntity userEntity);

    int insertUserByUsername(UserEntity signUpData);
}



