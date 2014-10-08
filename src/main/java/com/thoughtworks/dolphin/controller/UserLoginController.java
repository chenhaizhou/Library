package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.model.UserEntity;
import com.thoughtworks.dolphin.model.UserView;
import com.thoughtworks.dolphin.service.UserService;
import com.thoughtworks.dolphin.service.impl.UserServiceImpl;
import com.thoughtworks.dolphin.util.CacheUtil;
import com.thoughtworks.dolphin.util.CookieUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserService userService;


    public UserLoginController() {
        userService = new UserServiceImpl();
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
    public
    @ResponseBody
    String loginSubmit(HttpServletRequest req, HttpServletResponse resp, @RequestBody UserEntity loginData) {

        String result = userService.login(loginData);

        if (result.equals("UserLoginSuccess")) {
            String sessionId = req.getRequestedSessionId();
            CookieUtil.saveCookie(resp, "sessionId", sessionId, Constants.COOKIE_LOGIN_MAXAGE);
            CacheUtil.put(sessionId, new UserView(loginData.getUsername()));
        }

        System.out.println("Resutk:"+result);
        return "{\"result\":\"" + result + "\"}";
    }



}