package com.thoughtworks.dolphin.model;

/**
 * Created by hdzhang on 9/28/14.
 */
public class UserEntity {

    String username;
    String password;

    public UserEntity(String username,String password){
        this.username = username;
        this.password = password;
    }
}
