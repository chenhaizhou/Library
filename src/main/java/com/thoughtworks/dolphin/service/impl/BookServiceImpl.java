package com.thoughtworks.dolphin.service.impl;

import com.thoughtworks.dolphin.dao.BookDAO;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDAO bookMapper;

    public int insertBook(Book book) {
        book.setCreatedTime(new Date(System.currentTimeMillis()));
        return bookMapper.addBook(book);
    }

    public int getAllBookCount() {
        return bookMapper.getAllBookCount();
    }

    public List<Book> getBooks(int fromIdx, int len) {
        return bookMapper.getBooks(fromIdx, len);
    }

    public boolean isExist(String isbn) {
        List<Book> newBookList = bookMapper.getBookByISBN(isbn);
        return !newBookList.isEmpty();
    }
}
