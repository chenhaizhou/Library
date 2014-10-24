package com.thoughtworks.dolphin.controller;

import com.google.common.base.Strings;
import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.model.UserEntity;
import com.thoughtworks.dolphin.model.UserView;
import com.thoughtworks.dolphin.service.UserService;
import com.thoughtworks.dolphin.util.CacheUtil;
import com.thoughtworks.dolphin.util.CookieUtil;
import com.thoughtworks.dolphin.util.EncryptUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserLoginController {

    private static Logger LOGGER = Logger.getLogger(UserLoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
    public
    @ResponseBody
    String loginSubmit(HttpServletRequest req, HttpServletResponse resp, @RequestBody UserEntity loginData) {

        if (loginData != null && !Strings.isNullOrEmpty(loginData.getPassword())) {
            loginData.setPassword(EncryptUtil.encrypt(loginData.getPassword().trim()));
        }

        String result = userService.login(loginData);

        if (result.equals("UserLoginSuccess")) {
            String sessionId = req.getRequestedSessionId();
            CookieUtil.saveCookie(resp, Constants.COOKIE_SESSION_ID_KEY, sessionId, Constants.COOKIE_LOGIN_MAXAGE);
            CacheUtil.getInstance().put(sessionId, new UserView(loginData.getUsername()));
        }

        LOGGER.debug("Result:" + result);
        return "{\"result\":\"" + result + "\"}";
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public
    @ResponseBody
    String getUserInfo(HttpServletRequest request, HttpServletResponse response) {

        String userName = "";

        Cookie sessionCookie = CookieUtil.fetchCookie(request, Constants.COOKIE_SESSION_ID_KEY);
        if (sessionCookie != null) {
            String sessionId = sessionCookie.getValue();
            UserView userView = (UserView) CacheUtil.getInstance().get(sessionId);
            if (userView != null) {
                userName = userView.getUserName();
            }
        }
        return "{\"userName\":\"" + userName + "\"}";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public  void logout(HttpServletRequest request, HttpServletResponse response){
        Cookie sessionCookie = CookieUtil.fetchCookie(request, Constants.COOKIE_SESSION_ID_KEY);
        if (sessionCookie != null) {
            String sessionId = sessionCookie.getValue();
            CacheUtil.getInstance().remove(sessionId);
        }
    }

    @RequestMapping(value = "/signUpSubmit", method = RequestMethod.POST)
    @ResponseBody
    public String signUpSubmit(HttpServletRequest req, HttpServletResponse resp, @RequestBody UserEntity signUpData){

        String result = userService.signUp(signUpData);
        if(result.equals("SignUpSuccess")){
            String sessionId = req.getRequestedSessionId();
            CookieUtil.saveCookie(resp, Constants.COOKIE_SESSION_ID_KEY, sessionId, Constants.COOKIE_LOGIN_MAXAGE);
            CacheUtil.getInstance().put(sessionId, new UserView(signUpData.getUsername()));
        }
        return result;
    }

}