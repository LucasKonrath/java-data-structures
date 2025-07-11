package sorting;

public class BubbleSort {
    public void bubbleSort(int[] input){
        for(int pass = 0; pass < input.length - 1; pass++) {
            boolean swapped = false;
            for(int i = 0; i < input.length - 1 - pass; i++) {
                if(input[i] > input[i + 1]) {
                    // Swap the elements
                    int temp = input[i];
                    input[i] = input[i + 1];
                    input[i + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped in the inner loop, then the array is sorted
            if(!swapped) {
                break;
            }
        }
    }
}
