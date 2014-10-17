package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.model.UserEntity;
import com.thoughtworks.dolphin.service.UserService;
import com.thoughtworks.dolphin.util.CacheUtil;
import com.thoughtworks.dolphin.util.CookieUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CacheUtil.class})
public class UserLoginControllerTest {

    @InjectMocks
    private UserLoginController userLoginController;

    @Mock
    private UserService userService;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldLoginSubmitUsernameNotExist(){

        UserEntity userEntity = mock(UserEntity.class);

        String result = "{\"result\":\"UserNameError\"}";
        when(userService.login(userEntity)).thenReturn("UserNameError");

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse rep = mock(HttpServletResponse.class);

        String actualResult = userLoginController.loginSubmit(req, rep, userEntity);
        assertEquals(result, actualResult);

    }

    @Test
    public void shouldLoginSubmitPasswordError(){

        UserEntity userEntity = mock(UserEntity.class);

        String result = "{\"result\":\"UserLoginError\"}";
        when(userService.login(userEntity)).thenReturn("UserLoginError");

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse rep = mock(HttpServletResponse.class);

        String actualResult = userLoginController.loginSubmit(req, rep, userEntity);
        assertEquals(result, actualResult);

    }

    @Test
    public void shouldLoginSubmitLoginSuccess(){

        UserEntity userEntity = mock(UserEntity.class);

        String result = "{\"result\":\"UserLoginSuccess\"}";
        when(userService.login(userEntity)).thenReturn("UserLoginSuccess");

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse rep = mock(HttpServletResponse.class);

        mockStatic(CookieUtil.class);
        mockStatic(CacheUtil.class);

        CacheUtil instance = mock(CacheUtil.class);
        when(CacheUtil.getInstance()).thenReturn(instance);

        String actualResult = userLoginController.loginSubmit(req, rep, userEntity);
        assertEquals(result, actualResult);
    }


}
