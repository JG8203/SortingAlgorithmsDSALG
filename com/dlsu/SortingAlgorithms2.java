package com.dlsu;

public class SortingAlgorithms2 {

    private static void swap(Record[] array, int firstIndex, int secondIndex) {
        Record temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    // Insertion sort algorithm
    public Record[] insertionSort(Record[] array, int length) {
        for (int i = 1; i < length; ++i) {
            Record key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j].getIdNumber() > key.getIdNumber()) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        return array;
    }

    // Selection sort algorithm
    public Record[] selectionSort(Record[] array, int length) {
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (array[j].getIdNumber() < array[minIndex].getIdNumber()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(array, minIndex, i);
            }
        }
        return array;
    }

    // Merge sort algorithm
    public Record[] mergeSort(Record[] array, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);

            int leftArraySize = mid - start + 1;
            int rightArraySize = end - mid;

            Record[] leftArray = new Record[leftArraySize];
            Record[] rightArray = new Record[rightArraySize];

            System.arraycopy(array, start, leftArray, 0, leftArraySize);
            System.arraycopy(array, mid + 1, rightArray, 0, rightArraySize);

            int k = start;

            for (int i = 0, j = 0; i < leftArraySize && j < rightArraySize; k++) {
                if (leftArray[i].getIdNumber() <= rightArray[j].getIdNumber()) {
                    array[k] = leftArray[i++];
                } else {
                    array[k] = rightArray[j++];
                }
            }

            for (int i = k - start; i < leftArraySize; i++, k++) {
                array[k] = leftArray[i];
            }

            for (int j = k - (mid + 1); j < rightArraySize; j++, k++) {
                array[k] = rightArray[j];
            }
        }
        return array;
    }

    private static boolean isSorted(Record[] array, int length) {
        for (int i = 1; i < length; i++) {
            if (array[i].getIdNumber() < array[i - 1].getIdNumber()) {
                return false;
            }
        }
        return true;
    }

    public static Record[] bogoSort(Record[] array, int length) {
        while (!isSorted(array, length)) {
            for (int i = 0; i < length; i++) {
                int randomIndex = (int) (Math.random() * length);
                swap(array, i, randomIndex);
            }
        }
        return array;
    }
}
