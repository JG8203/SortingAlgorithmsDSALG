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
            long averageExecutionTime = 0;
            long startTime, endTime;
            Record[] recordsCopy;
            // Perform and time the sorting algorithms

            // Insertion Sort
            for(int i = 0; i < 5; i++)
            {
                recordsCopy = Arrays.copyOf(records, n);
                startTime = System.currentTimeMillis();
                sortingAlgorithms.insertionSort(recordsCopy, n);
                endTime = System.currentTimeMillis();
                System.out.println((i+1) + " Insertion Sort took " + (endTime - startTime) + " ms");
                averageExecutionTime =  averageExecutionTime + (endTime - startTime);
            }
            System.out.println("Insertion Sort Average Execution time: " + averageExecutionTime/ 5  + " ms");
            System.out.println(" ");
            // Selection Sort
            averageExecutionTime =  0;
            for(int i = 0; i < 5; i++)
            {
                recordsCopy = Arrays.copyOf(records, n);
                startTime = System.currentTimeMillis();
                sortingAlgorithms.selectionSort(recordsCopy, n);
                endTime = System.currentTimeMillis();
                System.out.println((i+1) + " Selection Sort took " + (endTime - startTime) + " ms");
                averageExecutionTime =  averageExecutionTime + (endTime-startTime);
            }
            System.out.println("Selection Sort Average Execution time: " + averageExecutionTime/ 5  + " ms");
            System.out.println(" ");
            // Merge Sort
            averageExecutionTime =  0;
            for(int i = 0; i < 5; i++)
            {
                recordsCopy = Arrays.copyOf(records, n);
                startTime = System.currentTimeMillis();
                sortingAlgorithms.mergeSort(recordsCopy, 0, n-1);
                endTime = System.currentTimeMillis();
                System.out.println((i+1) + " Merge Sort took " + (endTime - startTime) + " ms");
                averageExecutionTime =  averageExecutionTime + (endTime-startTime);
            }
            System.out.println("Merge Sort Average Execution time: " + averageExecutionTime/ 5  + " ms");
            // Bogo Sort
         /*   recordsCopy = Arrays.copyOf(records, n);
            startTime = System.currentTimeMillis();
            sortingAlgorithms.bogoSort(recordsCopy, n);
            endTime = System.currentTimeMillis();
            System.out.println("Bogo Sort took " + (endTime - startTime) + " ms");
          */
        }
    }
}
