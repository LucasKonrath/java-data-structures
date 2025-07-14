package sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class MergeSortTest {

    private MergeSort mergeSort;

    @BeforeEach
    void setUp() {
        mergeSort = new MergeSort();
    }

    @Test
    void testSortEmptyArray() {
        int[] input = {};
        int[] expected = {};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortSingleElement() {
        int[] input = {5};
        int[] expected = {5};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortTwoElementsAlreadySorted() {
        int[] input = {1, 2};
        int[] expected = {1, 2};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortTwoElementsReversed() {
        int[] input = {2, 1};
        int[] expected = {1, 2};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortAlreadySortedArray() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortReverseSortedArray() {
        int[] input = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortWithDuplicates() {
        int[] input = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortAllSameElements() {
        int[] input = {7, 7, 7, 7, 7};
        int[] expected = {7, 7, 7, 7, 7};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortNegativeNumbers() {
        int[] input = {-3, -1, -4, -1, -5};
        int[] expected = {-5, -4, -3, -1, -1};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortMixedPositiveNegative() {
        int[] input = {-2, 3, -1, 0, 4, -5};
        int[] expected = {-5, -2, -1, 0, 3, 4};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortLargeArray() {
        int[] input = new int[100];
        Random random = new Random(42); // Fixed seed for reproducible tests

        for (int i = 0; i < input.length; i++) {
            input[i] = random.nextInt(1000);
        }

        int[] expected = input.clone();
        Arrays.sort(expected);

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortVeryLargeArray() {
        int[] input = new int[1000];
        Random random = new Random(123); // Fixed seed for reproducible tests

        for (int i = 0; i < input.length; i++) {
            input[i] = random.nextInt(10000);
        }

        int[] expected = input.clone();
        Arrays.sort(expected);

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortRandomOrder() {
        int[] input = {64, 34, 25, 12, 22, 11, 90};
        int[] expected = {11, 12, 22, 25, 34, 64, 90};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortWithZeros() {
        int[] input = {0, 5, 0, 3, 0, 1};
        int[] expected = {0, 0, 0, 1, 3, 5};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortMaxMinValues() {
        int[] input = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 100, -100};
        int[] expected = {Integer.MIN_VALUE, -100, 0, 100, Integer.MAX_VALUE};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortDoesNotModifyOriginalWhenCopied() {
        int[] original = {3, 1, 4, 1, 5};
        int[] input = original.clone();
        int[] expected = {1, 1, 3, 4, 5};

        mergeSort.sort(input);

        // Original should remain unchanged
        assertArrayEquals(new int[]{3, 1, 4, 1, 5}, original);
        // Input should be sorted
        assertArrayEquals(expected, input);
    }

    @Test
    void testSortThreeElements() {
        int[] input = {3, 1, 2};
        int[] expected = {1, 2, 3};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortOddNumberOfElements() {
        int[] input = {9, 1, 8, 2, 7, 3, 6, 4, 5};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortEvenNumberOfElements() {
        int[] input = {8, 1, 7, 2, 6, 3, 5, 4};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortStability() {
        // Merge sort is stable, so equal elements should maintain relative order
        // Testing with a simple case where stability can be observed
        int[] input = {5, 2, 8, 2, 9, 1, 2};
        int[] expected = {1, 2, 2, 2, 5, 8, 9};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortPowerOfTwoSize() {
        // Test with array sizes that are powers of 2 (common case for merge sort)
        int[] input = {16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortNonPowerOfTwoSize() {
        // Test with array size that's not a power of 2
        int[] input = {7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortNearSortedArray() {
        // Array that's almost sorted (merge sort should handle this efficiently)
        int[] input = {1, 2, 3, 5, 4, 6, 7, 8, 10, 9};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortPartiallyReversed() {
        int[] input = {1, 2, 3, 4, 10, 9, 8, 7, 6, 5};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        mergeSort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testMergeMethod() {
        // Test the merge method directly
        int[] arr = new int[6];
        int[] left = {1, 3, 5};
        int[] right = {2, 4, 6};
        int[] expected = {1, 2, 3, 4, 5, 6};

        mergeSort.merge(arr, left, right);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testMergeMethodWithDifferentSizes() {
        // Test merge with different sized arrays
        int[] arr = new int[5];
        int[] left = {1, 4};
        int[] right = {2, 3, 5};
        int[] expected = {1, 2, 3, 4, 5};

        mergeSort.merge(arr, left, right);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testMergeMethodWithEmptyLeft() {
        int[] arr = new int[3];
        int[] left = {};
        int[] right = {1, 2, 3};
        int[] expected = {1, 2, 3};

        mergeSort.merge(arr, left, right);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testMergeMethodWithEmptyRight() {
        int[] arr = new int[3];
        int[] left = {1, 2, 3};
        int[] right = {};
        int[] expected = {1, 2, 3};

        mergeSort.merge(arr, left, right);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testSortPerformanceComparison() {
        // Test to ensure Merge sort performs well on different input patterns
        int[] randomArray = generateRandomArray(100, 42);
        int[] sortedArray = generateSortedArray(100);
        int[] reversedArray = generateReversedArray(100);

        // Test that all arrays get sorted correctly
        int[] randomExpected = randomArray.clone();
        int[] sortedExpected = sortedArray.clone();
        int[] reversedExpected = reversedArray.clone();

        Arrays.sort(randomExpected);
        Arrays.sort(sortedExpected);
        Arrays.sort(reversedExpected);

        mergeSort.sort(randomArray);
        mergeSort.sort(sortedArray);
        mergeSort.sort(reversedArray);

        assertArrayEquals(randomExpected, randomArray);
        assertArrayEquals(sortedExpected, sortedArray);
        assertArrayEquals(reversedExpected, reversedArray);
    }

    // Helper methods for generating test data
    private int[] generateRandomArray(int size, long seed) {
        Random random = new Random(seed);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000);
        }
        return array;
    }

    private int[] generateSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    private int[] generateReversedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }
}
