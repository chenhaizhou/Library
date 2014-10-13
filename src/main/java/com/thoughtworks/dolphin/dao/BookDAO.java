package com.thoughtworks.dolphin.dao;

import com.thoughtworks.dolphin.dto.BookSearchCondition;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.model.Image;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface BookDAO {
    int addBook(Book book);

    List<Book> getBooks(Map<String, Object> map);

    int getBookCount(BookSearchCondition condition);

    Book getBookByISBN(String isbn);

    Book getBookById(String bookId);

    void deleteBook(String isbn);
}
