package com.thoughtworks.dolphin.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.dolphin.AbstractUnitTest;
import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.common.IndexConfig;
import com.thoughtworks.dolphin.common.SearchResult;
import com.thoughtworks.dolphin.dao.BookDAO;
import com.thoughtworks.dolphin.dao.ImageDAO;
import com.thoughtworks.dolphin.dto.BookQuery;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.model.BorrowBook;
import com.thoughtworks.dolphin.service.impl.BookServiceImpl;
import com.thoughtworks.dolphin.util.CacheUtil;
import com.thoughtworks.dolphin.util.IndexUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class BookServiceTest extends AbstractUnitTest {

    @InjectMocks
//    @Autowired
    private BookServiceImpl bookService;

    @Mock
    private BookDAO bookMapper;

    @Mock
    private ImageDAO imageDAO;

    @Mock
    IndexConfig<Book> bookIndexConfig;

    @Mock
    private CacheUtil cacheUtil;

    @Mock
    private IndexUtil indexUtil;

    @Mock
    private UploadService uploadService;

    private Map<Integer, Book> books;

    @BeforeClass
    public static void before() throws Exception {
        File dir = new File(Constants.INDEX_DIRECTORY + File.separator + Book.class.getSimpleName());
        if (dir.exists()) {
            dir.delete();
        }
    }

    @Before
    public void setUp() throws Exception {

        bookService = new BookServiceImpl();

        MockitoAnnotations.initMocks(this);

        books= prepareBooks();
        for(int bookId : books.keySet()){
            Book book = books.get(bookId);
            when(bookMapper.getBookById(bookId)).thenReturn(book);
            when(bookMapper.getBookByISBN(book.getIsbn())).thenReturn(book);
        }

        when(bookMapper.getAllBooks()).thenReturn(Lists.newArrayList(books.values()));
        when(bookMapper.addBook(books.get(1))).thenReturn(books.get(1).getId());

        when(bookMapper.getBorrowedBookListCount("zhoujie", 0)).thenReturn(1);

        IndexConfig<Book> indexConfig = prepareBookConfig();
        bookIndexConfig = mock(IndexConfig.class);
        when(bookIndexConfig.getDirectory()).thenReturn(indexConfig.getDirectory());
        when(bookIndexConfig.getDocumentTranslateFunction()).thenReturn(indexConfig.getDocumentTranslateFunction());
        when(bookIndexConfig.getKeyField()).thenReturn(indexConfig.getKeyField());
        when(bookIndexConfig.getTargetTranslateFunction()).thenReturn(indexConfig.getTargetTranslateFunction());

        bookService.setBookIndexConfig(bookIndexConfig);

        IndexUtil.getInstance().createIndex(indexConfig, Lists.newArrayList(books.values()));

        cacheUtil = CacheUtil.getInstance();
        cacheUtil.put("books", books);

        List<Book> bookList = Lists.newArrayList(books.values());
        indexUtil = mock(IndexUtil.class);
        doNothing().when(indexUtil).createIndex(bookIndexConfig, bookList);
        doNothing().when(indexUtil).updateIndex(bookIndexConfig, "1", bookList);
        when(uploadService.deleteImage(anyString(), anyString())).thenReturn(true);
        bookService.setIndexUtil(indexUtil);
    }
    private IndexConfig<Book> prepareBookConfig() {
        try {
            IndexConfig<Book> bookIndexConfig = IndexConfig.newConfig(Book.class);
            bookIndexConfig.setKeyField("id");
            bookIndexConfig.setDocTranslator(new Function<Book, Document>() {
                public Document apply(Book book) {
                    Document doc = new Document();
                    doc.add(new IntField("id", book.getId(), Field.Store.YES));
                    doc.add(new TextField("name", book.getName(), Field.Store.YES));
                    doc.add(new TextField("author", book.getAuthor(), Field.Store.YES));
                    doc.add(new TextField("isbn", book.getIsbn(), Field.Store.YES));
                    doc.add(new TextField("publisher", book.getPublisher(), Field.Store.YES));
                    return doc;
                }
            });
            bookIndexConfig.setTargetTranslator(new Function<Document, Book>() {
                public Book apply(Document doc) {
                    return ((Map<Integer, Book>) CacheUtil.getInstance().get("books")).get(Integer.parseInt(doc.get("id")));
                }
            });
            return bookIndexConfig;
        } catch (IOException e) {
            return null;
        }

    }

    @Test
    public void shouldGetBookById() throws Exception {
        final int bookId = 1;

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
        when(bookMapper.getBookById(anyInt())).thenReturn(book);
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
    public void shouldGetBorrowedBookListCountWhenBorrowed(){
        String userName = "jack";
        String status = "borrowed";

        when(bookMapper.getBorrowedBookListCount(userName, 0)).thenReturn(1);
        assertEquals(1, bookService.getBorrowedBookListCount(userName, status));
    }

    @Test
    public void shouldGetBorrowedBookListCountWhenReturned(){
        String userName = "jack";
        String status = "returned";

        when(bookMapper.getBorrowedBookListCount(userName, 1)).thenReturn(2);
        assertEquals(2, bookService.getBorrowedBookListCount(userName, status));
    }

    @Test
    public void shouldGetBorrowedBookListWhenBorrowing(){

        String username = "zhoujie";
        String pagenumber = "1";

        String status = "borrowing";

        List<BorrowBook> borrowingList = Lists.newArrayList();
        BorrowBook borrowBook = TestObject.prepareOneBorrowedBook(1, "ABC", "Thinking in Java", "11-234324", "one", "abc", new Date(), 1, new Date());
        borrowingList.add(borrowBook);
        when(bookMapper.getBorrowingBookList(username, 0)).thenReturn(borrowingList);

        List<BorrowBook> actualBorrowingList = bookService.getBorrowedBookList(username, pagenumber, status);


        List<BorrowBook> expectedBorrowingList = new ArrayList<BorrowBook>();
        BorrowBook expectedBook = TestObject.prepareOneBorrowedBook(1, "ABC", "Thinking in Java", "11-234324", "one", "abc", new Date(), 1, new Date());
        expectedBorrowingList.add(expectedBook);

        assertEquals(expectedBorrowingList.size(), actualBorrowingList.size());
        BorrowBook actualBook = actualBorrowingList.get(0);

        assertEquals(expectedBook.getBorrowId(), actualBook.getBorrowId());
        assertEquals(expectedBook.getAuthor(), actualBook.getAuthor());

    }

    @Test
    public void shouldGetBorrowedBookListWhenReturned(){

        String username = "zhoujie";
        String pagenumber = "1";

        String status = "returned";
        int fromIdx = 0;
        int len = 10;

        List<BorrowBook> borrowingList = Lists.newArrayList();
        final long timestamp = System.currentTimeMillis();
        final long returnTimestamp = timestamp + 10;
        BorrowBook borrowBook = TestObject.prepareOneBorrowedBook(1, "ABC", "Thinking in Java", "11-234324", "one", "abc", new Date(timestamp), 1, new Date(returnTimestamp));
        borrowingList.add(borrowBook);
        when(bookMapper.getBorrowedBookList(username, fromIdx, len, 1)).thenReturn(borrowingList);

        List<BorrowBook> actualBorrowingList = bookService.getBorrowedBookList(username, pagenumber, status);


        List<BorrowBook> expectedBorrowingList = new ArrayList<BorrowBook>();
        BorrowBook expectedBook = TestObject.prepareOneBorrowedBook(1, "ABC", "Thinking in Java", "11-234324", "one", "abc", new Date(timestamp), 1, new Date(returnTimestamp));
        expectedBorrowingList.add(expectedBook);


        assertEquals(expectedBorrowingList.size(), actualBorrowingList.size());
        BorrowBook actualBook = actualBorrowingList.get(0);

        assertEquals(expectedBook.getReturnDate(), actualBook.getReturnDate());
        assertEquals(expectedBook.getAuthor(), actualBook.getAuthor());
    }

    private Map<Integer,Book> prepareBooks(){
        Map<Integer,Book> books = Maps.newHashMap();
        Book book1 = TestObject.prepareOneBook(1, "Catherine 1", "Thinking in Java", "001-001", "publisher 1", "xxx xxx");
        Book book2 = TestObject.prepareOneBook(2, "Catherine 2", "Thinking in Ruby", "002-002", "publisher 2", "xxx xxx");
        Book book3 = TestObject.prepareOneBook(3, "Catherine 3", "Thinking in Net", "003-003", "publisher 3", "xxx xxx");

        books.put(1, book1);
        books.put(2, book2);
        books.put(3, book3);
        return books;
    }
}
