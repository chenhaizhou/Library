package com.thoughtworks.dolphin.service;

import com.thoughtworks.dolphin.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by lzwu on 9/29/14.
 */
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Before
    public void setup() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"conf/spring.xml", "conf/spring-mybatis.xml", "conf/mybatis-config.xml"});
        bookService = (BookService) context.getBean("bookService");
    }


    @Test
    public void shouldGetAllDatas() {
        int count = bookService.getAllBookCount();
        assertEquals(41, count);
    }

    @Test
    public void shouldGetBooks() {
        final int from = 5;
        final int len = 24;

        List<Book> books = bookService.getBooks(from, len);
        assertEquals(len, books.size());
    }
}
