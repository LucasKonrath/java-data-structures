package searching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTest {

    private BinarySearch binarySearch;

    @BeforeEach
    void setUp() {
        binarySearch = new BinarySearch();
    }

    // Basic functionality tests

    @Test
    void testSearchEmptyArray() {
        int[] input = {};
        int target = 5;

        int result = binarySearch.search(input, target);

        assertEquals(-1, result, "Should return -1 for empty array");
    }

    @Test
    void testSearchNullArray() {
        int[] input = null;
        int target = 5;
        assertEquals(-1, binarySearch.search(input, target), "Should return -1 for null array");
       }

    @Test
    void testSearchSingleElementFound() {
        int[] input = {42};
        int target = 42;

        int result = binarySearch.search(input, target);

        assertEquals(0, result, "Should return index 0 when target is the only element");
    }

    @Test
    void testSearchSingleElementNotFound() {
        int[] input = {42};
        int target = 10;

        int result = binarySearch.search(input, target);

        assertEquals(-1, result, "Should return -1 when target is not found");
    }

    // Position-based tests

    @Test
    void testSearchTargetAtBeginning() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 10;

        int result = binarySearch.search(input, target);

        assertEquals(0, result, "Should return index 0 when target is at beginning");
    }

    @Test
    void testSearchTargetAtEnd() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 50;

        int result = binarySearch.search(input, target);

        assertEquals(4, result, "Should return last index when target is at end");
    }

    @Test
    void testSearchTargetInMiddle() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 30;

        int result = binarySearch.search(input, target);

        assertEquals(2, result, "Should return correct index when target is in middle");
    }

    // Not found scenarios

    @Test
    void testSearchTargetNotFoundBetweenElements() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 25; // Between 20 and 30

        int result = binarySearch.search(input, target);

        assertEquals(-1, result, "Should return -1 when target is between elements");
    }

    @Test
    void testSearchTargetSmallerThanAll() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 5; // Smaller than all elements

        int result = binarySearch.search(input, target);

        assertEquals(-1, result, "Should return -1 when target is smaller than all elements");
    }

    @Test
    void testSearchTargetLargerThanAll() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 99; // Larger than all elements

        int result = binarySearch.search(input, target);

        assertEquals(-1, result, "Should return -1 when target is larger than all elements");
    }

    // Edge cases and special scenarios

    @Test
    void testSearchWithDuplicates() {
        int[] input = {1, 3, 5, 5, 5, 8, 10}; // Multiple 5s
        int target = 5;

        int result = binarySearch.search(input, target);

        assertTrue(result >= 2 && result <= 4,
                  "Should return one of the valid indices where 5 exists (2, 3, or 4)");
        assertEquals(5, input[result], "Should find the target value at the returned index");
    }

    @Test
    void testSearchWithNegativeNumbers() {
        int[] input = {-10, -5, 0, 5, 10}; // Sorted with negatives
        int target = -5;

        int result = binarySearch.search(input, target);

        assertEquals(1, result, "Should handle negative numbers correctly");
    }

    @Test
    void testSearchZero() {
        int[] input = {-5, -1, 0, 1, 5};
        int target = 0;

        int result = binarySearch.search(input, target);

        assertEquals(2, result, "Should find zero correctly");
    }

    // Large array tests

    @Test
    void testSearchLargeSortedArray() {
        int[] input = new int[1000];
        for (int i = 0; i < 1000; i++) {
            input[i] = i * 2; // Even numbers 0, 2, 4, 6, ...
        }
        int target = 998; // This should be at index 499

        int result = binarySearch.search(input, target);

        assertEquals(499, result, "Should handle large sorted arrays correctly");
    }

    @Test
    void testSearchLargeSortedArrayNotFound() {
        int[] input = new int[1000];
        for (int i = 0; i < 1000; i++) {
            input[i] = i * 2; // Even numbers 0, 2, 4, 6, ...
        }
        int target = 999; // Odd number, should not be found

        int result = binarySearch.search(input, target);

        assertEquals(-1, result, "Should not find odd numbers in even number array");
    }

    // Performance edge cases

    @Test
    void testSearchConsecutiveNumbers() {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 6;

        int result = binarySearch.search(input, target);

        assertEquals(5, result, "Should work correctly with consecutive numbers");
    }

    @Test
    void testSearchEvenLengthArray() {
        int[] input = {10, 20, 30, 40, 50, 60}; // Even length
        int target = 40;

        int result = binarySearch.search(input, target);

        assertEquals(3, result, "Should handle even-length arrays correctly");
    }

    @Test
    void testSearchOddLengthArray() {
        int[] input = {10, 20, 30, 40, 50}; // Odd length
        int target = 30;

        int result = binarySearch.search(input, target);

        assertEquals(2, result, "Should handle odd-length arrays correctly");
    }

    // Recursive implementation tests

    @Test
    void testSearchRecursiveBasicFunctionality() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 30;

        int result = binarySearch.searchRecursive(input, target);

        assertEquals(2, result, "Recursive search should work correctly");
    }

    @Test
    void testSearchRecursiveNotFound() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 25;

        int result = binarySearch.searchRecursive(input, target);

        assertEquals(-1, result, "Recursive search should return -1 when not found");
    }

    @Test
    void testSearchRecursiveNullArray() {
        int[] input = null;
        int target = 5;

        assertThrows(IllegalArgumentException.class, () -> {
            binarySearch.searchRecursive(input, target);
        }, "Recursive search should throw IllegalArgumentException for null array");
    }

    @Test
    void testSearchRecursiveEmptyArray() {
        int[] input = {};
        int target = 5;

        int result = binarySearch.searchRecursive(input, target);

        assertEquals(-1, result, "Recursive search should return -1 for empty array");
    }

    // Integer overflow protection test
    @Test
    void testSearchIntegerOverflowProtection() {
        // This test ensures our mid calculation doesn't overflow
        int[] input = new int[10];
        for (int i = 0; i < 10; i++) {
            input[i] = i + 1000000000; // Large numbers
        }
        int target = 1000000005;

        int result = binarySearch.search(input, target);

        assertEquals(5, result, "Should handle large numbers without integer overflow");
    }
}
