public class SortingAlgorithms {

    private void    swap(Record[] arr, int i, int j) {
        Record temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void insertionSort(Record[] arr, int n) {
        for (int i = 1; i < n; ++i) {
            Record key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].getIdNumber() > key.getIdNumber()) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public void selectionSort(Record[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j].getIdNumber() < arr[min_idx].getIdNumber())
                    min_idx = j;

            swap(arr, min_idx, i);
        }
    }

    private void merge(Record[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        Record[] L = new Record[n1];
        Record[] R = new Record[n2];

        System.arraycopy(arr, p, L, 0, n1);
        System.arraycopy(arr, q + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = p;

        while (i < n1 && j < n2) {
            if (L[i].getIdNumber() <= R[j].getIdNumber()) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public void mergeSort(Record[] arr, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    // For the extra algorithm, let's use quicksort
    private int partition(Record arr[], int low, int high) {
        int pivot = arr[high].getIdNumber();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].getIdNumber() < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;

    }

    private boolean isSorted(Record[] arr, int n) {
        for (int i = 1; i < n; i++) {
            if (arr[i].getIdNumber() < arr[i - 1].getIdNumber()) {
                return false;
            }
        }
        return true;
    }

    public void bogoSort(Record[] arr, int n) {
        while (!isSorted(arr, n)) {
            for (int i = 0; i < n; i++) {
                int r = (int) (Math.random() * (i + 1));
                swap(arr, i, r);
            }
        }
    }
}
