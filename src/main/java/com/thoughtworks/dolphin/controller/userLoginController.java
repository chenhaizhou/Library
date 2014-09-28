package com.thoughtworks.dolphin.controller;

/**
 * Created by hdzhang on 9/28/14.
 */

import com.thoughtworks.dolphin.model.User;
import com.thoughtworks.dolphin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
