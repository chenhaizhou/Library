package com.thoughtworks.dolphin.service;

import com.thoughtworks.dolphin.dto.BookQuery;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.model.BorrowBook;

import java.util.List;

public interface BookService {

    int insertBook(Book book);

    int getBookCount(BookQuery condition);

    List<Book> getBooks(BookQuery condition);

    boolean isExist(String isbn);

    Book getBook(int bookId);

    void deleteBook(String isbn, String path);

    int updateBook(Book book);

    String borrowBook(int bookId, String userName);

    int getBorrowedBookListCount(String userName, String status);

    List<BorrowBook> getBorrowedBookList(String username, String pagenumber, String status);

}
