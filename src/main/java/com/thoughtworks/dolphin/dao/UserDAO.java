package com.thoughtworks.dolphin.dao;

import com.thoughtworks.dolphin.model.UserEntity;

import java.util.List;


public interface UserDAO {

    public int isUserExist(UserEntity userEntity);

    public int isUserInfoRight(UserEntity userEntity);
}



