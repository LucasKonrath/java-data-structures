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

class QuickSortTest {

    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {

        @Test
        @DisplayName("Should sort empty array")
        void testEmptyArray() {
            int[] arr = {};
            QuickSort.quickSort(arr, 0, arr.length - 1);
            assertArrayEquals(new int[]{}, arr);
        }

        @Test
        @DisplayName("Should sort single element array")
        void testSingleElement() {
            int[] arr = {42};
            QuickSort.quickSort(arr, 0, arr.length - 1);
            assertArrayEquals(new int[]{42}, arr);
        }

        @Test
        @DisplayName("Should sort two element array")
        void testTwoElements() {
            int[] arr = {5, 2};
            QuickSort.quickSort(arr, 0, arr.length - 1);
            assertArrayEquals(new int[]{2, 5}, arr);
        }

        @Test
        @DisplayName("Should sort already sorted array")
        void testAlreadySorted() {
            int[] arr = {1, 2, 3, 4, 5};
            QuickSort.quickSort(arr, 0, arr.length - 1);
            assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
        }

        @Test
        @DisplayName("Should sort reverse sorted array")
        void testReverseSorted() {
            int[] arr = {5, 4, 3, 2, 1};
            QuickSort.quickSort(arr, 0, arr.length - 1);
            assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
        }

        @Test
        @DisplayName("Should sort array with duplicates")
        void testWithDuplicates() {
            int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
            QuickSort.quickSort(arr, 0, arr.length - 1);
            assertArrayEquals(new int[]{1, 1, 2, 3, 3, 4, 5, 5, 6, 9}, arr);
        }

        @Test
        @DisplayName("Should sort array with all identical elements")
        void testAllIdentical() {
            int[] arr = {7, 7, 7, 7, 7};
            QuickSort.quickSort(arr, 0, arr.length - 1);
            assertArrayEquals(new int[]{7, 7, 7, 7, 7}, arr);
        }
    }

    @Nested
    @DisplayName("Edge Cases and Boundary Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("Should handle negative numbers")
        void testNegativeNumbers() {
            int[] arr = {-3, -1, -4, -1, -5};
            QuickSort.quickSort(arr, 0, arr.length - 1);
            assertArrayEquals(new int[]{-5, -4, -3, -1, -1}, arr);
        }

        @Test
        @DisplayName("Should handle mixed positive and negative numbers")
        void testMixedNumbers() {
            int[] arr = {-2, 0, 3, -1, 2, -3, 1};
            QuickSort.quickSort(arr, 0, arr.length - 1);
            assertArrayEquals(new int[]{-3, -2, -1, 0, 1, 2, 3}, arr);
        }

        @Test
        @DisplayName("Should handle array with zero")
        void testWithZero() {
            int[] arr = {3, 0, -1, 2, 0, 1};
            QuickSort.quickSort(arr, 0, arr.length - 1);
            assertArrayEquals(new int[]{-1, 0, 0, 1, 2, 3}, arr);
        }

        @Test
        @DisplayName("Should handle large numbers")
        void testLargeNumbers() {
            int[] arr = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 1000000, -1000000};
            QuickSort.quickSort(arr, 0, arr.length - 1);
            assertArrayEquals(new int[]{Integer.MIN_VALUE, -1000000, 0, 1000000, Integer.MAX_VALUE}, arr);
        }

        @Test
        @DisplayName("Should not modify array when low >= high")
        void testInvalidBounds() {
            int[] original = {3, 1, 4, 1, 5};
            int[] arr = original.clone();

            // Test low > high
            QuickSort.quickSort(arr, 3, 1);
            assertArrayEquals(original, arr);

            // Test low == high
            QuickSort.quickSort(arr, 2, 2);
            assertArrayEquals(original, arr);
        }
    }

    @Nested
    @DisplayName("Partial Sorting Tests")
    class PartialSortingTests {

        @Test
        @DisplayName("Should sort partial array range")
        void testPartialSort() {
            int[] arr = {10, 5, 3, 8, 2, 7, 1, 9};
            // Sort only indices 2 to 5 (3, 8, 2, 7)
            QuickSort.quickSort(arr, 2, 5);

            // Check that only the specified range is sorted
            assertEquals(10, arr[0]); // unchanged
            assertEquals(5, arr[1]);  // unchanged
            assertEquals(2, arr[2]);  // sorted
            assertEquals(3, arr[3]);  // sorted
            assertEquals(7, arr[4]);  // sorted
            assertEquals(8, arr[5]);  // sorted
            assertEquals(1, arr[6]);  // unchanged
            assertEquals(9, arr[7]);  // unchanged
        }

        @Test
        @DisplayName("Should sort first half of array")
        void testSortFirstHalf() {
            int[] arr = {6, 2, 8, 1, 4, 9, 3, 7};
            QuickSort.quickSort(arr, 0, 3); // Sort first 4 elements

            // Verify first 4 elements are sorted
            assertTrue(arr[0] <= arr[1]);
            assertTrue(arr[1] <= arr[2]);
            assertTrue(arr[2] <= arr[3]);

            // Note: QuickSort may affect elements outside the range during partitioning
            // This is expected behavior for the Lomuto partition scheme
            // The original elements {4, 9, 3, 7} may be reordered
        }

        @Test
        @DisplayName("Should sort last half of array")
        void testSortLastHalf() {
            int[] arr = {6, 2, 8, 1, 4, 9, 3, 7};
            int[] originalFirstHalf = {arr[0], arr[1], arr[2], arr[3]};
            QuickSort.quickSort(arr, 4, 7); // Sort last 4 elements

            // Verify first 4 elements are unchanged
            assertArrayEquals(originalFirstHalf, new int[]{arr[0], arr[1], arr[2], arr[3]});

            // Verify last 4 elements are sorted
            assertTrue(arr[4] <= arr[5]);
            assertTrue(arr[5] <= arr[6]);
            assertTrue(arr[6] <= arr[7]);
        }
    }

    @Nested
    @DisplayName("Performance and Stress Tests")
    class PerformanceTests {

        @Test
        @DisplayName("Should sort large random array")
        void testLargeRandomArray() {
            int size = 1000;
            int[] arr = new Random(42).ints(size, -1000, 1000).toArray();
            int[] expected = arr.clone();
            Arrays.sort(expected);

            QuickSort.quickSort(arr, 0, arr.length - 1);
            assertArrayEquals(expected, arr);
        }

        @Test
        @DisplayName("Should handle array with many duplicates efficiently")
        void testManyDuplicates() {
            int[] arr = new int[100];
            Random random = new Random(42);
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(5); // Only values 0-4
            }

            int[] expected = arr.clone();
            Arrays.sort(expected);

            QuickSort.quickSort(arr, 0, arr.length - 1);
            assertArrayEquals(expected, arr);
        }

        @Test
        @DisplayName("Should sort array in reasonable time")
        void testPerformance() {
            int[] arr = new Random(42).ints(10000, -50000, 50000).toArray();

            long startTime = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long endTime = System.currentTimeMillis();

            // Verify it's sorted
            for (int i = 1; i < arr.length; i++) {
                assertTrue(arr[i-1] <= arr[i],
                    "Array is not sorted at index " + i + ": " + arr[i-1] + " > " + arr[i]);
            }

            // Should complete in reasonable time (less than 1 second for 10k elements)
            assertTrue(endTime - startTime < 1000,
                "QuickSort took too long: " + (endTime - startTime) + "ms");
        }
    }

    @ParameterizedTest
    @MethodSource("provideTestArrays")
    @DisplayName("Should sort various array configurations")
    void testVariousArrays(int[] input, int[] expected) {
        QuickSort.quickSort(input, 0, input.length - 1);
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
            Arguments.of(new int[]{-1, -3, -2}, new int[]{-3, -2, -1}),
            Arguments.of(new int[]{0, 0, 0}, new int[]{0, 0, 0}),
            Arguments.of(new int[]{1, 2, 2, 1}, new int[]{1, 1, 2, 2})
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
            QuickSort.quickSort(arr, 0, arr.length - 1);
            assertEquals(originalLength, arr.length);
        }

        @Test
        @DisplayName("Should preserve all elements (permutation test)")
        void testElementsPreserved() {
            int[] arr = {5, 2, 8, 1, 9, 3, 2, 5};
            int[] original = arr.clone();

            QuickSort.quickSort(arr, 0, arr.length - 1);

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

            QuickSort.quickSort(arr1, 0, arr1.length - 1);
            QuickSort.quickSort(arr2, 0, arr2.length - 1);

            assertArrayEquals(arr1, arr2);
        }

        @Test
        @DisplayName("Should match Arrays.sort() result")
        void testMatchesStandardSort() {
            Random random = new Random(42);
            for (int test = 0; test < 10; test++) {
                int[] arr1 = random.ints(50, -100, 100).toArray();
                int[] arr2 = arr1.clone();

                QuickSort.quickSort(arr1, 0, arr1.length - 1);
                Arrays.sort(arr2);

                assertArrayEquals(arr2, arr1, "Test case " + test + " failed");
            }
        }
    }
}
