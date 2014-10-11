package com.thoughtworks.dolphin.dto;

import com.google.common.base.Strings;

import java.io.Serializable;

public class BookSearchCondition implements Serializable {

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
        if (Strings.isNullOrEmpty(keyword)) {
            this.keyword = null;
        } else {
            this.keyword = keyword.trim();
        }
    }

    private int pageNumber;
    
    private  String keyword;

}
