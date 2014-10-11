package com.thoughtworks.dolphin.dto;

public class BookSearchCondition  {

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    private int pageNumber;
    
    private  String keyword;

}
