package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by jiezhou on 9/28/14.
 */
public class BookControllerTest {

    private BookService bookService;

    @Before
    public void before(){

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"conf/spring.xml"
                ,"conf/spring-mybatis.xml"});
        bookService = (BookService) context.getBean("bookServiceImpl");
    }

    @Test
    public void addBook(){
        Book book = new Book();
        book.setName("TDD book");
        book.setIsbn("122343545454545");
        book.setPublisher("Test Publisher");
        book.setCover("/book/test.jpg");
        book.setAuthor("Test author");

        bookService.insertBook(book);
    }

}
