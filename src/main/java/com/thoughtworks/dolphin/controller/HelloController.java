package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }

}
