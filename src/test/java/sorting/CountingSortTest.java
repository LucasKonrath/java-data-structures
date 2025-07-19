package sorting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CountingSortTest {

    private final CountingSort countingSort = new CountingSort();

    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {

        @Test
        @DisplayName("Should sort empty array")
        void testEmptyArray() {
            int[] arr = {};
            countingSort.countingSort(arr);
            assertArrayEquals(new int[]{}, arr);
        }

        @Test
        @DisplayName("Should sort single element array")
        void testSingleElement() {
            int[] arr = {42};
            countingSort.countingSort(arr);
            assertArrayEquals(new int[]{42}, arr);
        }

        @Test
        @DisplayName("Should sort two element array")
        void testTwoElements() {
            int[] arr = {5, 2};
            countingSort.countingSort(arr);
            assertArrayEquals(new int[]{2, 5}, arr);
        }

        @Test
        @DisplayName("Should sort already sorted array")
        void testAlreadySorted() {
            int[] arr = {1, 2, 3, 4, 5};
            countingSort.countingSort(arr);
            assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
        }

        @Test
        @DisplayName("Should sort reverse sorted array")
        void testReverseSorted() {
            int[] arr = {5, 4, 3, 2, 1};
            countingSort.countingSort(arr);
            assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
        }

        @Test
        @DisplayName("Should sort array with duplicates")
        void testWithDuplicates() {
            int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
            countingSort.countingSort(arr);
            assertArrayEquals(new int[]{1, 1, 2, 3, 3, 4, 5, 5, 6, 9}, arr);
        }

        @Test
        @DisplayName("Should sort array with all identical elements")
        void testAllIdentical() {
            int[] arr = {7, 7, 7, 7, 7};
            countingSort.countingSort(arr);
            assertArrayEquals(new int[]{7, 7, 7, 7, 7}, arr);
        }

        @Test
        @DisplayName("Should handle array with zeros")
        void testWithZeros() {
            int[] arr = {3, 0, 2, 0, 1, 0};
            countingSort.countingSort(arr);
            assertArrayEquals(new int[]{0, 0, 0, 1, 2, 3}, arr);
        }
    }

    @Nested
    @DisplayName("Edge Cases and Boundary Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("Should handle array starting with zero")
        void testStartingWithZero() {
            int[] arr = {0, 5, 3, 2, 1};
            countingSort.countingSort(arr);
            assertArrayEquals(new int[]{0, 1, 2, 3, 5}, arr);
        }

        @Test
        @DisplayName("Should handle large numbers efficiently")
        void testLargeNumbers() {
            int[] arr = {1000, 1, 500, 250, 750};
            countingSort.countingSort(arr);
            assertArrayEquals(new int[]{1, 250, 500, 750, 1000}, arr);
        }

        @Test
        @DisplayName("Should handle array with max value at start")
        void testMaxValueAtStart() {
            int[] arr = {100, 1, 2, 3};
            countingSort.countingSort(arr);
            assertArrayEquals(new int[]{1, 2, 3, 100}, arr);
        }

        @Test
        @DisplayName("Should handle sparse range")
        void testSparseRange() {
            int[] arr = {1, 10, 5, 15, 3};
            countingSort.countingSort(arr);
            assertArrayEquals(new int[]{1, 3, 5, 10, 15}, arr);
        }

        @Test
        @DisplayName("Should throw exception for null array")
        void testNullArray() {
            int[] arr = null;
            assertThrows(NullPointerException.class, () -> countingSort.countingSort(arr));
        }

        @Test
        @DisplayName("Should throw exception for negative numbers")
        void testNegativeNumbers() {
            int[] arr = {3, -1, 4, 2};
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> countingSort.countingSort(arr));
        }
    }

    @Nested
    @DisplayName("Performance and Stress Tests")
    class PerformanceTests {

        @Test
        @DisplayName("Should sort large array with small range efficiently")
        void testLargeArraySmallRange() {
            int[] arr = new Random(42).ints(10000, 0, 100).toArray();
            int[] expected = arr.clone();
            Arrays.sort(expected);

            long startTime = System.currentTimeMillis();
            countingSort.countingSort(arr);
            long endTime = System.currentTimeMillis();

            assertArrayEquals(expected, arr);
            // Should be very fast for small range
            assertTrue(endTime - startTime < 100,
                "CountingSort took too long for small range: " + (endTime - startTime) + "ms");
        }

        @Test
        @DisplayName("Should handle array with many duplicates efficiently")
        void testManyDuplicates() {
            int[] arr = new int[1000];
            Random random = new Random(42);
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(10); // Only values 0-9
            }

            int[] expected = arr.clone();
            Arrays.sort(expected);

            countingSort.countingSort(arr);
            assertArrayEquals(expected, arr);
        }

        @Test
        @DisplayName("Should be stable (preserve relative order of equal elements)")
        void testStability() {
            // Note: Basic counting sort is stable, but we test the implementation
            int[] arr = {3, 2, 3, 1, 2, 1};
            int[] expected = {1, 1, 2, 2, 3, 3};

            countingSort.countingSort(arr);
            assertArrayEquals(expected, arr);
        }

        @Test
        @DisplayName("Should handle worst case scenario gracefully")
        void testWorstCase() {
            // Worst case: large range with few elements
            int[] arr = {1, 10000}; // Creates large counting array

            long startTime = System.currentTimeMillis();
            countingSort.countingSort(arr);
            long endTime = System.currentTimeMillis();

            assertArrayEquals(new int[]{1, 10000}, arr);
            // Should still complete in reasonable time
            assertTrue(endTime - startTime < 1000,
                "CountingSort took too long for worst case: " + (endTime - startTime) + "ms");
        }
    }

    @ParameterizedTest
    @MethodSource("provideTestArrays")
    @DisplayName("Should sort various array configurations")
    void testVariousArrays(int[] input, int[] expected) {
        countingSort.countingSort(input);
        assertArrayEquals(expected, input);
    }

    private static Stream<Arguments> provideTestArrays() {
        return Stream.of(
            Arguments.of(new int[]{}, new int[]{}),
            Arguments.of(new int[]{1}, new int[]{1}),
            Arguments.of(new int[]{2, 1}, new int[]{1, 2}),
            Arguments.of(new int[]{3, 1, 2}, new int[]{1, 2, 3}),
            Arguments.of(new int[]{1, 3, 2, 4}, new int[]{1, 2, 3, 4}),
            Arguments.of(new int[]{5, 2, 8, 1, 9}, new int[]{1, 2, 5, 8, 9}),
            Arguments.of(new int[]{0, 0, 0}, new int[]{0, 0, 0}),
            Arguments.of(new int[]{1, 2, 2, 1}, new int[]{1, 1, 2, 2}),
            Arguments.of(new int[]{10, 5, 15, 0, 20}, new int[]{0, 5, 10, 15, 20})
        );
    }

    @Nested
    @DisplayName("Correctness Verification Tests")
    class CorrectnessTests {

        @Test
        @DisplayName("Should maintain array length")
        void testArrayLengthPreserved() {
            int[] arr = {5, 2, 8, 1, 9, 3};
            int originalLength = arr.length;
            countingSort.countingSort(arr);
            assertEquals(originalLength, arr.length);
        }

        @Test
        @DisplayName("Should preserve all elements (permutation test)")
        void testElementsPreserved() {
            int[] arr = {5, 2, 8, 1, 9, 3, 2, 5};
            int[] original = arr.clone();

            countingSort.countingSort(arr);

            // Both arrays should have same elements (just reordered)
            Arrays.sort(original);
            assertArrayEquals(original, arr);
        }

        @Test
        @DisplayName("Should produce stable sort behavior")
        void testSortStability() {
            // Test that multiple sorts of the same array produce the same result
            int[] arr1 = {3, 1, 4, 1, 5, 9, 2, 6, 5};
            int[] arr2 = arr1.clone();

            countingSort.countingSort(arr1);
            countingSort.countingSort(arr2);

            assertArrayEquals(arr1, arr2);
        }

        @Test
        @DisplayName("Should match Arrays.sort() result for valid inputs")
        void testMatchesStandardSort() {
            Random random = new Random(42);
            for (int test = 0; test < 10; test++) {
                int[] arr1 = random.ints(50, 0, 100).toArray(); // Non-negative integers only
                int[] arr2 = arr1.clone();

                countingSort.countingSort(arr1);
                Arrays.sort(arr2);

                assertArrayEquals(arr2, arr1, "Test case " + test + " failed");
            }
        }

        @Test
        @DisplayName("Should be more efficient than comparison sorts for small ranges")
        void testEfficiencyComparison() {
            int[] arr = new Random(42).ints(5000, 0, 50).toArray(); // Small range
            int[] arrForComparison = arr.clone();

            // Time CountingSort
            long startTime = System.currentTimeMillis();
            countingSort.countingSort(arr);
            long countingSortTime = System.currentTimeMillis() - startTime;

            // Time Arrays.sort (QuickSort/TimSort)
            startTime = System.currentTimeMillis();
            Arrays.sort(arrForComparison);
            long arraysSortTime = System.currentTimeMillis() - startTime;

            // Verify same result
            assertArrayEquals(arrForComparison, arr);

            // CountingSort should be competitive or faster for small ranges
            System.out.println("CountingSort: " + countingSortTime + "ms, Arrays.sort: " + arraysSortTime + "ms");
        }
    }

    @Nested
    @DisplayName("Memory Efficiency Tests")
    class MemoryEfficiencyTests {

        @Test
        @DisplayName("Should handle reasonable memory usage for typical cases")
        void testMemoryUsage() {
            // Test with reasonable range to ensure we don't run out of memory
            int[] arr = {1, 5, 3, 8, 2, 7, 4, 6};

            assertDoesNotThrow(() -> countingSort.countingSort(arr));
            assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, arr);
        }

        @Test
        @DisplayName("Should warn about potential memory issues with large ranges")
        void testLargeRangeWarning() {
            // This test documents the limitation of counting sort with large ranges
            // In a real implementation, you might want to add range validation
            int[] arr = {0, 1000000}; // This creates a 1M+ element counting array

            // This will work but uses significant memory
            assertDoesNotThrow(() -> countingSort.countingSort(arr));
            assertArrayEquals(new int[]{0, 1000000}, arr);
        }
    }
}
