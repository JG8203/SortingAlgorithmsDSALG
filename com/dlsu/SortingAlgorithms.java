package com.dlsu;

import java.util.Arrays;

public class SortingAlgorithms {

    private static void swap(Record[] array, int firstIndex, int secondIndex) {
        Record temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    public SortResult insertionSort(Record[] array, int length) {
        long ctr = 1;
        for (int i = 1; i < length; ++i) {
            Record key = array[i]; ctr++;
            int j = i - 1; ctr++;
            ctr++;
            while (j >= 0 && array[j].getIdNumber() > key.getIdNumber()) {
                ctr++;
                array[j + 1] = array[j]; ctr++;
                j--; ctr++;
            }
            array[j + 1] = key; ctr++;
            ctr++;
        }
        return new SortResult(array, ctr);
    }

    public SortResult selectionSort(Record[] array, int length) {
        long ctr = 1;
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i; ctr++;
            ctr++;
            for (int j = i + 1; j < length; j++) {
                ctr++;
                if (array[j].getIdNumber() < array[minIndex].getIdNumber()) {
                    minIndex = j;
                }
            }
            ctr++;
            if(minIndex != i) {
                swap(array, minIndex, i); ctr++;
            }
            ctr++;
        }
        return new SortResult(array, ctr);
    }

    public SortResult mergeSort(Record[] array, int start, int end) {
        long ctr = 1;
        if (start < end) {
            ctr++;
            int mid = (start + end) / 2;
            SortResult leftResult = mergeSort(array, start, mid);
            SortResult rightResult = mergeSort(array, mid + 1, end);

            SortResult mergeResult = merge(leftResult.getSortedArray(), rightResult.getSortedArray());
            return new SortResult(mergeResult.getSortedArray(), leftResult.getFrequencyCount() + rightResult.getFrequencyCount() + mergeResult.getFrequencyCount());
        } else {
            return new SortResult(new Record[]{array[start]}, ctr);
        }
    }

    private SortResult merge(Record[] leftArray, Record[] rightArray) {
        long ctr = 0;
        int leftLength = leftArray.length; ctr++;
        int rightLength = rightArray.length; ctr++;
        int mergedLength = leftLength + rightLength; ctr++;
        Record[] mergedArray = new Record[mergedLength]; ctr++;

        int i = 0, j = 0, k = 0;

        ctr++;
        while (i < leftLength && j < rightLength) {
            ctr++;
            if (leftArray[i].getIdNumber() <= rightArray[j].getIdNumber()) {
                mergedArray[k++] = leftArray[i++]; ctr++;
            } else {
                mergedArray[k++] = rightArray[j++]; ctr++;
            }
            ctr++;
        }
        ctr++;
        while (i < leftLength) {
            mergedArray[k++] = leftArray[i++];
            ctr++;
        }

        while (j < rightLength) {
            mergedArray[k++] = rightArray[j++];
            ctr++;
        }

        return new SortResult(mergedArray, ctr);
    }

    public static SortResult bogoSort(Record[] array, int length) {
        long ctr = 1;
        ctr++;
        while (!isSorted(array, length)) {
            for (int i = 0; i < length; i++) {
                int randomIndex = (int) (Math.random() * (i + 1));
                ctr++;
                swap(array, i, randomIndex);
                ctr++;
            }
            ctr++;
        }
        return new SortResult(array, ctr);
    }

    public static boolean isSorted(Record[] array, int length) {
        for (int i = 1; i < length; i++) {
            if (array[i].getIdNumber() < array[i - 1].getIdNumber()) {
                return false;
            }
        }
        return true;
    }
}
