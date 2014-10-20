package com.thoughtworks.dolphin.dao;

import com.thoughtworks.dolphin.dto.BookQuery;
import com.thoughtworks.dolphin.model.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookDAO {
    int addBook(Book book);

    List<Book> getBooks(Map<String, Object> map);

    int getBookCount(BookQuery condition);

    Book getBookByISBN(String isbn);

    Book getBookById(int bookId);

    void deleteBook(String isbn);

    int updateBook(Book book);

    boolean borrowBook(@Param("isbn") String isbn);
}
