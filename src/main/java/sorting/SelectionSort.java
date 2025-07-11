package sorting;

public class SelectionSort {
    public void selectionSort(int[] arr){
        int i, j, min, temp;

        for(i = 0; i < arr.length - 1; i++){
            min = i; // Assume the minimum is the first element of the unsorted part
            for(j = i + 1; j < arr.length; j++){
                if(arr[j] < arr[min]){
                    min = j; // Update the index of the minimum element
                }
            }
            temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }
}
