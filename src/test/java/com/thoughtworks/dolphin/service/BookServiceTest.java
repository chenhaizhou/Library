package com.thoughtworks.dolphin.service;

import com.google.common.collect.Lists;
import com.thoughtworks.dolphin.AbstractUnitTest;
import com.thoughtworks.dolphin.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookServiceTest extends AbstractUnitTest {

    @Autowired
    private BookService bookService;

    private List<Book> prepareBookList;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void should_Exist_ISBN() throws Exception {

    }

}
