package searching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderedLinearSearchTest {

    private OrderedLinearSearch search;

    @BeforeEach
    void setUp() {
        search = new OrderedLinearSearch();
    }

    @Test
    void testSearchEmptyArray() {
        int[] input = {};
        int target = 5;

        int result = search.search(input, target);

        assertEquals(-1, result, "Should return -1 for empty array");
    }

    @Test
    void testSearchSingleElementFound() {
        int[] input = {42};
        int target = 42;

        int result = search.search(input, target);

        assertEquals(0, result, "Should return index 0 when target is the only element");
    }

    @Test
    void testSearchSingleElementNotFound() {
        int[] input = {42};
        int target = 10;

        int result = search.search(input, target);

        assertEquals(-1, result, "Should return -1 when target is not found");
    }

    @Test
    void testSearchTargetAtBeginning() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 10;

        int result = search.search(input, target);

        assertEquals(0, result, "Should return index 0 when target is at beginning");
    }

    @Test
    void testSearchTargetAtEnd() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 50;

        int result = search.search(input, target);

        assertEquals(4, result, "Should return last index when target is at end");
    }

    @Test
    void testSearchTargetInMiddle() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 30;

        int result = search.search(input, target);

        assertEquals(2, result, "Should return correct index when target is in middle");
    }

    @Test
    void testSearchTargetNotFoundEarlyExit() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 25; // Between 20 and 30, should exit early

        int result = search.search(input, target);

        assertEquals(-1, result, "Should return -1 and exit early when target is between elements");
    }

    @Test
    void testSearchTargetSmallerThanAll() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 5; // Smaller than all elements

        int result = search.search(input, target);

        assertEquals(-1, result, "Should return -1 when target is smaller than all elements");
    }

    @Test
    void testSearchTargetLargerThanAll() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 99; // Larger than all elements

        int result = search.search(input, target);

        assertEquals(-1, result, "Should return -1 when target is larger than all elements");
    }

    @Test
    void testSearchWithDuplicates() {
        int[] input = {1, 3, 5, 5, 5, 8, 10}; // Multiple 5s
        int target = 5;

        int result = search.search(input, target);

        assertEquals(2, result, "Should return first occurrence index when duplicates exist");
    }

    @Test
    void testSearchWithNegativeNumbers() {
        int[] input = {-10, -5, 0, 5, 10}; // Sorted with negatives
        int target = -5;

        int result = search.search(input, target);

        assertEquals(1, result, "Should handle negative numbers correctly");
    }

    @Test
    void testSearchZero() {
        int[] input = {-5, -1, 0, 1, 5};
        int target = 0;

        int result = search.search(input, target);

        assertEquals(2, result, "Should find zero correctly");
    }

    @Test
    void testSearchEarlyExitOptimization() {
        int[] input = {1, 5, 10, 15, 20, 25, 30, 35, 40}; // Large sorted array
        int target = 7; // Between 5 and 10

        int result = search.search(input, target);

        assertEquals(-1, result, "Should exit early when encountering element greater than target");
    }

    @Test
    void testSearchAllElementsChecked() {
        int[] input = {2, 4, 6, 8, 10};
        int target = 12; // Larger than all elements

        int result = search.search(input, target);

        assertEquals(-1, result, "Should check all elements when target is larger than all");
    }

    @Test
    void testSearchLargeOrderedArray() {
        int[] input = new int[1000];
        for (int i = 0; i < 1000; i++) {
            input[i] = i * 2; // Even numbers 0, 2, 4, 6, ...
        }
        int target = 998; // This should be at index 499

        int result = search.search(input, target);

        assertEquals(499, result, "Should handle large ordered arrays correctly");
    }

    @Test
    void testSearchLargeOrderedArrayEarlyExit() {
        int[] input = new int[1000];
        for (int i = 0; i < 1000; i++) {
            input[i] = i * 2; // Even numbers 0, 2, 4, 6, ...
        }
        int target = 5; // Odd number, should exit early

        int result = search.search(input, target);

        assertEquals(-1, result, "Should exit early in large array when target doesn't exist");
    }

    @Test
    void testSearchConsecutiveNumbers() {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 6;

        int result = search.search(input, target);

        assertEquals(5, result, "Should work correctly with consecutive numbers");
    }

    @Test
    void testSearchSparseArray() {
        int[] input = {10, 50, 100, 500, 1000};
        int target = 75; // Between 50 and 100

        int result = search.search(input, target);

        assertEquals(-1, result, "Should handle sparse arrays with early exit optimization");
    }
}
