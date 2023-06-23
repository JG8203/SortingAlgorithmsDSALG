#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX_NAME_LEN 256
#define MAX_LINE_LEN 256

typedef struct record {
    char* name;
    int idNumber;
} Record;

Record** read_file(const char* path, int* n);
void swap(Record** arr, int i, int j);
void insertion_sort(Record** arr, int n);
void selection_sort(Record** arr, int n);
void merge_sort(Record** arr, int p, int r);
void quick_sort(Record** arr, int low, int high);
void merge(Record** arr, int p, int q, int r);
int partition(Record** arr, int low, int high);
void median_of_three(Record** arr, int low, int mid, int high);
void print_sort_time(Record** arr, int n, void (*sortFunc)(Record**, int, int), const char* sortName);

void insertion_sort_wrapper(Record** records, int n, int unused) {
    insertion_sort(records, n);
}

void selection_sort_wrapper(Record** records, int n, int unused) {
    selection_sort(records, n);
}

int main() {
    // Define the dataset file paths
    char* fileNames[] = {"almostsorted.txt", "random100.txt", "random25000.txt", "random50000.txt", "random75000.txt", "random100000.txt", "totallyreversed.txt"};
    int n_files = sizeof(fileNames) / sizeof(fileNames[0]);

    // Iterate over each file
    for (int i = 0; i < n_files; i++) {
        printf("\nProcessing file: %s\n", fileNames[i]);

        // Read the file
        int n = 0;
        Record** records = read_file(fileNames[i], &n);
        if (!records) {
            fprintf(stderr, "File read error.\n");
            continue;
        }

        // Perform and time the sorting algorithms
	print_sort_time(records, n, insertion_sort_wrapper, "Insertion Sort");
	print_sort_time(records, n, selection_sort_wrapper, "Selection Sort");
	print_sort_time(records, n, merge_sort, "Merge Sort");
	print_sort_time(records, n, quick_sort, "Quick Sort");

        for(int j = 0; j < n; j++){
            free(records[j]);
        }
        free(records);
    }
    return 0;
}



Record** copy_records(Record** records, int n) {
    Record** copy = malloc(n * sizeof(Record*));
    for (int i = 0; i < n; i++) {
        copy[i] = malloc(sizeof(Record));
        copy[i]->name = strdup(records[i]->name);
        copy[i]->idNumber = records[i]->idNumber;
    }
    return copy;
}


Record** read_file(const char* path, int* n) {
    FILE* file = fopen(path, "r");
    if (!file) {
        fprintf(stderr, "Cannot open file: %s\n", path);
        return NULL;
    }
    
    fscanf(file, "%d\n", n); // Get the number of records
    Record** records = malloc((*n) * sizeof(Record*));
    
    for (int i = 0; i < *n; i++) {
        records[i] = malloc(sizeof(Record));
        char line[MAX_LINE_LEN];
        fgets(line, MAX_LINE_LEN, file);
        
        records[i]->idNumber = atoi(strtok(line, " "));
        records[i]->name = strdup(strtok(NULL, "\n"));
    }
    
    fclose(file);
    return records;
}

void swap(Record** arr, int i, int j) {
    Record* temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

void insertion_sort(Record** arr, int n) {
    for (int i = 1; i < n; ++i) {
        Record* key = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j]->idNumber > key->idNumber) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}

void selection_sort(Record** arr, int n) {
    for (int i = 0; i < n - 1; i++) {
        int min_idx = i;
        for (int j = i + 1; j < n; j++)
            if (arr[j]->idNumber < arr[min_idx]->idNumber)
                min_idx = j;

        swap(arr, min_idx, i);
    }
}

void merge_sort(Record** arr, int p, int r) {
    if (p < r) {
        int q = (p + r) / 2;
        merge_sort(arr, p, q);
        merge_sort(arr, q + 1, r);
        merge(arr, p, q, r);
    }
}


void quick_sort(Record** arr, int low, int high) {
    if (low < high) {
        int mid = low + (high - low) / 2;
        median_of_three(arr, low, mid, high);

        // Now high contains the index of median of low, mid, high
        // Use this as pivot for partitioning
        int pi = partition(arr, low, high);

        // Recursively sort elements before and after partition
        quick_sort(arr, low, pi - 1);
        quick_sort(arr, pi + 1, high);
    }
}


void merge(Record** arr, int p, int q, int r) {
    int n1 = q - p + 1;
    int n2 = r - q;
    Record** L = malloc(n1 * sizeof(Record*));
    Record** R = malloc(n2 * sizeof(Record*));

    memcpy(L, &arr[p], n1 * sizeof(Record*));
    memcpy(R, &arr[q + 1], n2 * sizeof(Record*));

    int i = 0, j = 0;
    int k = p;

    while (i < n1 && j < n2) {
        if (L[i]->idNumber <= R[j]->idNumber) {
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

    free(L);
    free(R);
}

int partition(Record** arr, int low, int high) {
    int pivot = arr[high]->idNumber;
    int i = (low - 1); 
    for (int j = low; j < high; j++) {
        if (arr[j]->idNumber < pivot) {
            i++;
            swap(arr, i, j);
        }
    }
    swap(arr, i + 1, high);
    return i + 1;
}

void median_of_three(Record** arr, int low, int mid, int high) {
    if ((arr[low]->idNumber < arr[mid]->idNumber && arr[mid]->idNumber < arr[high]->idNumber)
            || (arr[high]->idNumber < arr[mid]->idNumber && arr[mid]->idNumber < arr[low]->idNumber)) {
        swap(arr, mid, high);
    } else if ((arr[mid]->idNumber < arr[low]->idNumber && arr[low]->idNumber < arr[high]->idNumber)
            || (arr[high]->idNumber < arr[low]->idNumber && arr[low]->idNumber < arr[mid]->idNumber)) {
        swap(arr, low, high);
    }
}


void print_sort_time(Record** arr, int n, void (*sortFunc)(Record**, int, int), const char* sortName){
    Record** recordsCopy = copy_records(arr, n);
    clock_t start = clock();
    sortFunc(recordsCopy, n, 0);
    clock_t end = clock();
    printf("%s took %lf ms\n", sortName, ((double) (end - start)) / CLOCKS_PER_SEC * 1000);
    for (int i = 0; i < n; i++) {
        free(recordsCopy[i]->name);
        free(recordsCopy[i]);
    }
    free(recordsCopy);
}
