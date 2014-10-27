package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.model.UserEntity;
import com.thoughtworks.dolphin.model.UserView;
import com.thoughtworks.dolphin.service.UserService;
import com.thoughtworks.dolphin.util.CacheUtil;
import com.thoughtworks.dolphin.util.CookieUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CacheUtil.class, CookieUtil.class, UserLoginController.class})
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

    @Test
    public void shouldGetUserInfoWithEmptyUser(){

        String userName = "";
        String expectedResult = "{\"userName\":\"" + userName + "\"}";

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse rep = mock(HttpServletResponse.class);

        String actualResult = userLoginController.getUserInfo(req, rep);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldGetUserInfo() {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse rep = mock(HttpServletResponse.class);

        mockStatic(CookieUtil.class);
        Cookie cookie = mock(Cookie.class);

        when(CookieUtil.fetchCookie(req, Constants.COOKIE_SESSION_ID_KEY)).thenReturn(cookie);

        mockStatic(CacheUtil.class);
        CacheUtil instance = mock(CacheUtil.class);


        UserView userView = new UserView();
        userView.setUserName("ZZ");
        when(CacheUtil.getInstance()).thenReturn(instance);
        when(instance.get(Mockito.anyString())).thenReturn(userView);

        String actualResult = userLoginController.getUserInfo(req, rep);

        String userName = "ZZ";
        String expectedResult = "{\"userName\":\"" + userName + "\"}";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void shouldUserSignUpSuccess(){

        UserEntity userEntity = mock(UserEntity.class);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse rep = mock(HttpServletResponse.class);

        when(userService.signUp(userEntity)).thenReturn("SignUpSuccess");

        mockStatic(CookieUtil.class);
        mockStatic(CacheUtil.class);

        CacheUtil instance = mock(CacheUtil.class);
        when(CacheUtil.getInstance()).thenReturn(instance);

        String expectedResult = "SignUpSuccess";

        String actualResult = userLoginController.signUpSubmit(req,rep,userEntity);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldUserSignUpError(){

        UserEntity userEntity = mock(UserEntity.class);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse rep = mock(HttpServletResponse.class);

        when(userService.signUp(userEntity)).thenReturn("SignUpError");

        String expectedResult = "SignUpError";

        String actualResult = userLoginController.signUpSubmit(req,rep,userEntity);

        assertEquals(expectedResult, actualResult);
    }


}
