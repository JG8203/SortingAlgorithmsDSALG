package com.dlsu;
public class SortingAlgorithms {

    private static void swap(Record[] array, int firstIndex, int secondIndex) {
        Record temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    // Insertion sort algorithm
    public long insertionSort(Record[] array, int length) {
        long ctr = 1; // for the outer for loop initialization
        for (int i = 1; i < length; ++i) {
            Record key = array[i]; ctr++; // for the key assignment
            int j = i - 1; ctr++;
            ctr++; // for the j initialization
            while (j >= 0 && array[j].getIdNumber() > key.getIdNumber()) {
                ctr++; // for the while loop condition
                array[j + 1] = array[j]; ctr++;
                j--; ctr++;
            }
            array[j + 1] = key; ctr++;
            ctr++; // loop check
        }
        return ctr;
    }

    // Selection sort algorithm
    public long selectionSort(Record[] array, int length) {
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
        }
        return ctr;
    }

    // Merge sort algorithm
    public long mergeSort(Record[] array, int start, int end) {
        long ctr = 1; // for the outer if condition
        if (start < end) {
            ctr++; // for the mid assignment
            int mid = (start + end) / 2;
            ctr++; // for the first recursive call
            ctr += mergeSort(array, start, mid);
            ctr++; // for the second recursive call
            ctr += mergeSort(array, mid + 1, end);

            ctr++; // for the leftArraySize assignment
            int leftArraySize = mid - start + 1;
            ctr++; // for the rightArraySize assignment
            int rightArraySize = end - mid;

            ctr++; // for the leftArray initialization
            Record[] leftArray = new Record[leftArraySize];
            ctr++; // for the rightArray initialization
            Record[] rightArray = new Record[rightArraySize];

            ctr++; // for the leftArray assignment
            System.arraycopy(array, start, leftArray, 0, leftArraySize);
            ctr++; // for the rightArray assignment
            System.arraycopy(array, mid + 1, rightArray, 0, rightArraySize);

            ctr++; // for the k initialization
            int k = start;

            ctr++; // for the inner for loop initialization
            for (int i = 0, j = 0; (ctr += 2) > 0 && i < leftArraySize && j < rightArraySize; k++) {
                ctr++; // for the if condition
                if (leftArray[i].getIdNumber() <= rightArray[j].getIdNumber()) {
                    array[k] = leftArray[i++];
                } else {
                    array[k] = rightArray[j++];
                }
            }

            ctr++; // for the final failing inner loop condition check
            for (int i = k - start; i < leftArraySize; i++, k++) {
                array[k] = leftArray[i];
                ctr++; // for the assignment operation
            }

            ctr++; // for the final failing inner loop condition check
            for (int j = k - (mid + 1); j < rightArraySize; j++, k++) {
                array[k] = rightArray[j];
                ctr++; // for the assignment operation
            }
        }
        return ctr;
    }

    private static boolean isSorted(Record[] array, int length) {
        for (int i = 1; i < length; i++) {
            if (array[i].getIdNumber() < array[i - 1].getIdNumber()) {
                return false;
            }
        }
        return true;
    }

    // Bogo sort algorithm
    public static long bogoSort(Record[] array, int length) {
        long ctr = 1; // for the outer while loop initialization
        ctr++; // for the while loop condition
        while (!isSorted(array, length)) {
            for (int i = 0; i < length; i++) {
                int randomIndex = (int) (Math.random() * (i + 1));
                ctr++; // for the randomIndex assignment
                swap(array, i, randomIndex);
                ctr++; // for the swap operation
            }
            ctr++; // for the while loop condition
        }
        return ctr;
    }
}
