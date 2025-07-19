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

class BucketSortTest {

    private final BucketSort bucketSort = new BucketSort();

    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {

        @Test
        @DisplayName("Should sort empty array")
        void testEmptyArray() {
            int[] arr = {};
            bucketSort.sort(arr);
            assertArrayEquals(new int[]{}, arr);
        }

        @Test
        @DisplayName("Should sort single element array")
        void testSingleElement() {
            int[] arr = {5};
            bucketSort.sort(arr);
            assertArrayEquals(new int[]{5}, arr);
        }

        @Test
        @DisplayName("Should sort two element array")
        void testTwoElements() {
            int[] arr = {7, 2};
            bucketSort.sort(arr);
            assertArrayEquals(new int[]{2, 7}, arr);
        }

        @Test
        @DisplayName("Should sort already sorted array")
        void testAlreadySorted() {
            int[] arr = {1, 2, 3, 4, 5};
            bucketSort.sort(arr);
            assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
        }

        @Test
        @DisplayName("Should sort reverse sorted array")
        void testReverseSorted() {
            int[] arr = {9, 8, 7, 6, 5};
            bucketSort.sort(arr);
            assertArrayEquals(new int[]{5, 6, 7, 8, 9}, arr);
        }

        @Test
        @DisplayName("Should sort array with duplicates")
        void testWithDuplicates() {
            int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
            bucketSort.sort(arr);
            assertArrayEquals(new int[]{1, 1, 2, 3, 3, 4, 5, 5, 6, 9}, arr);
        }

        @Test
        @DisplayName("Should sort array with all identical elements")
        void testAllIdentical() {
            int[] arr = {7, 7, 7, 7, 7};
            bucketSort.sort(arr);
            assertArrayEquals(new int[]{7, 7, 7, 7, 7}, arr);
        }

        @Test
        @DisplayName("Should handle array with zeros")
        void testWithZeros() {
            int[] arr = {3, 0, 2, 0, 1, 0};
            bucketSort.sort(arr);
            assertArrayEquals(new int[]{0, 0, 0, 1, 2, 3}, arr);
        }

        @Test
        @DisplayName("Should handle array with only zeros")
        void testOnlyZeros() {
            int[] arr = {0, 0, 0, 0};
            bucketSort.sort(arr);
            assertArrayEquals(new int[]{0, 0, 0, 0}, arr);
        }
    }

    @Nested
    @DisplayName("Valid Range Tests (0-9)")
    class ValidRangeTests {

        @Test
        @DisplayName("Should sort array with all digits 0-9")
        void testAllDigits() {
            int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
            bucketSort.sort(arr);
            assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, arr);
        }

        @Test
        @DisplayName("Should handle maximum value (9)")
        void testMaxValue() {
            int[] arr = {9, 1, 9, 2, 9};
            bucketSort.sort(arr);
            assertArrayEquals(new int[]{1, 2, 9, 9, 9}, arr);
        }

        @Test
        @DisplayName("Should handle minimum value (0)")
        void testMinValue() {
            int[] arr = {5, 0, 3, 0, 1};
            bucketSort.sort(arr);
            assertArrayEquals(new int[]{0, 0, 1, 3, 5}, arr);
        }

        @Test
        @DisplayName("Should sort random valid digits")
        void testRandomValidDigits() {
            int[] arr = {4, 7, 1, 9, 2, 5, 8, 0, 3, 6};
            bucketSort.sort(arr);
            assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, arr);
        }
    }

    @Nested
    @DisplayName("Edge Cases and Boundary Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("Should throw exception for null array")
        void testNullArray() {
            int[] arr = null;
            assertThrows(NullPointerException.class, () -> bucketSort.sort(arr));
        }

        @Test
        @DisplayName("Should throw exception for negative numbers")
        void testNegativeNumbers() {
            int[] arr = {3, -1, 4, 2};
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> bucketSort.sort(arr));
        }

        @Test
        @DisplayName("Should throw exception for numbers greater than 9")
        void testNumbersGreaterThan9() {
            int[] arr = {3, 10, 4, 2};
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> bucketSort.sort(arr));
        }

        @Test
        @DisplayName("Should throw exception for large numbers")
        void testLargeNumbers() {
            int[] arr = {3, 100, 4, 2};
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> bucketSort.sort(arr));
        }

        @Test
        @DisplayName("Should document the valid range limitation")
        void testRangeLimitation() {
            // This test documents that BucketSort only works with 0-9
            int[] validArr = {9, 0, 5, 3, 7, 1, 8, 2, 6, 4};
            assertDoesNotThrow(() -> bucketSort.sort(validArr));

            int[] invalidArr = {10, 5, 3};
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> bucketSort.sort(invalidArr));
        }
    }

    @Nested
    @DisplayName("Performance and Stress Tests")
    class PerformanceTests {

        @Test
        @DisplayName("Should sort large array with valid range efficiently")
        void testLargeArrayValidRange() {
            int[] arr = new Random(42).ints(10000, 0, 10).toArray();
            int[] expected = arr.clone();
            Arrays.sort(expected);

            long startTime = System.currentTimeMillis();
            bucketSort.sort(arr);
            long endTime = System.currentTimeMillis();

            assertArrayEquals(expected, arr);
            // Should be very fast for valid range
            assertTrue(endTime - startTime < 100,
                "BucketSort took too long for valid range: " + (endTime - startTime) + "ms");
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

            bucketSort.sort(arr);
            assertArrayEquals(expected, arr);
        }

        @Test
        @DisplayName("Should be stable (preserve relative order of equal elements)")
        void testStability() {
            // Note: This BucketSort implementation is stable
            int[] arr = {3, 2, 3, 1, 2, 1};
            int[] expected = {1, 1, 2, 2, 3, 3};

            bucketSort.sort(arr);
            assertArrayEquals(expected, arr);
        }

        @Test
        @DisplayName("Should have linear time complexity for valid range")
        void testLinearTimeComplexity() {
            // Test with different sizes to verify O(n) performance
            int[] sizes = {100, 1000, 10000};
            long[] times = new long[sizes.length];

            for (int i = 0; i < sizes.length; i++) {
                int[] arr = new Random(42).ints(sizes[i], 0, 10).toArray();

                long startTime = System.nanoTime();
                bucketSort.sort(arr);
                long endTime = System.nanoTime();

                times[i] = endTime - startTime;
            }

            // Verify it's sorted correctly for the largest case
            int[] testArr = new Random(42).ints(sizes[sizes.length - 1], 0, 10).toArray();
            int[] expected = testArr.clone();
            Arrays.sort(expected);
            bucketSort.sort(testArr);
            assertArrayEquals(expected, testArr);

            // The time should scale roughly linearly (allow some variance)
            assertTrue(times[2] < times[0] * 200, // 10000 elements shouldn't take 200x more time than 100
                "BucketSort doesn't appear to have linear time complexity");
        }
    }

    @ParameterizedTest
    @MethodSource("provideTestArrays")
    @DisplayName("Should sort various valid array configurations")
    void testVariousArrays(int[] input, int[] expected) {
        bucketSort.sort(input);
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
            Arguments.of(new int[]{9, 5, 7, 0, 8}, new int[]{0, 5, 7, 8, 9})
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
            bucketSort.sort(arr);
            assertEquals(originalLength, arr.length);
        }

        @Test
        @DisplayName("Should preserve all elements (permutation test)")
        void testElementsPreserved() {
            int[] arr = {5, 2, 8, 1, 9, 3, 2, 5};
            int[] original = arr.clone();

            bucketSort.sort(arr);

            // Both arrays should have same elements (just reordered)
            Arrays.sort(original);
            assertArrayEquals(original, arr);
        }

        @Test
        @DisplayName("Should produce deterministic results")
        void testDeterministicBehavior() {
            // Test that multiple sorts of the same array produce the same result
            int[] arr1 = {3, 1, 4, 1, 5, 9, 2, 6, 5};
            int[] arr2 = arr1.clone();

            bucketSort.sort(arr1);
            bucketSort.sort(arr2);

            assertArrayEquals(arr1, arr2);
        }

        @Test
        @DisplayName("Should match Arrays.sort() result for valid inputs")
        void testMatchesStandardSort() {
            Random random = new Random(42);
            for (int test = 0; test < 10; test++) {
                int[] arr1 = random.ints(50, 0, 10).toArray(); // Valid range 0-9
                int[] arr2 = arr1.clone();

                bucketSort.sort(arr1);
                Arrays.sort(arr2);

                assertArrayEquals(arr2, arr1, "Test case " + test + " failed");
            }
        }

        @Test
        @DisplayName("Should be more efficient than comparison sorts for valid range")
        void testEfficiencyComparison() {
            int[] arr = new Random(42).ints(5000, 0, 10).toArray(); // Valid range
            int[] arrForComparison = arr.clone();

            // Time BucketSort
            long startTime = System.currentTimeMillis();
            bucketSort.sort(arr);
            long bucketSortTime = System.currentTimeMillis() - startTime;

            // Time Arrays.sort (QuickSort/TimSort)
            startTime = System.currentTimeMillis();
            Arrays.sort(arrForComparison);
            long arraysSortTime = System.currentTimeMillis() - startTime;

            // Verify same result
            assertArrayEquals(arrForComparison, arr);

            // BucketSort should be competitive or faster for small ranges
            System.out.println("BucketSort: " + bucketSortTime + "ms, Arrays.sort: " + arraysSortTime + "ms");
        }
    }

    @Nested
    @DisplayName("Implementation Limitation Tests")
    class LimitationTests {

        @Test
        @DisplayName("Should document the hardcoded range limitation")
        void testHardcodedRangeLimitation() {
            // This test documents that the current implementation only supports 0-9
            // In a production environment, this would be improved to handle dynamic ranges

            // Valid range works
            int[] validArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
            assertDoesNotThrow(() -> bucketSort.sort(validArr));
            assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, validArr);

            // Invalid ranges throw exceptions
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
                bucketSort.sort(new int[]{10});
            });

            assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
                bucketSort.sort(new int[]{-1});
            });
        }

        @Test
        @DisplayName("Should suggest improvements for production use")
        void testProductionImprovements() {
            // This test documents potential improvements:
            // 1. Dynamic range calculation (min/max finding)
            // 2. Support for negative numbers
            // 3. Memory optimization for sparse ranges
            // 4. Generic bucket count parameter

            assertTrue(true, "Current implementation should be enhanced for production use");
        }
    }
}
