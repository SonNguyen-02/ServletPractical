package com.mct.practical.practical3.utils;

public class Pair<K, L> {
    public final K first;
    public final L second;

    public Pair(K first, L second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first + ":" + second;
    }
}
