package com.thoughtworks.dolphin.dao;

import com.thoughtworks.dolphin.model.UserEntity;

public interface UserDAO {

    UserEntity selectUserByName(UserEntity userEntity);

    int insertUserByUsername(UserEntity signUpData);
}



