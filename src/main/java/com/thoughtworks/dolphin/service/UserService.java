package com.thoughtworks.dolphin.service;

import com.thoughtworks.dolphin.model.UserEntity;

public interface UserService {

    /*
    return value:
    1:user not exist;
    2:user password error;
    3:user login success.
     */
    public String login(UserEntity userEntity);

}


