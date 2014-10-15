package com.thoughtworks.dolphin.service;

import com.thoughtworks.dolphin.dto.BookSearchCondition;
import com.thoughtworks.dolphin.model.Book;

import java.util.List;

public interface BookService {

    int insertBook(Book book);

    int getBookCount(BookSearchCondition condition);

    List<Book> getBooks(BookSearchCondition condition);

    boolean isExist(String isbn);

    Book getBook(String bookId);

    void deleteBook(String isbn, String path);

    int updateBook(Book book);
}
