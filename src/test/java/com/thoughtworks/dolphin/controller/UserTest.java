package com.thoughtworks.dolphin.controller;


import com.thoughtworks.dolphin.AbstractUnitTest;
import com.thoughtworks.dolphin.model.UserEntity;
import com.thoughtworks.dolphin.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by hdzhang on 9/28/14.
 */
@Ignore
public class UserTest extends AbstractUnitTest {

    @Autowired
    private UserService userService;

    @Test
    public void Should_Login_Success() {
        userService.login(new UserEntity("admin", "admin"));
    }
//
//    @Test
//    public void Should_Login_Fail_Wrong_Both_Input()
//    {
//        assertFalse(userService.login(new UserEntity("admin1", "admin1")));
//    }
//
//    @Test
//    public void Should_Login_Fail_Wrong_Username()
//    {
//        assertFalse(userService.login(new UserEntity("admin1","admin")));
//    }
//
//    @Test
//    public void Should_Login_Fail_Wrong_Password()
//    {
//        assertFalse(userService.login(new UserEntity("admin","admin1")));
//    }
}
