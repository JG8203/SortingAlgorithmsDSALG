package com.dlsu;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.io.*;

public class Main {
    private static final String[] FILE_NAMES = {"almostsorted10.txt","totallyreversed10.txt", "random5.txt", "random10.txt","random15.txt","random100.txt"};

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        SortingAlgorithms sortingAlgorithms = new SortingAlgorithms();

        for (String fileName : FILE_NAMES) {
            processFile(fileName, fileReader, sortingAlgorithms);
        }
    }

    private static void processFile(String fileName, FileReader fileReader, SortingAlgorithms sortingAlgorithms) {
        System.out.println("\nProcessing file: " + fileName);
        Record[] records = fileReader.readFile(fileName);
        int n = records.length;

        File dir = new File(fileName.replace(".txt", ""));
        dir.mkdirs();

        performAndTimeSort("Insertion Sort", (recordsCopy, writer) -> sortingAlgorithms.insertionSort(recordsCopy, n, writer), records, dir);
        performAndTimeSort("Selection Sort", (recordsCopy, writer) -> sortingAlgorithms.selectionSort(recordsCopy, n, writer), records, dir);
        performAndTimeSort("Merge Sort", (recordsCopy, writer) -> sortingAlgorithms.mergeSort(recordsCopy, 0, n-1, writer), records, dir);
        // performAndTimeSort("Bogo Sort", (recordsCopy, writer) -> SortingAlgorithms.bogoSort(recordsCopy, n, writer), records, dir);
    }

    private static void performAndTimeSort(String algorithmName, BiFunction<Record[], PrintWriter, Long> sortAction, Record[] records, File dir) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(dir, algorithmName + ".txt"), false))) {
            double totalExecutionTime = 0;
            long totalFrequencyCount = 0;
            for (int i = 0; i < 5; i++) {
                Record[] recordsCopy = Arrays.copyOf(records, records.length);
                long startTime = System.nanoTime();
                long frequencyCount = sortAction.apply(recordsCopy, writer);
                long executionTime = System.nanoTime() - startTime;
                totalExecutionTime += executionTime;
                totalFrequencyCount += frequencyCount;
                System.out.println((i + 1) + ". " + algorithmName + " took " + executionTime / 1e6 + " ms");
            }
            double averageExecutionTime = totalExecutionTime / 5;
            double averageFrequencyCount = totalFrequencyCount / 5;
            System.out.println(algorithmName + " Average Execution time: " + averageExecutionTime / 1e6 + " ms and Average Number of Operations: " + averageFrequencyCount);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
