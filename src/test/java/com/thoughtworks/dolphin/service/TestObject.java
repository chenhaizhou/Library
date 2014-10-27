package com.thoughtworks.dolphin.service;

import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.model.BorrowBook;

import java.util.Date;

public class TestObject {
    public static Book prepareOneBook(int bookId, String author, String name, String isbn, String publisher, String introduction) {
        Book book = new Book();
        book.setId(bookId);
        book.setAuthor(author);
        book.setName(name);
        book.setIsbn(isbn);
        book.setPublisher(publisher);
        book.setIntroduction(introduction);
        book.setCreatedTime(new Date());
        return book;
    }

    public static BorrowBook prepareOneBorrowedBook(int bookId, String author, String name, String isbn, String publisher, String introduction, Date borrowDate, int borrowId, Date returnDate) {
        BorrowBook book = new BorrowBook();
        book.setId(bookId);
        book.setAuthor(author);
        book.setName(name);
        book.setIsbn(isbn);
        book.setPublisher(publisher);
        book.setIntroduction(introduction);
        book.setCreatedTime(new Date());
        book.setBorrowDate(borrowDate);
        book.setReturnDate(returnDate);
        book.setBorrowId(borrowId);

        return book;
    }
}
