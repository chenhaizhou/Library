package com.thoughtworks.dolphin.common;

import com.google.common.primitives.Floats;

public abstract class IndexTarget<T extends IndexTarget> implements Comparable<IndexTarget> {

    protected float score;

    public float getScore() {
        return score;
    }

    public void setScore(float score) {

        this.score = score;
    }
    public int compareTo(IndexTarget o) {
        if (o == null) {
            return -1;
        }
        int scoreCompareValue = Floats.compare(o.score, this.score);
        if (scoreCompareValue != 0) {
            return scoreCompareValue;
        }
        return compare((T) o);
    }

    public abstract int compare(T o);
}
