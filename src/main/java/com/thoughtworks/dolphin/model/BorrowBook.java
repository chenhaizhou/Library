package com.thoughtworks.dolphin.model;

import java.util.Date;

/**
 * Created by hdzhang on 10/23/14.
 */
public class BorrowBook extends Book {

    private Date borrowDate;

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }
}
