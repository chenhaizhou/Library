package com.thoughtworks.dolphin.model;

import java.io.Serializable;

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
    String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public boolean isBuildInAdmin() {
        return isBuildInAdmin;
    }

    public void setBuildInAdmin(boolean isBuildInAdmin) {
        this.isBuildInAdmin = isBuildInAdmin;
    }

    boolean isBuildInAdmin;

    public UserEntity(){

    }

    public UserEntity(String username,String password){
        this.username = username;
        this.password = password;
    }

    public UserEntity(String username, String password, boolean isBuildInAdmin) {
        this.username = username;
        this.password = password;
        this.isBuildInAdmin = isBuildInAdmin;
    }

    public UserEntity(String username, String name, String password, boolean isBuildInAdmin) {
        this.username = username;
        this.name = name;
        this.password = password;
        if (isBuildInAdmin) this.isBuildInAdmin = true;
        else this.isBuildInAdmin = false;
    }
}
