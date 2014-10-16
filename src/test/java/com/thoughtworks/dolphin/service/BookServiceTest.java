package com.thoughtworks.dolphin.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.dolphin.AbstractUnitTest;
import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.dao.BookDAO;
import com.thoughtworks.dolphin.dao.ImageDAO;
import com.thoughtworks.dolphin.dto.BookQuery;
import com.thoughtworks.dolphin.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class BookServiceTest extends AbstractUnitTest {

    @InjectMocks
    @Autowired
    private BookService bookService;

    @Mock
    private BookDAO bookMapper;

    @Mock
    private ImageDAO imageDAO;

    private Map<Integer, Book> books;

    private BookQuery query;
    private Map<String,Object> queryMap;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        books= prepareBooks();
        for(int bookId : books.keySet()){
            Book book = books.get(bookId);
            when(bookMapper.getBookById(bookId)).thenReturn(book);
            when(bookMapper.getBookByISBN(book.getIsbn())).thenReturn(book);
        }

        query = prepareQuery();
        queryMap = prepareQueryMap();
        when(bookMapper.getBookCount(query)).thenReturn(3);
        when(bookMapper.getBooks(queryMap)).thenReturn(Lists.newArrayList(books.values()));
        when(bookMapper.addBook(books.get(1))).thenReturn(books.get(1).getId());
    }

    @Test
    public void shouldGetBookById() throws Exception {
        final  int bookId =1;

        assertEquals(books.get(bookId).getIsbn(), bookService.getBook(bookId).getIsbn());
    }

    @Test
    public void shouldExistIsbn() throws Exception {

        String isbn = books.get(1).getIsbn();
        assertTrue(bookService.isExist(isbn));
    }

    @Test
    public void shouldGetBookCount() throws Exception {

        assertEquals(3, bookService.getBookCount(query));
    }

    @Test
    public void shouldGetBooks() throws Exception {
        List<Book> resultBooks= bookService.getBooks(query);
        assertEquals(3,resultBooks.size());
    }

    @Test
    public void shouldInsertBook() throws Exception {

        assertEquals(books.get(1).getId(),bookService.insertBook(books.get(1)));
    }

    @Test
    public void shouldUpdateBook() throws Exception {
        Book book = mock(Book.class);
        when(bookMapper.updateBook(book)).thenReturn(1);
        assertEquals(1, bookService.updateBook(book));
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        Book book = mock(Book.class);
        when(book.getCoverImageUrl()).thenReturn("/upload/abc.jpg");
        when(book.getIsbn()).thenReturn(books.get(1).getIsbn());
        bookService.deleteBook(book.getIsbn(), anyString());

        verify(bookMapper,times(1)).deleteBook(anyString());
        verify(imageDAO, times(1)).deleteImage(anyInt());
    }

    private Map<Integer,Book> prepareBooks(){
        Map<Integer,Book> books = Maps.newHashMap();
        Book book1 = prepareOneBook(1, "Catherine 1", "Thinking in Java", "001-001", "publisher 1", "xxx xxx");
        Book book2 = prepareOneBook(2, "Catherine 2", "Thinking in Ruby", "002-002", "publisher 2", "xxx xxx");
        Book book3 = prepareOneBook(3, "Catherine 3", "Thinking in Net", "003-003", "publisher 3", "xxx xxx");

        books.put(1,book1);
        books.put(2,book2);
        books.put(3,book3);
        return books;
    }

    private Book prepareOneBook(int bookId, String author, String name, String isbn, String publisher, String introduction) {
        Book book = new Book();
        book.setId(bookId);
        book.setAuthor(author);
        book.setName(name);
        book.setIsbn(isbn);
        book.setPublisher(publisher);
        book.setIntroduction(introduction);
        book.setCreatedTime(new Date());
        return book;
    }

    private BookQuery prepareQuery() {
        BookQuery query = new BookQuery();
        query.setKeyword("Catherine");
        query.setPageNumber(1);
        return query;
    }

    private Map<String,Object> prepareQueryMap(){
        Map<String,Object> queryMap = Maps.newHashMap();

        queryMap.put("keyword", "Catherine");
        queryMap.put("fromIdx", 0);
        queryMap.put("len", Constants.ITEM_COUNT_IN_EACH_PAGE);
        return  queryMap;
    }
}
