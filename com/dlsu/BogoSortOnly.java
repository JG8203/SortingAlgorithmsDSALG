package com.dlsu;

import java.util.Arrays;

public class BogoSortOnly {
    private static final String[] FILE_NAMES = {
            "almostsorted10.txt",
            "totallyreversed10.txt",
            "random5.txt",
            "random10.txt",
            "random15.txt",
            "random100.txt"
    };

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        SortingAlgorithms2 sortingAlgorithms = new SortingAlgorithms2();

        for (String fileName : FILE_NAMES) {
            processFile(fileName, fileReader, sortingAlgorithms);
        }
    }

    private static void processFile(String fileName, FileReader fileReader, SortingAlgorithms2 sortingAlgorithms) {
        System.out.println("\nProcessing file: " + fileName);
        Record[] records = fileReader.readFile(fileName);
        int n = records.length;

        Record[] sortedArray = SortingAlgorithms2.bogoSort(Arrays.copyOf(records, n), n);
        printSortedArray(sortedArray);
    }

    private static void printSortedArray(Record[] sortedArray) {
        System.out.println("Sorted array:");
        for (Record record : sortedArray) {
            System.out.println(record.getIdNumber());
        }
    }
}
