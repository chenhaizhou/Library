package com.thoughtworks.dolphin.fliter;

import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.model.UserEntity;
import com.thoughtworks.dolphin.model.UserView;
import com.thoughtworks.dolphin.util.CacheUtil;
import com.thoughtworks.dolphin.util.CookieUtil;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationFilter extends HandlerInterceptorAdapter {

//    private static Logger logger = Logger.getLogger(AuthorizationFilter.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String url = request.getRequestURL().toString();

        if (isNeedCheck(url)) {

            Cookie cookie = CookieUtil.fetchCookie(request, "sessionId");
            if (cookie == null) {
                processRequest(request, response, "/login");
                return false;
            }

            String sessionId = cookie.getValue();
            Element element = (Element)CacheUtil.get(sessionId);

            if (element == null) {
                UserView userView = (UserView)element.getObjectValue();
                processRequest(request, response, "/login");
                return false;
            } else {
                CookieUtil.saveCookie(response, "sessionId", sessionId, Constants.COOKIE_LOGIN_MAXAGE);
            }
        }

        return true;
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, String redirectUrl) throws IOException {
        if(isAjaxRequest(request)){
            response.setStatus(Constants.HTTP_STATUS_CODE_FOR_AJAX);
        } else {
            redirectUrl(request, response, redirectUrl);
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        return request.getHeader("x-requested-with") != null;
    }

    private void redirectUrl(HttpServletRequest request, HttpServletResponse response, String toLogin) throws IOException {
        response.sendRedirect(request.getContextPath() + toLogin);
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