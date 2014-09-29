package com.thoughtworks.dolphin.controller;


import com.thoughtworks.dolphin.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by hdzhang on 9/28/14.
 */
public class UserTest {

    private UserService userService;

    @Before
    public void before(){

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"conf/spring.xml"
                ,"conf/spring-mybatis.xml"});
        userService = (UserService) context.getBean("userServiceImpl");
    }

//    @Test
//    public void Should_Login_Success()
//    {
//        assertTrue(userService.login(new UserEntity("admin","admin")));
//    }
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
