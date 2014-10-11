package com.thoughtworks.dolphin.service.impl;

import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.dao.BookDAO;
import com.thoughtworks.dolphin.dto.BookSearchCondition;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.service.BookService;
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

    public int insertBook(Book book) {
        book.setCreatedTime(new Date(System.currentTimeMillis()));
        return bookMapper.addBook(book);
    }

    public int getBookCount(BookSearchCondition condition) {
        return bookMapper.getBookCount(condition);
    }


    public List<Book> getBooks(BookSearchCondition condition) {
        int pageIndex = Constants.ITEM_COUNT_IN_EACH_PAGE * (condition.getPageNumber() - 1);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("keyword", condition.getKeyword());
        paramMap.put("fromIdx", pageIndex);
        paramMap.put("len", Constants.ITEM_COUNT_IN_EACH_PAGE);
        List<Book> books = bookMapper.getBooks(paramMap);

        return books;
    }


    public boolean isExist(String isbn) {
        List<Book> newBookList = bookMapper.getBookByISBN(isbn);
        return !newBookList.isEmpty();
    }
}
