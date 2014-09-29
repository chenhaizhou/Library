package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.model.UserEntity;
import com.thoughtworks.dolphin.service.UserService;
import com.thoughtworks.dolphin.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UserLoginController {

    private UserService userService;

    public  UserLoginController(){
        userService = new UserServiceImpl();
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
    @ResponseBody
    public String loginSubmit(@RequestBody UserEntity loginData) {

        System.out.println("------------login-----------");

        System.out.println(loginData.getUsername());
        System.out.println(loginData.getPassword());

        return "1";

    }

}
