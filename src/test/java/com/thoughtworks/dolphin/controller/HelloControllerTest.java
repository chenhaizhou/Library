package com.thoughtworks.dolphin.controller;


import com.thoughtworks.dolphin.model.User;
import com.thoughtworks.dolphin.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HelloControllerTest {

    private UserService userService;

    @Test
    public void shouldTest() {
        assertThat(1, is(1));
    }

    @Before
    public void before(){

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"conf/spring.xml"
                ,"conf/spring-mybatis.xml"});
        userService = (UserService) context.getBean("userServiceImpl");
    }

    @Test
    public void addUser(){
        User user = new User();
        user.setNickname("zj");
        user.setState(2);
        System.out.println(userService.insertUser(user));
    }
}
