package com.thoughtworks.dolphin.service;

import com.thoughtworks.dolphin.model.Book;

import java.util.List;

public interface BookService {

    int insertBook(Book book);

    int getAllBookCount();

    List<Book> getBooks(int fromIdx, int len);

    boolean isExist(String isbn);

    Book getBook(String bookId);
}
