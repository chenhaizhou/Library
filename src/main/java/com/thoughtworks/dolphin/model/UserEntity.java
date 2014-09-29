package com.thoughtworks.dolphin.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created by hdzhang on 9/28/14.
 */
public class UserEntity implements Serializable{

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String username;
    String password;

    public UserEntity(){

    }

    public UserEntity(String username,String password){
        this.username = username;
        this.password = password;
    }
}
