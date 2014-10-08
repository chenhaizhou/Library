package com.thoughtworks.dolphin.fliter;

import com.thoughtworks.dolphin.util.CacheUtil;
import net.sf.ehcache.Element;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ybhan on 9/30/14.
 */
public class AuthorizationFilter implements HandlerInterceptor {

    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }


    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {

    }


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String url=request.getRequestURL().toString();

        if(isNeedCheck(url)){

            Cookie cookie = fetchCookie(request, "sessionId");
            if (cookie == null) {
                redirectUrl(request, response, "login");
            }

            if(org!=null){
                return true;
            }else{
                redirectUrl(request, response, "login");
            }
            return false;
        }
        return true;


    }

    private void redirectUrl(HttpServletRequest request, HttpServletResponse response, String toLogin) throws IOException {
        response.sendRedirect(request.getContextPath() + toLogin);
    }

    private Cookie fetchCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key) && cookie.getPath().equals("/")) {
                return cookie;
            }
        }
        return null;
    }

    private boolean isNeedCheck(String url) {
        List<String> checkUrls = new ArrayList<String>();
        checkUrls.add("addBook");
        checkUrls.add("sample");
        for (String checkUrl : checkUrls) {
            if (url.contains(checkUrl)) {
                return true;
            }
        }
        return false;
    }

}