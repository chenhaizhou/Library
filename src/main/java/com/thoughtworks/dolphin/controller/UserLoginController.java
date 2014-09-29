package com.thoughtworks.dolphin.controller;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.model.UserEntity;
import com.thoughtworks.dolphin.service.UserService;
import com.thoughtworks.dolphin.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserService userService;

    public  UserLoginController(){
        userService = new UserServiceImpl();
    }

    @RequestMapping("login")
    public String login(){

        System.out.println("------------XXXX-----------");
        return "login";
    }

    @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
    public @ResponseBody String loginSubmit(@RequestBody UserEntity loginData) {

        System.out.println("------------login :; UserName:"+ loginData.getUsername() +"-----------");

        String result = userService.login(new UserEntity(loginData.getUsername(),loginData.getPassword()));

        return "{\"result\":"+ result+ "}";
    }


}