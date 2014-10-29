package com.thoughtworks.dolphin.model;

import java.util.Date;

public class BorrowBook extends Book {

    private Date borrowDate;
    private int borrowId;
    private Date returnDate;

    private BorrowStatus status;

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public BorrowStatus getStatus() {
        return status;
    }

    public void setStatus(int val) {
        this.status = BorrowStatus.fromValue(val);
    }
}
