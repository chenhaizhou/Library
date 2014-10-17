package com.thoughtworks.dolphin.service;


import com.thoughtworks.dolphin.dao.UserDAO;
import com.thoughtworks.dolphin.model.UserEntity;
import com.thoughtworks.dolphin.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDAO userDAO;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldLoginSuccessfully(){

        UserEntity entity = new UserEntity();
        entity.setUsername("admin");
        entity.setPassword("admin");

        when(userDAO.selectUserByName(entity)).thenReturn(entity);

        String actualResult = userService.login(entity);

        assertEquals("UserLoginSuccess",actualResult);

    }

    @Test
    public void shouldLoginFail(){

        UserEntity entity = new UserEntity();
        entity.setUsername("admin");
        entity.setPassword("admin123");

        UserEntity realEntity = new UserEntity();
        realEntity.setUsername("admin");
        realEntity.setPassword("admin");

        when(userDAO.selectUserByName(entity)).thenReturn(realEntity);

        String actualResult = userService.login(entity);

        assertEquals("UserLoginError",actualResult);

    }

    @Test
    public void shouldLoginUserNotExists() {
        UserEntity entity = new UserEntity();
        entity.setUsername("xxxxxxxxxx");

        when(userDAO.selectUserByName(entity)).thenReturn(null);
        String actualResult = userService.login(entity);
        assertEquals("UserNameError",actualResult);
    }


}
