package com.thoughtworks.dolphin.dao;

import com.thoughtworks.dolphin.model.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookDAO {
    int addBook(Book book);

    @Select("select i.image_id, i.image_url, book_id, book_name, author, isbn, publisher, cover_image_id, introduction " +
            "from books b, images i " +
            "where b.cover_image_id=i.image_id " +
            "order by book_id limit #{fromIdx}, #{len}")
    @ResultMap("getBooksResultMap")
    List<Book> getBooks(@Param("fromIdx") int fromIdx, @Param("len") int len);

    @Select("select count(1) as cnt from books")
    int getAllBookCount();

    @Select("select book_id as id, book_name as name, author, isbn, publisher, cover_image_id as coverImageId, introduction from books where isbn = #{isbn}")
    List<Book> getBookByISBN(String isbn);
}
