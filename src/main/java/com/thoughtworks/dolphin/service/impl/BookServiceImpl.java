package com.thoughtworks.dolphin.service.impl;

import com.thoughtworks.dolphin.dao.BookDAO;
import com.thoughtworks.dolphin.dao.UserDAO;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;

/**
 * Created by jiezhou on 9/28/14.
 */
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDAO bookDAO;

    public int insertBook(Book book) {
        return bookDAO.addBook(book);
    }
}
