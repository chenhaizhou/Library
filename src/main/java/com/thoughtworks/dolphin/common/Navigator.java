package com.thoughtworks.dolphin.common;

/**
 * Created by lzwu on 9/28/14.
 */
public class Navigator {

    private int itemCount;

    private int pageCount;

    public Navigator(int itemCount, int pageCount) {
        this.itemCount = itemCount;
        this.pageCount = pageCount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public int getPageCount() {
        return pageCount;
    }
}
