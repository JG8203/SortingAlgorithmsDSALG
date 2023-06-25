package com.dlsu;

public class SortResult {
    private final Record[] sortedArray;
    private final long frequencyCount;

    public SortResult(Record[] sortedArray, long frequencyCount) {
        this.sortedArray = sortedArray;
        this.frequencyCount = frequencyCount;
    }

    public Record[] getSortedArray() {
        return sortedArray;
    }

    public long getFrequencyCount() {
        return frequencyCount;
    }
}

