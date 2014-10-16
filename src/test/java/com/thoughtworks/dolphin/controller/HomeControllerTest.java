package com.thoughtworks.dolphin.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by lzwu on 10/16/14.
 */
public class HomeControllerTest {

    private HomeController controller;

    @Before
    public void setUp() throws Exception {
        controller = new HomeController();

    }

    @Test
    public void shouldGoToIndex() throws Exception {
        assertEquals("index", controller.index());
    }

    @Test
    public void shouldGoToErrorPage() throws Exception {
        Model model = mock(Model.class);
        String message = "123";
        controller.goToErrorPage(message, model);
        verify(model, times(1)).addAttribute("message", message);
    }
}
