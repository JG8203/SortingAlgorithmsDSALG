import java.util.Arrays;
import java.util.function.Supplier;

public class Main {
    private static final String[] FILE_NAMES = {"10.txt", "random100.txt", "random25000.txt", "random50000.txt",
            "random75000.txt", "random100000.txt", "totallyreversed.txt", "almostsorted.txt"};

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

        performAndTimeSort("Insertion Sort", () -> sortingAlgorithms.insertionSort(Arrays.copyOf(records, n), n));
        performAndTimeSort("Selection Sort", () -> sortingAlgorithms.selectionSort(Arrays.copyOf(records, n), n));
        performAndTimeSort("Merge Sort", () -> sortingAlgorithms.mergeSort(Arrays.copyOf(records, n), 0, n-1));
    }

    private static void performAndTimeSort(String algorithmName, Supplier<Long> sortAction) {
        double totalExecutionTime = 0;
        for (int i = 0; i < 5; i++) {
            long startTime = System.nanoTime();
            long frequencyCount = sortAction.get();
            long executionTime = System.nanoTime() - startTime;
            totalExecutionTime += executionTime;
            System.out.println((i + 1) + ". " + algorithmName + " took " + executionTime / 1e6 + " ms and had " + frequencyCount + " operations");
        }
        double averageExecutionTime = totalExecutionTime / 5;
        System.out.println(algorithmName + " Average Execution time: " + averageExecutionTime / 1e6 + " ms");
        System.out.println();
    }
}
