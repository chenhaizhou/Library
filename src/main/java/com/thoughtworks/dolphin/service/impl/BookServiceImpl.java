package com.thoughtworks.dolphin.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.dolphin.common.BookIndexConfig;
import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.common.IndexConfig;
import com.thoughtworks.dolphin.common.SearchResult;
import com.thoughtworks.dolphin.dao.BookDAO;
import com.thoughtworks.dolphin.dao.ImageDAO;
import com.thoughtworks.dolphin.dto.BookQuery;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.model.BorrowBook;
import com.thoughtworks.dolphin.service.BookService;
import com.thoughtworks.dolphin.service.UploadService;
import com.thoughtworks.dolphin.util.CacheUtil;
import com.thoughtworks.dolphin.util.IndexUtil;
import org.apache.log4j.Logger;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDAO bookMapper;
    @Autowired
    private ImageDAO imageDAO;

    private IndexUtil indexUtil = IndexUtil.getInstance();

    private static Logger LOG = Logger.getLogger(BookServiceImpl.class);

    @Autowired
    private UploadService uploadService;

    private IndexConfig<Book> bookIndexConfig;


    public void setBookIndexConfig(IndexConfig<Book> bookIndexConfig) {
        this.bookIndexConfig = bookIndexConfig;
    }

    public void setIndexUtil(IndexUtil indexUtil) {
        this.indexUtil = indexUtil;
    }

    public int insertBook(Book book) {
        book.setCreatedTime(new Date(System.currentTimeMillis()));
        int insertValue = bookMapper.addBook(book);
        addOrUpdateBookCache(book.getId());
        return insertValue;
    }


    public SearchResult<Book> getBooks(BookQuery condition) {
        int fromIdx = Constants.ITEM_COUNT_IN_EACH_PAGE * (condition.getPageNumber() - 1);
        int toIdx = fromIdx + Constants.ITEM_COUNT_IN_EACH_PAGE;

        try {
            List<Book> allBooks = Lists.newArrayList(getBooksFromCache().values());
            if (Strings.isNullOrEmpty(condition.getKeyword())) {
                return new SearchResult<Book>(subList(allBooks, fromIdx, toIdx), getBooksFromCache().values().size());
            }
            return indexUtil.search(Constants.SEARCH_BOOK_FIELDS, condition.getKeyword(), bookIndexConfig, fromIdx, toIdx);
        } catch (IOException e) {
            LOG.error(e.getMessage());
            return new SearchResult<Book>(null, 0);
        } catch (ParseException e) {
            LOG.error(e.getMessage());
            return new SearchResult<Book>(null, 0);
        }
    }

    public <T> List<T> subList(List<T> list, int fromIdx, int toIdx) {
        if (list == null || list.size() == 0) {
            return Lists.newArrayList();
        }
        if (list.size() < fromIdx) {
            return Lists.newArrayList();
        }
        if (list.size() < toIdx) {
            toIdx = list.size();
        }
        return list.subList(fromIdx, toIdx);
    }


    public boolean isExist(String isbn) {

        try {
            Book book = bookMapper.getBookByISBN(isbn);
            return book != null;
        } catch (Exception e) {
            return false;
        }
    }

    public Book getBook(int bookId) {
        return bookMapper.getBookById(bookId);
    }

    public void deleteBook(String isbn, String path) {
        Book book = bookMapper.getBookByISBN(isbn);
        if (book != null) {

            String imageUrl = book.getCoverImageUrl();

            String fileName = imageUrl.substring(imageUrl.indexOf("/") + 1);

            bookMapper.deleteBook(isbn);
            imageDAO.deleteImage(book.getCoverImageId());

            uploadService.deleteImage(path, fileName);

            removeBookFromCache(book);
        }

    }

    public int updateBook(Book book) {
        int updateResult = bookMapper.updateBook(book);
        addOrUpdateBookCache(book.getId());
        return updateResult;

    }

    public String borrowBook(int bookId, String userName) {

        Book book = bookMapper.getBookById(bookId);
        if(remainNumber(book) <= 0){
            return "unavailable";
        }
        boolean success = bookMapper.borrowBook(bookId, userName) > 0;
        if(success) {
            addOrUpdateBookCache(bookId);
            return "success";
        } else {
            return "fail";
        }
    }

    public int getBorrowedBookListCount(String userName, String status) {
        int statusCode = 0;
        if(status.equals("returned")){
            statusCode = 1;
        }

        return bookMapper.getBorrowedBookListCount(userName, statusCode);
    }

    public List<BorrowBook> getBorrowedBookList(String username, String pagenumber, String status) {
        if(status.equals("borrowing")) {
            return bookMapper.getBorrowingBookList(username, 0);
        }

        int pageNumber = Integer.parseInt(pagenumber);
        int fromIdx = (pageNumber - 1) * Constants.ITEM_COUNT_IN_EACH_BORROW_PAGE;
        int len = Constants.ITEM_COUNT_IN_EACH_BORROW_PAGE;
        int statusCode = 0;
        if(status.equals("returned")){
            statusCode = 1;
        }
        return bookMapper.getBorrowedBookList(username, fromIdx, len, statusCode);
    }

    private int remainNumber(Book book) {
        return book.getTotalNumber() - book.getBorrowedNumber();
    }


    private Map<Integer, Book> getBooksFromCache() {
        CacheUtil cacheUtil = CacheUtil.getInstance();
        if (cacheUtil.get("books") == null) {
            initBookCache();
        }
        return (Map<Integer, Book>) cacheUtil.get("books");
    }

    private void initBookCache() {
        try {
            CacheUtil cacheUtil = CacheUtil.getInstance();
            List<Book> allBooks = bookMapper.getAllBooks();

            Map<Integer, Book> bookMap = Maps.newTreeMap();
            for (Book book : allBooks) {
                bookMap.put(book.getId(), book);
            }
            cacheUtil.put("books", bookMap);
            buildIndex(Lists.newArrayList(bookMap.values()));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    private void buildIndex(List<Book> books) {
        try {
            bookIndexConfig = BookIndexConfig.getBookIndexConfig();
            indexUtil.createIndex(bookIndexConfig, books);
            LOG.info("initialize index successfully.");
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }



    private void addOrUpdateBookCache(int bookId) {
        CacheUtil cacheUtil = CacheUtil.getInstance();
        Map<Integer, Book> bookMap = (Map<Integer, Book>) cacheUtil.get("books");
        Book book = bookMapper.getBookById(bookId);
        bookMap.put(bookId, book);
        updateIndexForBook(bookId, Lists.newArrayList(bookMap.values()));
    }

    private void removeBookFromCache(Book book) {
        CacheUtil cacheUtil = CacheUtil.getInstance();
        Map<Integer, Book> bookMap = (Map<Integer, Book>) cacheUtil.get("books");
        bookMap.remove(book.getId());
        updateIndexForBook(book.getId(), Lists.newArrayList(bookMap.values()));
    }

    private void updateIndexForBook(int bookId, List<Book> books) {
        try {
            indexUtil.updateIndex(bookIndexConfig, String.valueOf(bookId), books);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
