package com.thoughtworks.dolphin.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mybatis.xml", "/conf/spring.xml"})
public class CookieTest {

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp(){
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void shouldFetchCookie() throws Exception {
        Cookie cookie = new Cookie("cookie","test");
        cookie.setPath("/");
        when(request.getCookies()).thenReturn(new Cookie[]{cookie});

        assertEquals(cookie,CookieUtil.fetchCookie(request, "cookie"));
    }

    @Test
    public void shouldSaveCookie() throws Exception {

        CookieUtil.saveCookie(response,"cookie","value",1);
        verify(response,times(1)).addCookie(any(Cookie.class));
    }

    @Test
    public void shouldDeleteCookie() throws Exception {

        CookieUtil.removeCookie(response,"cookie");
        verify(response,times(1)).addCookie(any(Cookie.class));
    }
}
