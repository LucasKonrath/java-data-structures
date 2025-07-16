package sorting;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.shuffle;

public class BogoSort {
    public void bogoSort(List<Integer> list) {
        while(!isSorted(list)) {
            shuffle(list); // Randomly shuffle the list until it is sorted
        }
    }

    boolean isSorted(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if(list.get(i) > list.get(i + 1)) {
                return false; // If any element is greater than the next, the array is not sorted
            }
        }
        return true;
    }
}
