package com.thoughtworks.dolphin.service;

import com.thoughtworks.dolphin.model.UserEntity;

public interface UserService {

    public String login(UserEntity userEntity);

    String signUp(UserEntity signUpData);
}


