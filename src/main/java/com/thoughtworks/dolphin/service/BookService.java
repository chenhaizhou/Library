package com.thoughtworks.dolphin.service;

import com.thoughtworks.dolphin.model.Book;

import java.util.List;

/**
 * Created by jiezhou on 9/28/14.
 */
public interface BookService {


    int insertBook(Book book);

    int getAllBookCount();

    List<Book> getBooks(int fromIdx, int len);
}
