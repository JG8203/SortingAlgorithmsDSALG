import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Initialize the FileReader and the SortingAlgorithms
        FileReader fileReader = new FileReader();
        SortingAlgorithms sortingAlgorithms = new SortingAlgorithms();
        
        // Define the dataset file paths
        String[] fileNames = {"almostsorted.txt", "random100.txt", "random25000.txt", "random50000.txt", "random75000.txt", "random100000.txt", "totallyreversed.txt"};

        // Iterate over each file
        for (String fileName : fileNames) {
            System.out.println("\nProcessing file: " + fileName);

            // Read the file
            Record[] records = fileReader.readFile(fileName);
            int n = records.length;

            // Perform and time the sorting algorithms

            // Insertion Sort
            Record[] recordsCopy = Arrays.copyOf(records, n);
            long startTime = System.currentTimeMillis();
            sortingAlgorithms.insertionSort(recordsCopy, n);
            long endTime = System.currentTimeMillis();
            System.out.println("Insertion Sort took " + (endTime - startTime) + " ms");

            // Selection Sort
            recordsCopy = Arrays.copyOf(records, n);
            startTime = System.currentTimeMillis();
            sortingAlgorithms.selectionSort(recordsCopy, n);
            endTime = System.currentTimeMillis();
            System.out.println("Selection Sort took " + (endTime - startTime) + " ms");

            // Merge Sort
            recordsCopy = Arrays.copyOf(records, n);
            startTime = System.currentTimeMillis();
            sortingAlgorithms.mergeSort(recordsCopy, 0, n-1);
            endTime = System.currentTimeMillis();
            System.out.println("Merge Sort took " + (endTime - startTime) + " ms");

            // Bogo Sort
            recordsCopy = Arrays.copyOf(records, n);
            startTime = System.currentTimeMillis();
            sortingAlgorithms.bogoSort(recordsCopy, n);
            endTime = System.currentTimeMillis();
            System.out.println("Bogo Sort took " + (endTime - startTime) + " ms");
        }
    }
}
