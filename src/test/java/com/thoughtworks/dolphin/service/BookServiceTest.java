package com.thoughtworks.dolphin.service;

import com.sun.glass.ui.Application;
import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.dto.BookSearchCondition;
import com.thoughtworks.dolphin.model.Book;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceTest {

    @Autowired
    private BookService bookService;

    private static ApplicationContext context;

    private static final Log LOGGER = LogFactory.getLog(BookServiceTest.class);

    @BeforeClass
    public static void setUpBeforeClass() {
        context = new ClassPathXmlApplicationContext(new String[]{"file:src/test/resources/conf/spring.xml", "file:src/test/resources/conf/spring-mybatis.xml"});
    }

    @Before
    public void setUp() throws Exception {
        bookService = (BookService) context.getBean("bookService");
    }

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

    @Ignore
    public void shouldAddBook(){
        Book book = new Book();

        book.setName("TDD book");
        String isbn = "1122334455";
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
        book.setIsbn("ISBN-checkisbn");
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

    @Test
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

    @AfterClass
    public static void destroyContext(){
        ((ClassPathXmlApplicationContext) context).close();
    }
}
