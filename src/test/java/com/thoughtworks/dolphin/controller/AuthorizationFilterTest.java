package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.AbstractUnitTest;
import com.thoughtworks.dolphin.fliter.AuthorizationFilter;
import com.thoughtworks.dolphin.model.UserView;
import com.thoughtworks.dolphin.util.CacheUtil;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class AuthorizationFilterTest extends AbstractUnitTest {

    private AuthorizationFilter filter;

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void init() {
        filter = new AuthorizationFilter();

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        doNothing().when(response).addCookie(any(Cookie.class));
        doNothing().when(response).setStatus(anyInt());
        try {
            doNothing().when(response).sendRedirect(anyString());
        } catch (IOException e) {

        }
    }

    @Test
    public void shouldRedirectPage() throws Exception {
        final String url = "/Library/addBook";
        when(request.getRequestURL()).thenReturn(new StringBuffer(url));
        when(request.getCookies()).thenReturn(null);
        when(request.getHeader("x-requested-with")).thenReturn(null);

        filter.preHandle(request, response, null);
        verify(response, times(1)).sendRedirect(anyString());
    }

    @Test
    public void shouldSendAjax() throws Exception {
        final String url = "/Library/addBook";
        when(request.getRequestURL()).thenReturn(new StringBuffer(url));
        when(request.getCookies()).thenReturn(null);
        when(request.getHeader("x-requested-with")).thenReturn("123");

        filter.preHandle(request, response, null);
        verify(response, times(1)).setStatus(anyInt());
    }

    @Test
    public void shouldRedirectToLogin() throws Exception {
        final String url = "/Library/addBook";
        when(request.getRequestURL()).thenReturn(new StringBuffer(url));
        when(request.getCookies()).thenReturn(new Cookie[]{new Cookie("sessionId", "00001")});
        when(request.getHeader("x-requested-with")).thenReturn(null);

        filter.preHandle(request, response, null);
        verify(response, times(1)).sendRedirect(anyString());
    }

    @Test
    public void shouldKeepLoginStatus() throws Exception {
        final String url = "/Library/addBook";
        when(request.getRequestURL()).thenReturn(new StringBuffer(url));
        when(request.getCookies()).thenReturn(new Cookie[]{new Cookie("sessionId", "00001")});
        when(request.getHeader("x-requested-with")).thenReturn(null);

        CacheUtil cacheUtil = mock(CacheUtil.class);
        when(cacheUtil.get("00001")).thenReturn(new UserView("abc"));

        filter.preHandle(request, response, null);
        verify(response, times(1)).sendRedirect(anyString());
    }

    @Test
    public void shouldNotFilterPageWhenNotLogin() throws Exception {
        final String url = "/Library/index";
        when(request.getRequestURL()).thenReturn(new StringBuffer(url));
        when(request.getCookies()).thenReturn(null);
        when(request.getHeader("x-requested-with")).thenReturn("123");

        assertTrue(filter.preHandle(request, response, null));
    }

    @Test
    public void shouldNotFilterPageAfterLogin() throws Exception {
        final String url = "/Library/index";
        when(request.getRequestURL()).thenReturn(new StringBuffer(url));
        when(request.getCookies()).thenReturn(new Cookie[]{new Cookie("sessionId", "00001")});
        when(request.getHeader("x-requested-with")).thenReturn(null);

        CacheUtil cacheUtil = mock(CacheUtil.class);
        when(cacheUtil.get("00001")).thenReturn(new UserView("abc"));

        assertTrue(filter.preHandle(request, response, null));
    }
}
