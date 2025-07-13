package sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class ShellSortTest {

    private ShellSort shellSort;

    @BeforeEach
    void setUp() {
        shellSort = new ShellSort();
    }

    @Test
    void testSortEmptyArray() {
        int[] input = {};
        int[] expected = {};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortSingleElement() {
        int[] input = {5};
        int[] expected = {5};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortTwoElementsAlreadySorted() {
        int[] input = {1, 2};
        int[] expected = {1, 2};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortTwoElementsReversed() {
        int[] input = {2, 1};
        int[] expected = {1, 2};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortAlreadySortedArray() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortReverseSortedArray() {
        int[] input = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortWithDuplicates() {
        int[] input = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortAllSameElements() {
        int[] input = {7, 7, 7, 7, 7};
        int[] expected = {7, 7, 7, 7, 7};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortNegativeNumbers() {
        int[] input = {-3, -1, -4, -1, -5};
        int[] expected = {-5, -4, -3, -1, -1};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortMixedPositiveNegative() {
        int[] input = {-2, 3, -1, 0, 4, -5};
        int[] expected = {-5, -2, -1, 0, 3, 4};

        shellSort.shellSort(input);

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

        shellSort.shellSort(input);

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

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortRandomOrder() {
        int[] input = {64, 34, 25, 12, 22, 11, 90};
        int[] expected = {11, 12, 22, 25, 34, 64, 90};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortWithZeros() {
        int[] input = {0, 5, 0, 3, 0, 1};
        int[] expected = {0, 0, 0, 1, 3, 5};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortMaxMinValues() {
        int[] input = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 100, -100};
        int[] expected = {Integer.MIN_VALUE, -100, 0, 100, Integer.MAX_VALUE};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortDoesNotModifyOriginalWhenCopied() {
        int[] original = {3, 1, 4, 1, 5};
        int[] input = original.clone();
        int[] expected = {1, 1, 3, 4, 5};

        shellSort.shellSort(input);

        // Original should remain unchanged
        assertArrayEquals(new int[]{3, 1, 4, 1, 5}, original);
        // Input should be sorted
        assertArrayEquals(expected, input);
    }

    @Test
    void testSortThreeElements() {
        int[] input = {3, 1, 2};
        int[] expected = {1, 2, 3};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortOddNumberOfElements() {
        int[] input = {9, 1, 8, 2, 7, 3, 6, 4, 5};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortEvenNumberOfElements() {
        int[] input = {8, 1, 7, 2, 6, 3, 5, 4};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortStability() {
        // Note: Shell sort is not stable, but we test that equal elements
        // are still present after sorting
        int[] input = {5, 2, 8, 2, 9, 1, 2};
        int[] expected = {1, 2, 2, 2, 5, 8, 9};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortPerformanceComparison() {
        // Test to ensure Shell sort performs reasonably on different input sizes
        int[] smallArray = generateRandomArray(10, 42);
        int[] mediumArray = generateRandomArray(100, 42);
        int[] largeArray = generateRandomArray(1000, 42);

        // Test that all arrays get sorted correctly
        int[] smallExpected = smallArray.clone();
        int[] mediumExpected = mediumArray.clone();
        int[] largeExpected = largeArray.clone();

        Arrays.sort(smallExpected);
        Arrays.sort(mediumExpected);
        Arrays.sort(largeExpected);

        shellSort.shellSort(smallArray);
        shellSort.shellSort(mediumArray);
        shellSort.shellSort(largeArray);

        assertArrayEquals(smallExpected, smallArray);
        assertArrayEquals(mediumExpected, mediumArray);
        assertArrayEquals(largeExpected, largeArray);
    }

    @Test
    void testSortWithSpecificPattern() {
        // Test Shell sort with a pattern that might expose issues with gap sequence
        int[] input = {16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortNearSortedArray() {
        // Array that's almost sorted (only a few elements out of place)
        int[] input = {1, 2, 3, 5, 4, 6, 7, 8, 10, 9};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testSortPartiallyReversed() {
        int[] input = {1, 2, 3, 4, 10, 9, 8, 7, 6, 5};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        shellSort.shellSort(input);

        assertArrayEquals(expected, input);
    }

    // Helper method to generate random arrays for testing
    private int[] generateRandomArray(int size, long seed) {
        Random random = new Random(seed);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000);
        }
        return array;
    }
}
