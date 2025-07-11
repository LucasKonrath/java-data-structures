package sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class SelectionSortTest {

    private SelectionSort selectionSort;

    @BeforeEach
    void setUp() {
        selectionSort = new SelectionSort();
    }

    @Test
    void testSortEmptyArray() {
        int[] input = {};
        int[] expected = {};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortSingleElement() {
        int[] input = {5};
        int[] expected = {5};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortTwoElementsInOrder() {
        int[] input = {1, 2};
        int[] expected = {1, 2};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortTwoElementsReversed() {
        int[] input = {2, 1};
        int[] expected = {1, 2};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortAlreadySortedArray() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortReverseSortedArray() {
        int[] input = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortRandomArray() {
        int[] input = {64, 34, 25, 12, 22, 11, 90};
        int[] expected = {11, 12, 22, 25, 34, 64, 90};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayWithDuplicates() {
        int[] input = {5, 2, 8, 2, 9, 1, 5, 4};
        int[] expected = {1, 2, 2, 4, 5, 5, 8, 9};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayWithAllSameElements() {
        int[] input = {7, 7, 7, 7, 7};
        int[] expected = {7, 7, 7, 7, 7};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayWithNegativeNumbers() {
        int[] input = {-3, 1, -7, 4, -2};
        int[] expected = {-7, -3, -2, 1, 4};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayWithMixedPositiveNegative() {
        int[] input = {-5, 10, -3, 8, 0, -1, 6};
        int[] expected = {-5, -3, -1, 0, 6, 8, 10};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayWithZero() {
        int[] input = {3, 0, -1, 5, 0, 2};
        int[] expected = {-1, 0, 0, 2, 3, 5};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortLargeArray() {
        int[] input = new int[100];
        for (int i = 0; i < 100; i++) {
            input[i] = 100 - i; // Reverse sorted
        }

        selectionSort.selectionSort(input);

        // Verify it's sorted
        for (int i = 0; i < 99; i++) {
            assertTrue(input[i] <= input[i + 1],
                "Array not sorted at position " + i + ": " + input[i] + " > " + input[i + 1]);
        }
    }

    @Test
    void testSortRandomLargeArray() {
        Random random = new Random(42); // Fixed seed for reproducible tests
        int[] input = new int[50];
        for (int i = 0; i < 50; i++) {
            input[i] = random.nextInt(1000);
        }

        int[] expected = input.clone();
        Arrays.sort(expected); // Use Java's built-in sort for expected result

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayWithMaxMinValues() {
        int[] input = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 1, -1};
        int[] expected = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortStabilityNote() {
        // Note: Selection sort is NOT stable, but we test that equal elements are sorted correctly
        int[] input = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 6, 9};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayDoesNotModifyOriginalReference() {
        int[] input = {3, 1, 4, 1, 5};
        int[] originalReference = input;

        selectionSort.selectionSort(input);

        // The reference should be the same (in-place sorting)
        assertSame(originalReference, input);

        // But the contents should be sorted
        int[] expected = {1, 1, 3, 4, 5};
        assertArrayEquals(expected, input);
    }

    @Test
    void testSortWorstCaseScenario() {
        // For selection sort, the worst case is O(n²) regardless of input order
        int[] input = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortBestCaseScenario() {
        // For selection sort, even best case (already sorted) is O(n²)
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortAlternatingPattern() {
        int[] input = {1, 10, 2, 9, 3, 8, 4, 7, 5, 6};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayVerifyInPlaceSorting() {
        int[] input = {5, 2, 8, 1, 9};
        int originalLength = input.length;

        selectionSort.selectionSort(input);

        // Verify array length hasn't changed (in-place sorting)
        assertEquals(originalLength, input.length);

        // Verify it's sorted
        for (int i = 0; i < input.length - 1; i++) {
            assertTrue(input[i] <= input[i + 1]);
        }
    }

    @Test
    void testMultipleSortCallsOnSameArray() {
        int[] input = {3, 1, 4, 1, 5};
        int[] expected = {1, 1, 3, 4, 5};

        // Sort multiple times
        selectionSort.selectionSort(input);
        selectionSort.selectionSort(input);
        selectionSort.selectionSort(input);

        // Should still be correctly sorted
        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayWithRepeatedSubsequences() {
        int[] input = {3, 1, 4, 3, 1, 4, 3, 1, 4};
        int[] expected = {1, 1, 1, 3, 3, 3, 4, 4, 4};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortMinimumSelectionBehavior() {
        // Test that selection sort correctly finds minimum in each pass
        int[] input = {5, 1, 4, 2, 8};

        selectionSort.selectionSort(input);

        // After sorting, verify the result
        int[] expected = {1, 2, 4, 5, 8};
        assertArrayEquals(expected, input);
    }

    @Test
    void testSortWithLargeRange() {
        int[] input = {1000, 1, 500, 250, 750, 100, 900};
        int[] expected = {1, 100, 250, 500, 750, 900, 1000};

        selectionSort.selectionSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortConsistentBehavior() {
        // Test that selection sort produces consistent results
        int[] input1 = {4, 2, 7, 1, 3};
        int[] input2 = {4, 2, 7, 1, 3};

        selectionSort.selectionSort(input1);
        selectionSort.selectionSort(input2);

        assertArrayEquals(input1, input2);
    }
}
