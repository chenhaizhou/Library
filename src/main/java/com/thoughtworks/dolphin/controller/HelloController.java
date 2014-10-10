package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("list")
         public String list()
    {
        return "list";
    }

    @RequestMapping("login")
    public String login()
    {
        return "login";
    }

}
