package com.thoughtworks.dolphin.model;

import com.google.common.primitives.Ints;
import com.thoughtworks.dolphin.common.IndexTarget;

import java.io.Serializable;
import java.util.Date;

public class Book extends IndexTarget<Book> implements Serializable {

    private int id;
    private String name;
    private String author;
    private String isbn;
    private String publisher;
    private String introduction;
    private int borrowedNumber;
    private int totalNumber;

    private int coverImageId;

    private Date createdTime;

    public int getCoverImageId() {
        return coverImageId;
    }

    private Image image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCoverImageUrl() {
        if (image == null) {
            return "";
        } else {
            return this.image.getImageUrl();
        }
    }

    public void setCoverImageId(int coverImageId) {
        this.coverImageId = coverImageId;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getBorrowedNumber() {
        return borrowedNumber;
    }

    public void setBorrowedNumber(int borrowedNumber) {
        this.borrowedNumber = borrowedNumber;
    }

    @Override
    public int compare(Book o) {
        if (o == null) {
            return -1;
        }
        return Ints.compare(this.getId(), o.getId());
    }
}
