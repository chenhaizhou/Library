package com.thoughtworks.dolphin.model;

import java.io.Serializable;

/**
 * Created by ybhan on 10/8/14.
 */
public class UserView implements Serializable{

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserView(){

    }

    public UserView(String userName) {

        this.userName = userName;
    }
}
