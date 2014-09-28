package com.thoughtworks.dolphin.controller;


import com.thoughtworks.dolphin.model.User;
import com.thoughtworks.dolphin.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class UserControllerTest {

    private UserService userService;

    @Before
    public void before(){

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"conf/spring.xml"
                ,"conf/spring-mybatis.xml"});
        userService = (UserService) context.getBean("userServiceImpl");
    }

    @Test
    public void addUser(){
        User user = new User();
        String nickName = "testUser" + System.currentTimeMillis();
        int state = 20000;
        user.setNickname(nickName);
        user.setState(state);
        userService.insertUser(user);

        List<User> userList = userService.queryUserByName(nickName);
        assertTrue(userList.size()>0);
        assertTrue(userList.get(0).getState() == state);
    }

    @Test
    public  void queryAllUserTest(){
        User user = new User();
        String nickName = "LiuDeHua";
        user.setNickname(nickName);
        user.setState(10000);
        userService.insertUser(user);

        List<User> userList = userService.queryAllUser();
        assertTrue(userList.size()>0);

    }

    @Test
    public void queryUserByIdTest(){
        User user = new User();
        String nickName = "testUser" + System.currentTimeMillis();
        int state = 20000;
        user.setNickname(nickName);
        user.setState(state);
        userService.insertUser(user);

        List<User> userList = userService.queryUserByName(nickName);
        User firstUser = userService.queryUserById(userList.get(0).getId());
        assertTrue(firstUser != null);

    }

    @Test
    public void queryUserByNameTest(){
        User user = new User();
        String nickName = "LiuDeHua";
        user.setNickname(nickName);
        user.setState(10000);
        userService.insertUser(user);
        List<User> userList = userService.queryUserByName(nickName);
        assertTrue(userList.size()>0);
    }

    @Test
    public void deleteUserByIdTest(){
    }
}
