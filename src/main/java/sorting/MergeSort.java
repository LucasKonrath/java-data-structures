package sorting;

public class MergeSort {
    public void sort(int[] arr) {
        if (arr.length < 2) {
            return; // Base case: array is already sorted
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        // Split the array into two halves
        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = mid; i < arr.length; i++) {
            right[i - mid] = arr[i];
        }

        // Recursively sort both halves
        sort(left);
        sort(right);

        // Merge the sorted halves
        merge(arr, left, right);
    }

    public void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Merge the two halves into the original array
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        // Copy any remaining elements from the left half
        while (i < left.length) {
            arr[k++] = left[i++];
        }

        // Copy any remaining elements from the right half
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
}
