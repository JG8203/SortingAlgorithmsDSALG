package com.dlsu;

import java.util.Random;
import java.io.PrintWriter;

public class SortingAlgorithms {

    private static void swap(Record[] array, int firstIndex, int secondIndex) {
        Record temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
    public long insertionSort(Record[] array, int length, PrintWriter writer) {
        long ctr = 1; // for the outer for loop initialization
        for (int i = 1; i < length; ++i) {
            Record key = array[i]; ctr++; // for the key assignment
            int j = i - 1; ctr++;
            ctr++; // for the j initialization
            while (j >= 0 && array[j].getIdNumber() > key.getIdNumber()) {
                ctr++; // for the while loop condition
                array[j + 1] = array[j]; ctr++;
                j = j - 1; ctr++;
                ctr++; // for the while loop condition
            }
            array[j + 1] = key; ctr++;
            writer.println("Iteration " + i + ":");
            for (Record r : array) {
                writer.println(r.getIdNumber() + " " + r.getName());
            }
            writer.println();
        }
        return ctr;
    }

    // Similar modifications for other sorting methods...


    public long selectionSort(Record[] array, int length, PrintWriter writer) {
        long ctr = 1; // for the outer for loop initialization
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            ctr++; // for the minIndex initialization
            ctr++; // for the inner for loop initialization
            for (int j = i + 1; j < length; j++) {
                ctr++; // for the inner for loop condition
                if (array[j].getIdNumber() < array[minIndex].getIdNumber()) {
                    minIndex = j;
                }
            }
            swap(array, minIndex, i);
            ctr++; // for the swap operation
            writer.println("Iteration " + (i + 1) + ":");
            for (Record r : array) {
                writer.println(r.getIdNumber() + " " + r.getName());
            }
            writer.println();
        }
        return ctr;
    }

    public long mergeSort(Record[] array, int start, int end, PrintWriter writer) {
        long ctr = 1; // for the outer if condition
        if (start < end) {
            ctr++; // for the mid assignment
            int mid = (start + end) / 2;

            ctr++; // for the first recursive call
            ctr += mergeSort(array, start, mid, writer);

            ctr++; // for the second recursive call
            ctr += mergeSort(array, mid + 1, end, writer);

            ctr += merge(array, start, mid, end, writer);
        }
        return ctr;
    }

    public long merge(Record[] array, int start, int mid, int end, PrintWriter writer) {
        long ctr = 0; // counter for merge process
        int n1 = mid - start + 1;
        int n2 = end - mid;

        /* Create temp arrays */
        Record[] L = new Record[n1];
        Record[] R = new Record[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = array[start + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[mid + 1 + j];

        /* Merge the temp arrays */
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
            ctr++; // for each iteration of the while loop
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
            ctr++; // for each iteration of the while loop
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
            ctr++; // for each iteration of the while loop
        }

        writer.println("After merge, Iteration " + ctr + ":");
        for (Record r : array) {
            writer.println(r.getIdNumber() + " " + r.getName());
        }
        writer.println();

        return ctr;
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

            writer.println("Iteration " + ctr + ":");
            for (Record r : array) {
                writer.println(r.getIdNumber() + " " + r.getName());
            }
            writer.println();
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
}
