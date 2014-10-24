package com.thoughtworks.dolphin.common;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

public class SearchResult<T extends IndexTarget> implements Serializable {

    private List<T> resultData;

    private int totalCount;

    public SearchResult() {
    }

    public SearchResult(List<T> resultData, int totalCount) {
        if (resultData == null) {
            resultData = Lists.newArrayList();
        }
        this.resultData = resultData;
        this.totalCount = totalCount;
    }

    public List<T> getResultData() {
        return resultData;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
