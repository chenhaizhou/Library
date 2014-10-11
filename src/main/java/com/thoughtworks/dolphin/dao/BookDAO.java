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

    @Select("select book_id as id, book_name as name, author, isbn, publisher, cover_image_id as coverImageId, introduction from books where isbn = #{isbn}")
    List<Book> getBookByISBN(String isbn);

    @Select("select book_id, book_name, author, isbn, publisher, cover_image_id, introduction " +
            "from books " +
            "where book_id = #{bookId}")
    @ResultMap("getBooksResultMap")
    List<Book> getBookById(String bookId);
}
