package com.thoughtworks.dolphin.service;

import com.thoughtworks.dolphin.AbstractUnitTest;
import com.thoughtworks.dolphin.dao.BookDAO;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.model.Image;
import com.thoughtworks.dolphin.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class BookDetailServiceTest extends AbstractUnitTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookDAO bookMapper;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetBook(){

        int bookId = 47;

        Book expectedBook = new Book();
        expectedBook.setAuthor("name");
        expectedBook.setId(47);
        Image image = new Image();
        image.setImageUrl("http://localhost:8080/Library/upload/s2157335_1412991950788.jpg");
        expectedBook.setImage(image);
        expectedBook.setIsbn("12345678");
        expectedBook.setIntroduction("this is a book!");

        when(bookMapper.getBookById(bookId)).thenReturn(expectedBook);

        Book book = bookService.getBook(bookId);

        assertEquals(expectedBook.getAuthor(), book.getAuthor());
        assertEquals(expectedBook.getId(), book.getId());
        assertEquals(expectedBook.getCoverImageUrl(), book.getCoverImageUrl());
        assertEquals(expectedBook.getIsbn(), book.getIsbn());
        assertEquals(expectedBook.getIntroduction(), book.getIntroduction());
        assertEquals(expectedBook.getPublisher(), book.getPublisher());

    }

    @Test
    public void shouldUpdateBook(){

        Book book = new Book();
        book.setId(47);
        book.setAuthor("name");

        when(bookMapper.updateBook(book)).thenReturn(1);

        int resultCode = bookService.updateBook(book);

        assertTrue(resultCode == 1);
    }

}
