package com.thoughtworks.dolphin.dao;

import com.google.common.collect.Lists;
import com.thoughtworks.dolphin.AbstractUnitTest;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.model.Image;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.*;

public class BookDAOTest extends AbstractUnitTest {

    @Autowired
    private BookDAO bookMapper;

    @Autowired
    private ImageDAO imageDAO;

    private List<Book> books;
    private List<Image> images;

    @Before
    public void setUp() throws Exception {
        images = prepareImages();

        for (Image image : images) {
            imageDAO.addImage(image);
        }

        books = prepareBooks();

        for (Book book : books) {
            bookMapper.addBook(book);
        }
    }

    @Test
    public void shouldGetOneBook() throws Exception {
        final int bookId = books.get(0).getId();
        assertTrue(bookId > 0);
        Book book = bookMapper.getBookById(bookId);
        assertNotNull(book);
    }

    @Test
    public void shouldExistIsbn() throws Exception {
        final String isbn = books.get(0).getIsbn();
        assertNotNull(bookMapper.getBookByISBN(isbn));
    }

    @Test
    public void shouldGetBooks() {
        int count = bookMapper.getAllBooks().size();
        assertTrue(count > 0);
    }

    @Test
    public void shouldAddBook() throws Exception {
        Book book = prepareOneBook("Jason", "abc", "004-004", "ppp", "xxx", new Date(), images.get(2), 10);
        bookMapper.addBook(book);
        assertNotNull(book);
        assertTrue(book.getId() > 0);
    }

    @Test
    public void shouldUpdateBook() throws Exception {
        Book firstBook = books.get(0);
        firstBook.setPublisher("updated publisher");
        bookMapper.updateBook(firstBook);

        Book updatedBook = bookMapper.getBookById(firstBook.getId());
        assertEquals("updated publisher", updatedBook.getPublisher());
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        final String isbn = books.get(0).getIsbn();
        bookMapper.deleteBook(isbn);

        Book book = bookMapper.getBookByISBN(isbn);
        assertNull(book);
    }

    @Test
    public void shouldBorrowBook() throws Exception {

        Book book = books.get(0);
        int bookId = book.getId();
        String userName = "zhoujie";

        assertTrue(bookMapper.borrowBook(bookId, userName) > 0);
    }

    @Test
    public void shouldGetBorrowedBookListCount() throws Exception {
        final String username = "zhoujie";

        int borrowedCount = bookMapper.getBorrowedBookListCount(username);

        bookMapper.borrowBook(books.get(1).getId(), username);

        assertEquals(borrowedCount + 1, bookMapper.getBorrowedBookListCount(username));

    }

    @Test
    public void shouldGetBorrowedBookList() throws Exception{

        String username = "zhoujie";
        int fromIdx = 0;
        int len = 10;

        assertEquals(1,bookMapper.getBorrowedBookList(username, fromIdx, len).size());

    }

    private Book prepareOneBook(String author, String name, String isbn, String publisher, String introduction, Date createTime, Image image, int totalQty) {
        Book book = new Book();
        book.setImage(image);
        book.setCoverImageId(image.getImageId());
        book.setAuthor(author);
        book.setName(name);
        book.setIsbn(isbn);
        book.setPublisher(publisher);
        book.setIntroduction(introduction);
        book.setCreatedTime(createTime);
        book.setTotalNumber(totalQty);
        return book;
    }

    private List<Book> prepareBooks() {
        List<Book> books = Lists.newArrayList();
        books.add(prepareOneBook("Catherine 1", "Thinking in Java", "001-001", "publisher 1", "xxx xxx", new Date(), images.get(0), 10));
        books.add(prepareOneBook("Catherine 2", "Thinking in Ruby", "002-002", "publisher 2", "xxx xxx", new Date(), images.get(1), 10));
        books.add(prepareOneBook("Catherine 3", "Thinking in VB", "003-003", "publisher 3", "xxx xxx", new Date(), images.get(2), 10));
        return books;
    }

    private List<Image> prepareImages() {
        List<Image> imageList = Lists.newArrayList();
        Image image1 = new Image();
        image1.setImageUrl("/upload/image1.jpg");
        Image image2 = new Image();
        image2.setImageUrl("/upload/image2.jpg");
        Image image3 = new Image();
        image3.setImageUrl("/upload/image3.jpg");
        imageList.add(image1);
        imageList.add(image2);
        imageList.add(image3);
        return imageList;
    }
}
