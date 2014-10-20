package com.thoughtworks.dolphin.service.impl;

import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.dao.BookDAO;
import com.thoughtworks.dolphin.dao.ImageDAO;
import com.thoughtworks.dolphin.dto.BookQuery;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.service.BookService;
import com.thoughtworks.dolphin.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDAO bookMapper;
    @Autowired
    private ImageDAO imageDAO;

    @Autowired
    private UploadService uploadService;

    public int insertBook(Book book) {
        book.setCreatedTime(new Date(System.currentTimeMillis()));
        return bookMapper.addBook(book);
    }

    public int getBookCount(BookQuery condition) {
        return bookMapper.getBookCount(condition);
    }


    public List<Book> getBooks(BookQuery condition) {
        int pageIndex = Constants.ITEM_COUNT_IN_EACH_PAGE * (condition.getPageNumber() - 1);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("keyword", condition.getKeyword());
        paramMap.put("fromIdx", pageIndex);
        paramMap.put("len", Constants.ITEM_COUNT_IN_EACH_PAGE);
        List<Book> books = bookMapper.getBooks(paramMap);

        return books;
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

            uploadService.deleteImage(path,fileName);
        }

    }

    public int updateBook(Book book) {

        return bookMapper.updateBook(book);

    }

    @Override
    public boolean borrowBook(int bookId, String userName) {

        return bookMapper.borrowBook(bookId, userName) > 0;
    }

}
