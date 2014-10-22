package com.thoughtworks.dolphin.model;

import java.io.Serializable;
import java.util.Date;

public class Borrow implements Serializable{

    private String username;
    private String bookId;
    private Date borrowDate;
    private String bookName;
    private String author;
    private Image image;

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setBookName(String bookName) {

        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }


    public String getUsername() {
        return username;
    }

    public String getBookId() {
        return bookId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {

        this.borrowDate = borrowDate;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public String getCoverImageUrl() {
        if (image == null) {
            return "";
        } else {
            return this.image.getImageUrl();
        }
    }
}
