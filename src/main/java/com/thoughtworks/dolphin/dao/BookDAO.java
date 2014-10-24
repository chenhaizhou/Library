package com.thoughtworks.dolphin.dao;

import com.thoughtworks.dolphin.dto.BookQuery;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.model.BorrowBook;
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

    int borrowBook(@Param("id")int bookId, @Param("userName")String userName);

    int getBorrowedBookListCount(@Param("username")String username, @Param("status") int status);

    List<BorrowBook> getBorrowedBookList(@Param("username") String username, @Param("fromIdx") int fromIdx, @Param("len") int len, @Param("status")int status);

    List<BorrowBook> getBorrowingBookList(@Param("username") String username, @Param("status")int status);
}
