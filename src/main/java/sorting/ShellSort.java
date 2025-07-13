package sorting;

public class ShellSort {
    public void shellSort(int[] arr) {
       int i, j, h, v;
       for(h = 1; h <= arr.length / 9; h = 3 * h + 1); // Calculate initial gap
        for(; h> 0; h /= 3) { // Reduce gap
            for(i = h; i < arr.length; i++) {
                v = arr[i]; // Element to be inserted
                j = i; // Position to insert the element
                while(j >= h && arr[j - h] > v) { // Shift elements to the right
                    arr[j] = arr[j - h];
                    j -= h;
                }
                arr[j] = v; // Place the element in its correct position
            }
        }
    }
}
