package com.dlsu;

import java.util.Random;
import java.io.PrintWriter;

public class SortingAlgorithms {

    private static void swap(Record[] array, int firstIndex, int secondIndex, PrintWriter writer) {
        Record temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        printArray(array, writer);
    }

    public long insertionSort(Record[] array, int length, PrintWriter writer) {
        long ctr = 1;
        for (int i = 1; i < length; ++i) {
            Record key = array[i]; ctr++;
            int j = i - 1; ctr++;
            ctr++;
            while (j >= 0 && array[j].getIdNumber() > key.getIdNumber()) {
                ctr++;
                array[j + 1] = array[j]; ctr++;
                j = j - 1; ctr++;
                ctr++;
            }
            array[j + 1] = key; ctr++;
            printArray(array, writer);
        }
        return ctr;
    }

    public long selectionSort(Record[] array, int length, PrintWriter writer) {
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
            swap(array, minIndex, i, writer); ctr++;
        }
        return ctr;
    }

    public long mergeSort(Record[] array, int start, int end, PrintWriter writer) {
        long ctr = 1;
        if (start < end) {
            ctr++;
            int mid = (start + end) / 2;
            ctr += mergeSort(array, start, mid, writer);
            ctr += mergeSort(array, mid + 1, end, writer);
            ctr += merge(array, start, mid, end, writer);
        }
        return ctr;
    }

    private long merge(Record[] array, int start, int mid, int end, PrintWriter writer) {
        int n1 = mid - start + 1;
        int n2 = end - mid;

        Record[] L = new Record[n1];
        Record[] R = new Record[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = array[start + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[mid + 1 + j];

        int i = 0, j = 0;

        int k = start;
        while (i < n1 && j < n2) {
            if (L[i].getIdNumber() <= R[j].getIdNumber()) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }

        printArray(array, writer); // print array after merging

        return k; // return number of operations
    }

    public static long bogoSort(Record[] array, int length, PrintWriter writer) {
        Random random = new Random();
        long ctr = 0;

        while (!isSorted(array, length)) {
            ctr++;
            for (int i = 0; i < length; i++) {
                int swapPosition = random.nextInt(length);
                Record temp = array[i];
                array[i] = array[swapPosition];
                array[swapPosition] = temp;
            }
            printArray(array, writer);
        }
        return ctr;
    }

    private static boolean isSorted(Record[] array, int length) {
        for (int i = 0; i < length - 1; i++) {
            if (array[i].getIdNumber() > array[i + 1].getIdNumber()) {
                return false;
            }
        }
        return true;
    }

    private static void printArray(Record[] array, PrintWriter writer) {
        for (Record r : array) {
            writer.print(r.getIdNumber() + " ");
        }
        writer.println();
    }
}
