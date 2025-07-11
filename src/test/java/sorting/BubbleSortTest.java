package sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class BubbleSortTest {

    private BubbleSort bubbleSort;

    @BeforeEach
    void setUp() {
        bubbleSort = new BubbleSort();
    }

    @Test
    void testSortEmptyArray() {
        int[] input = {};
        int[] expected = {};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortSingleElement() {
        int[] input = {5};
        int[] expected = {5};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortTwoElementsInOrder() {
        int[] input = {1, 2};
        int[] expected = {1, 2};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortTwoElementsReversed() {
        int[] input = {2, 1};
        int[] expected = {1, 2};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortAlreadySortedArray() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortReverseSortedArray() {
        int[] input = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortRandomArray() {
        int[] input = {64, 34, 25, 12, 22, 11, 90};
        int[] expected = {11, 12, 22, 25, 34, 64, 90};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayWithDuplicates() {
        int[] input = {5, 2, 8, 2, 9, 1, 5, 4};
        int[] expected = {1, 2, 2, 4, 5, 5, 8, 9};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayWithAllSameElements() {
        int[] input = {7, 7, 7, 7, 7};
        int[] expected = {7, 7, 7, 7, 7};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayWithNegativeNumbers() {
        int[] input = {-3, 1, -7, 4, -2};
        int[] expected = {-7, -3, -2, 1, 4};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayWithMixedPositiveNegative() {
        int[] input = {-5, 10, -3, 8, 0, -1, 6};
        int[] expected = {-5, -3, -1, 0, 6, 8, 10};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayWithZero() {
        int[] input = {3, 0, -1, 5, 0, 2};
        int[] expected = {-1, 0, 0, 2, 3, 5};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortLargeArray() {
        int[] input = new int[100];
        for (int i = 0; i < 100; i++) {
            input[i] = 100 - i; // Reverse sorted
        }

        bubbleSort.bubbleSort(input);

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

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayWithMaxMinValues() {
        int[] input = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 1, -1};
        int[] expected = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortStability() {
        // Note: Bubble sort is stable, but we can't easily test this with primitive arrays
        // This test ensures that equal elements maintain their relative order conceptually
        int[] input = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 6, 9};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortPartiallySort() {
        // Array that becomes sorted after a few passes (tests early termination)
        int[] input = {1, 3, 2, 4, 5, 6, 7};
        int[] expected = {1, 2, 3, 4, 5, 6, 7};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayDoesNotModifyOriginalReference() {
        int[] input = {3, 1, 4, 1, 5};
        int[] originalReference = input;

        bubbleSort.bubbleSort(input);

        // The reference should be the same (in-place sorting)
        assertSame(originalReference, input);

        // But the contents should be sorted
        int[] expected = {1, 1, 3, 4, 5};
        assertArrayEquals(expected, input);
    }

    @Test
    void testSortBestCaseScenario() {
        // Already sorted array should terminate early due to optimization
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortWorstCaseScenario() {
        // Reverse sorted array (worst case for bubble sort)
        int[] input = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortAlternatingPattern() {
        int[] input = {1, 10, 2, 9, 3, 8, 4, 7, 5, 6};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayVerifyInPlaceSorting() {
        int[] input = {5, 2, 8, 1, 9};
        int originalLength = input.length;

        bubbleSort.bubbleSort(input);

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
        bubbleSort.bubbleSort(input);
        bubbleSort.bubbleSort(input);
        bubbleSort.bubbleSort(input);

        // Should still be correctly sorted
        assertArrayEquals(expected, input);
    }

    @Test
    void testSortArrayWithRepeatedSubsequences() {
        int[] input = {3, 1, 4, 3, 1, 4, 3, 1, 4};
        int[] expected = {1, 1, 1, 3, 3, 3, 4, 4, 4};

        bubbleSort.bubbleSort(input);

        assertArrayEquals(expected, input);
    }
}
