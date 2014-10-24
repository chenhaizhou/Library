package com.thoughtworks.dolphin.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.dolphin.AbstractUnitTest;
import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.common.SearchResult;
import com.thoughtworks.dolphin.dao.BookDAO;
import com.thoughtworks.dolphin.dao.ImageDAO;
import com.thoughtworks.dolphin.dto.BookQuery;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.model.BorrowBook;
import com.thoughtworks.dolphin.util.CacheUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class BookServiceTest extends AbstractUnitTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookDAO bookMapper;

    @Mock
    private ImageDAO imageDAO;

    private CacheUtil cacheUtil;

    private Map<Integer, Book> books;

    private BookQuery query;

    @BeforeClass
    public static void before() throws Exception {
        File dir = new File(Constants.INDEX_DIRECTORY + File.separator + Book.class.getSimpleName());
        if (dir.exists()) {
            dir.delete();
        }
    }

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
        when(bookMapper.getAllBooks()).thenReturn(Lists.newArrayList(books.values()));
        when(bookMapper.addBook(books.get(1))).thenReturn(books.get(1).getId());
        when(bookMapper.getBorrowedBookListCount("zhoujie")).thenReturn(1);

        cacheUtil = mock(CacheUtil.class);
        when(cacheUtil.get("books")).thenReturn(books);
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
    public void shouldGetBooks() throws Exception {
        BookQuery bookQuery = new BookQuery();
        bookQuery.setPageNumber(1);
        SearchResult<Book> resultBooks= bookService.getBooks(bookQuery);
        assertEquals(3, resultBooks.getResultData().size());
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

    @Test
    public void shouldBorrowBook() throws Exception {

        final int bookId = 243;
        final String userName = "test_user";

        Book book = new Book();
        book.setId(bookId);
        book.setName(userName);
        book.setTotalNumber(100);
        book.setBorrowedNumber(10);
        when(bookMapper.getBookById(bookId)).thenReturn(book);
        bookService.borrowBook(bookId, userName);

        verify(bookMapper,times(1)).borrowBook(anyInt(), anyString());
    }

    @Test
    public void shouldGetBorrowedBookListCount(){
        when(bookMapper.getBorrowedBookListCount("jack")).thenReturn(1);
        assertEquals(1, bookService.getBorrowedBookListCount("jack"));
    }

    @Test
    public void shouldGetBorrowedBookList(){

        String username = "zhoujie";
        String pagenumber = "1";

        int fromIdx = 0;
        int len = 10;

        List<BorrowBook> expectedResult = Lists.newArrayList();
        BorrowBook borrowBook = prepareOneBorrowedBook(1, "ABC", "Thinking in Java", "11-234324", "one", "abc", new Date());
        expectedResult.add(borrowBook);

        when(bookMapper.getBorrowedBookList(username, fromIdx, len)).thenReturn(expectedResult);

        assertEquals(expectedResult, bookService.getBorrowedBookList(username, pagenumber));

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

    private BorrowBook prepareOneBorrowedBook(int bookId, String author, String name, String isbn, String publisher, String introduction, Date borrowDate) {
        BorrowBook book = new BorrowBook();
        book.setId(bookId);
        book.setAuthor(author);
        book.setName(name);
        book.setIsbn(isbn);
        book.setPublisher(publisher);
        book.setIntroduction(introduction);
        book.setCreatedTime(new Date());
        book.setBorrowDate(borrowDate);
        return book;
    }

    private BookQuery prepareQuery() {
        BookQuery query = new BookQuery();
        query.setKeyword("Catherine");
        query.setPageNumber(1);
        return query;
    }
}
