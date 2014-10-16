package com.thoughtworks.dolphin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HelloController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("error")
    public String goToErrorPage(@RequestParam("message") String message, Model model) {
        model.addAttribute("message", message);
        return "error";
    }
}
