package com.thoughtworks.dolphin.fliter;

import com.google.common.collect.Lists;
import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.model.UserView;
import com.thoughtworks.dolphin.util.CacheUtil;
import com.thoughtworks.dolphin.util.CookieUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AuthorizationFilter extends HandlerInterceptorAdapter {

    List<String> checkUrls;

    public AuthorizationFilter(){
        checkUrls = prepareCheckUrls();
    }

    private static Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String url = request.getRequestURL().toString();

        if (isNeedCheck(url)) {

            Cookie cookie = CookieUtil.fetchCookie(request, Constants.COOKIE_SESSION_ID_KEY);
            if (cookie == null) {
                LOGGER.info("User access URL:" + url + ", need to login...");
                processRequest(request, response, "/index.do");
                return false;
            }

            String sessionId = cookie.getValue();
            UserView userView = (UserView) CacheUtil.getInstance().get(sessionId);

            if (userView == null) {
                LOGGER.info("User access URL:" + url + ", need to login...");
                processRequest(request, response, "/index.do");
                return false;
            } else {
                CookieUtil.saveCookie(response, Constants.COOKIE_SESSION_ID_KEY, sessionId, Constants.COOKIE_LOGIN_MAXAGE);
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
        for (String checkUrl : checkUrls) {
            if (url.contains(checkUrl)) {
                return true;
            }
        }
        return false;
    }

    private List<String> prepareCheckUrls()
    {
        List<String> checkUrls = Lists.newArrayList();
        checkUrls.add("addBook");
        checkUrls.add("sample");
        checkUrls.add("delbook");
        checkUrls.add("editBook");
        checkUrls.add("borrowBook");
        return  checkUrls;
    }
}