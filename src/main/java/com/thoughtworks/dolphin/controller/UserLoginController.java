package com.thoughtworks.dolphin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserLoginController {

    @RequestMapping("login")
    public boolean login(String username,String password)
    {
        return true;
    }

}
