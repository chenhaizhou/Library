package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.AbstractUnitTest;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ybhan on 9/30/14.
 */
public class AuthorizationFilterTest extends AbstractUnitTest {

    HttpServletRequest request;
    HttpServletResponse response;

    @Before
    public void Init(){
       // request = Mockit
    }

    @Test
    public void Filter_Should_Work(){

    }

}
