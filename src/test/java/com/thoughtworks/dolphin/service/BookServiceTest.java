package com.thoughtworks.dolphin.service;

import com.thoughtworks.dolphin.AbstractUnitTest;
import com.thoughtworks.dolphin.dto.BookSearchCondition;
import com.thoughtworks.dolphin.model.Book;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.*;

public class BookServiceTest extends AbstractUnitTest {

    @Autowired
    private BookService bookService;

    private static final Log LOGGER = LogFactory.getLog(BookServiceTest.class);


    @Ignore
    public void shouldGetAllDatas() {
        BookSearchCondition condition = new BookSearchCondition();
        condition.setKeyword("Asia");
        int count = bookService.getBookCount(condition);
        assertEquals(1, count);
    }

    @Ignore
    public void shouldGetBooks() {
        BookSearchCondition condition = new BookSearchCondition();
        condition.setKeyword("Asia");
        condition.setPageNumber(1);

        List<Book> books = bookService.getBooks(condition);
        assertTrue(books.size() > 0);

        Book firstBook = books.get(0);
        assertEquals(13, firstBook.getCoverImageId());


    }

    @Test
    public void shouldAddBook(){
        Book book = new Book();

        book.setName("TDD book");
        String isbn = "1122334455" + System.currentTimeMillis();
        book.setIsbn(isbn);
        book.setPublisher("Test Publisher");
        book.setCoverImageId(1);
        book.setCreatedTime(new Date(System.currentTimeMillis()));
        book.setAuthor("Test author");
        bookService.insertBook(book);

        assertTrue(bookService.isExist(book.getIsbn()));
    }

    @Ignore
    public void shouldCheckISBN(){
        Book book = new Book();
        book.setAuthor("Author-checkisbn");
        book.setIsbn("ISBN-checkisbn" + System.currentTimeMillis());
        book.setCoverImageId(1);
        book.setCreatedTime(new Date(System.currentTimeMillis()));
        book.setPublisher("Publisher-checkisbn");
        book.setName("Name-checkisbn");
        bookService.insertBook(book);

        assertTrue(bookService.isExist(book.getIsbn()));

        Book newBook = new Book();
        newBook.setIsbn(String.valueOf(System.currentTimeMillis()));
        assertFalse(bookService.isExist(newBook.getIsbn()));
    }

    @Ignore
    public void shouldDeleteBook(){
        Book book = new Book();

        book.setName("TDD book");
        String isbn = "1122334455";
        book.setIsbn(isbn);
        book.setPublisher("Test Publisher");
        book.setCoverImageId(33);
        book.setCreatedTime(new Date(System.currentTimeMillis()));
        book.setAuthor("Test author");
        bookService.insertBook(book);

        String path = "images/";
        bookService.deleteBook(isbn,path);
        assertFalse(bookService.isExist(isbn));

    }

}
