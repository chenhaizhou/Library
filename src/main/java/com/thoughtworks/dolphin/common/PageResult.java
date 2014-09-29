package com.thoughtworks.dolphin.common;

import java.util.List;

/**
 * Created by lzwu on 9/28/14.
 */
public abstract class PageResult<T> {

    protected int itemCount;

    protected List<T> items;

    public int getItemCount() {
        return itemCount;
    }

    public List<T> getItems() {
        return items;
    }
}
