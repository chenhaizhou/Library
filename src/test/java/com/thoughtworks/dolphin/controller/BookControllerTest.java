package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.service.BookService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddBook(){

        Book book = mock(Book.class);

        final int coverImageId = 100;
        final int insertReturnCode = 1;
        when(book.getCoverImageId()).thenReturn(coverImageId);
        when(bookService.insertBook(book)).thenReturn(insertReturnCode);

        String resultStr = bookController.addBook(book);

        JSONObject expectecResult = new JSONObject();
        expectecResult.put("resultCode", "success");
        expectecResult.put("coverImageId", "100");

        assertTrue(resultStr.equals(expectecResult.toString()));
    }
}
