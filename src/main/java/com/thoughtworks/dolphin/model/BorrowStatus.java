package com.thoughtworks.dolphin.model;

import com.google.common.collect.Maps;

import java.util.Map;

public enum BorrowStatus {
    BORROWING(0), BORROWED(1);

    private int status;

    private BorrowStatus(int val) {
        this.status = val;
    }

    public int getValue() {
        return this.status;
    }

    private static Map<Integer, BorrowStatus> valueMap = Maps.newHashMap();
    static {
        for (BorrowStatus sts : BorrowStatus.values()) {
            valueMap.put(sts.getValue(), sts);
        }
    }

    public static BorrowStatus fromValue(int val) {
        return valueMap.get(val);
    }
}
