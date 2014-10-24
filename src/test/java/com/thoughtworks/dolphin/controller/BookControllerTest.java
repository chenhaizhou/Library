package com.thoughtworks.dolphin.controller;

import com.google.common.collect.Lists;
import com.thoughtworks.dolphin.AbstractUnitTest;
import com.thoughtworks.dolphin.dto.BookQuery;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.model.BorrowBook;
import com.thoughtworks.dolphin.model.Image;
import com.thoughtworks.dolphin.service.BookService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.ui.ModelMap;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookControllerTest extends AbstractUnitTest {

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





        assertTrue(resultStr.equals(expectecResult.toString()));
    }

    @Test
    public void shouldIsExist(){

        String isbn = "aslfjasldfkasldfj";
        when(bookService.isExist(isbn)).thenReturn(false);

        boolean resultStr = bookController.checkISBN(isbn, null);
        assertTrue(resultStr == true);
    }

    @Test
    public void shouldListBooks() throws Exception{

        BookQuery query = mock(BookQuery.class);
        PowerMockito.whenNew(BookQuery.class).withAnyArguments().thenReturn(query);

        List<Book> expectBookList = new ArrayList<Book>();
        Book book = new Book();
        book.setIsbn("3241234234");
        book.setName("bookname_test");
        expectBookList.add(book);

        when(bookService.getBooks(query)).thenReturn(expectBookList);

        List<Book> actualBookList = bookController.listBooks(1, "name");
        actualBookList.add(book);
        assertTrue(actualBookList.size() == expectBookList.size());
        assertTrue(actualBookList.equals(expectBookList));
    }

    @Test
    public void shouldGetBookDetail(){

        int bookId = 47;
        Book expectedBook = new Book();
        expectedBook.setAuthor("name");
        expectedBook.setId(47);

        Image image = new Image();
        image.setImageUrl("http://localhost:8080/Library/upload/s2157335_1412991950788.jpg");

        expectedBook.setImage(image);
        expectedBook.setIsbn("12345678");
        expectedBook.setIntroduction("this is a book!");

        when(bookService.getBook(bookId)).thenReturn(expectedBook);

        ModelMap map = new ModelMap();
        bookController.bookDetail(String.valueOf(bookId), map);
        Book book = (Book)map.get("book");

        assertEquals(expectedBook.getAuthor(), book.getAuthor());
        assertEquals(expectedBook.getId(), book.getId());
        assertEquals(expectedBook.getCoverImageUrl(), book.getCoverImageUrl());
        assertEquals(expectedBook.getIsbn(), book.getIsbn());
        assertEquals(expectedBook.getIntroduction(), book.getIntroduction());
        assertEquals(expectedBook.getPublisher(), book.getPublisher());

    }

    @Test
    public void shouldEditBook(){

        Book book = new Book();
        book.setId(47);
        book.setAuthor("name");

        final int updateReturnCode = 1;
        when(bookService.updateBook(book)).thenReturn(updateReturnCode);

        String resultStr = bookController.editBook(book);

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("resultCode","success");

        assertTrue(resultStr.equals(expectedResult.toString()));
    }

    @Test
    public void shouldBorrowBook() throws Exception {

        final String bookId = "243";
        final String userName = "test_user";

        when(bookService.borrowBook(anyInt(), anyString())).thenReturn("success");

        Map<String, String> map = new HashMap<String, String>();
        map.put("bookId", bookId);
        map.put("userName", userName);


        JSONObject expectedResult = new JSONObject();
        expectedResult.put("resultCode","success");

        String actualResult = bookController.borrowBook(map);

        assertTrue(actualResult.equals(expectedResult.toString()));
    }

    @Test
    public void shouldBorrowedBookListCountWhenBorrowed(){

        String username = "zhoujie";
        String status = "borrowed";

        when(bookService.getBorrowedBookListCount(username, status)).thenReturn(1);
        assertEquals(1, bookController.borrowedBookListCount(username, status));
    }

    @Test
    public void shouldBorrowedBookListCountWhenReturned(){

        String username = "zhoujie";
        String status = "returned";

        when(bookService.getBorrowedBookListCount(username, status)).thenReturn(1);
        assertEquals(1, bookController.borrowedBookListCount(username, status));
    }

    @Test
    public void shouldBorrowedBookListCountWhenBorrowing(){

        String username = "zhoujie";
        String status = "borrowing";

        when(bookService.getBorrowedBookListCount(username, status)).thenReturn(100);
        assertEquals(0, bookController.borrowedBookListCount(username, status));
    }

    @Test
    public void shouldBorrowedBooksListWhenBorrowing(){

        String username = "zhoujie";
        String pagenumber = "1";
        String status = "borrowing";

        List<BorrowBook> expectedResult = Lists.newArrayList();
        BorrowBook borrowBook = prepareOneBorrowedBook(1, "ABC", "Thinking in Java", "11-234324", "one", "abc", new Date(), 1, new Date());
        expectedResult.add(borrowBook);

        when(bookService.getBorrowedBookList(username, pagenumber, status)).thenReturn(expectedResult);

        List<BorrowBook> actualResult = bookController.borrowedBooksList(username, pagenumber, status);
        assertEquals(expectedResult.size(), actualResult.size());
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldBorrowedBooksListWhenReturned(){

        String username = "zhoujie";
        String pagenumber = "1";
        String status = "returned";

        List<BorrowBook> expectedResult = Lists.newArrayList();
        BorrowBook borrowBook = prepareOneBorrowedBook(1, "ABC", "Thinking in Java", "11-234324", "one", "abc", new Date(), 1, new Date());
        expectedResult.add(borrowBook);

        when(bookService.getBorrowedBookList(username, pagenumber, status)).thenReturn(expectedResult);

        List<BorrowBook> actualResult = bookController.borrowedBooksList(username, pagenumber, status);
        assertEquals(expectedResult.size(), actualResult.size());
        assertEquals(expectedResult, actualResult);
    }

    private BorrowBook prepareOneBorrowedBook(int bookId, String author, String name, String isbn, String publisher, String introduction, Date borrowDate, int borrowId, Date returnDate) {
        BorrowBook book = new BorrowBook();
        book.setId(bookId);
        book.setAuthor(author);
        book.setName(name);
        book.setIsbn(isbn);
        book.setPublisher(publisher);
        book.setIntroduction(introduction);
        book.setCreatedTime(new Date());
        book.setBorrowDate(borrowDate);
        book.setReturnDate(returnDate);
        book.setBorrowId(borrowId);

        return book;
    }

}
