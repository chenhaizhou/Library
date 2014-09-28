package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.model.User;
import com.thoughtworks.dolphin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/userController")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("input")
    public String input()
    {
        return "input";
    }  

    @RequestMapping("addUser")
    public String addUser(String nickname, int state)
    {
        User user = new User();
        user.setNickname(nickname);
        user.setState(state);
        int returnCode = userService.insertUser(user);
        String returnPage = "index";
        if(returnCode == 1){
            returnPage = "success";
        }
        return returnPage;
    }

    @RequestMapping("queryAllUser")
    public void queryAllUser(){
        List<User> allUserList = userService.queryAllUser();
        System.out.println("The total count of users:" + allUserList.size());
    }

    @RequestMapping("queryUserByName")
    public void queryUserByName(String nickname){
        List<User> userList = userService.queryUserByName(nickname);
        for(User user : userList){
            System.out.println("username :" + user.getNickname() + "; state:" + user.getState());
        }
    }

}
