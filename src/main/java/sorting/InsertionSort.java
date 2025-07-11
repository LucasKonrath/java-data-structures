package sorting;

public class InsertionSort {
    public void insertionSort(int[] arr){
        int i, j, key;

        for(i = 1; i < arr.length; i++){
            key = arr[i];
            j = i;
            while(j >= 1 && arr[j - 1] > key){
                arr[j] = arr[j - 1]; // Shift elements to the right
                j--;
            }
            arr[j] = key; // Place the key in its correct position
        }
    }
}
