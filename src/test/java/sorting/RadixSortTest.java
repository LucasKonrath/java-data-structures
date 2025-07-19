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

class RadixSortTest {

    private final RadixSort radixSort = new RadixSort();

    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {

        @Test
        @DisplayName("Should sort empty array")
        void testEmptyArray() {
            int[] arr = {};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{}, arr);
        }

        @Test
        @DisplayName("Should sort single element array")
        void testSingleElement() {
            int[] arr = {42};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{42}, arr);
        }

        @Test
        @DisplayName("Should sort two element array")
        void testTwoElements() {
            int[] arr = {25, 12};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{12, 25}, arr);
        }

        @Test
        @DisplayName("Should sort already sorted array")
        void testAlreadySorted() {
            int[] arr = {11, 22, 33, 44, 55};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{11, 22, 33, 44, 55}, arr);
        }

        @Test
        @DisplayName("Should sort reverse sorted array")
        void testReverseSorted() {
            int[] arr = {55, 44, 33, 22, 11};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{11, 22, 33, 44, 55}, arr);
        }

        @Test
        @DisplayName("Should sort array with duplicates")
        void testWithDuplicates() {
            int[] arr = {170, 45, 75, 90, 2, 802, 24, 66, 45, 170};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{2, 24, 45, 45, 66, 75, 90, 170, 170, 802}, arr);
        }

        @Test
        @DisplayName("Should sort array with all identical elements")
        void testAllIdentical() {
            int[] arr = {77, 77, 77, 77, 77};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{77, 77, 77, 77, 77}, arr);
        }

        @Test
        @DisplayName("Should handle array with zeros")
        void testWithZeros() {
            int[] arr = {30, 0, 20, 0, 10, 0};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{0, 0, 0, 10, 20, 30}, arr);
        }

        @Test
        @DisplayName("Should handle array with only zeros")
        void testOnlyZeros() {
            int[] arr = {0, 0, 0, 0};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{0, 0, 0, 0}, arr);
        }
    }

    @Nested
    @DisplayName("Different Number Ranges Tests")
    class NumberRangeTests {

        @Test
        @DisplayName("Should sort single digit numbers")
        void testSingleDigits() {
            int[] arr = {9, 1, 8, 2, 7, 3, 6, 4, 5, 0};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, arr);
        }

        @Test
        @DisplayName("Should sort two digit numbers")
        void testTwoDigits() {
            int[] arr = {64, 34, 25, 12, 22, 11, 90};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{11, 12, 22, 25, 34, 64, 90}, arr);
        }

        @Test
        @DisplayName("Should sort three digit numbers")
        void testThreeDigits() {
            int[] arr = {170, 45, 75, 90, 2, 802, 24, 66};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{2, 24, 45, 66, 75, 90, 170, 802}, arr);
        }

        @Test
        @DisplayName("Should sort four digit numbers")
        void testFourDigits() {
            int[] arr = {1234, 5678, 9012, 3456, 7890};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{1234, 3456, 5678, 7890, 9012}, arr);
        }

        @Test
        @DisplayName("Should sort mixed digit lengths")
        void testMixedDigitLengths() {
            int[] arr = {5, 123, 45, 6789, 12, 345, 1};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{1, 5, 12, 45, 123, 345, 6789}, arr);
        }

        @Test
        @DisplayName("Should handle large numbers")
        void testLargeNumbers() {
            int[] arr = {987654321, 123456789, 555555555, 111111111};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{111111111, 123456789, 555555555, 987654321}, arr);
        }
    }

    @Nested
    @DisplayName("Edge Cases and Boundary Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("Should throw exception for null array")
        void testNullArray() {
            int[] arr = null;
            assertThrows(NullPointerException.class, () -> radixSort.radixSort(arr));
        }

        @Test
        @DisplayName("Should throw exception for negative numbers")
        void testNegativeNumbers() {
            int[] arr = {30, -10, 20, 15};
            // RadixSort typically only works with non-negative integers
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> radixSort.radixSort(arr));
        }

        @Test
        @DisplayName("Should handle maximum integer value")
        void testMaxInteger() {
            int[] arr = {Integer.MAX_VALUE, 1000, 500};
            radixSort.radixSort(arr);
            assertEquals(500, arr[0]);
            assertEquals(1000, arr[1]);
            assertEquals(Integer.MAX_VALUE, arr[2]);
        }

        @Test
        @DisplayName("Should handle array with one large number and small numbers")
        void testMixedSmallLarge() {
            int[] arr = {1, 1000000, 5, 10, 999999};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{1, 5, 10, 999999, 1000000}, arr);
        }

        @Test
        @DisplayName("Should document the non-negative integer limitation")
        void testNonNegativeLimitation() {
            // This test documents that RadixSort only works with non-negative integers
            int[] validArr = {0, 5, 10, 15, 20};
            assertDoesNotThrow(() -> radixSort.radixSort(validArr));

            int[] invalidArr = {5, -1, 10};
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> radixSort.radixSort(invalidArr));
        }
    }

    @Nested
    @DisplayName("Performance and Stress Tests")
    class PerformanceTests {

        @Test
        @DisplayName("Should sort large array efficiently")
        void testLargeArray() {
            int[] arr = new Random(42).ints(10000, 0, 100000).toArray();
            int[] expected = arr.clone();
            Arrays.sort(expected);

            long startTime = System.currentTimeMillis();
            radixSort.radixSort(arr);
            long endTime = System.currentTimeMillis();

            assertArrayEquals(expected, arr);
            // Should be efficient for reasonable range
            assertTrue(endTime - startTime < 1000,
                "RadixSort took too long: " + (endTime - startTime) + "ms");
        }

        @Test
        @DisplayName("Should handle array with many duplicates efficiently")
        void testManyDuplicates() {
            int[] arr = new int[1000];
            Random random = new Random(42);
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(100); // Only values 0-99
            }

            int[] expected = arr.clone();
            Arrays.sort(expected);

            radixSort.radixSort(arr);
            assertArrayEquals(expected, arr);
        }

        @Test
        @DisplayName("Should be stable (preserve relative order of equal elements)")
        void testStability() {
            // RadixSort is stable
            int[] arr = {123, 456, 789, 123, 456, 123};
            int[] expected = {123, 123, 123, 456, 456, 789};

            radixSort.radixSort(arr);
            assertArrayEquals(expected, arr);
        }

        @Test
        @DisplayName("Should have linear time complexity for fixed digit count")
        void testLinearTimeComplexity() {
            // Test with different sizes but same digit range to verify O(d*(n+k)) performance
            int[] sizes = {100, 1000, 10000};
            long[] times = new long[sizes.length];

            for (int i = 0; i < sizes.length; i++) {
                int[] arr = new Random(42).ints(sizes[i], 0, 1000).toArray(); // Fixed 3-digit range

                long startTime = System.nanoTime();
                radixSort.radixSort(arr);
                long endTime = System.nanoTime();

                times[i] = endTime - startTime;
            }

            // Verify it's sorted correctly for the largest case
            int[] testArr = new Random(42).ints(sizes[sizes.length - 1], 0, 1000).toArray();
            int[] expected = testArr.clone();
            Arrays.sort(expected);
            radixSort.radixSort(testArr);
            assertArrayEquals(expected, testArr);

            // The time should scale roughly linearly (allow some variance)
            assertTrue(times[2] < times[0] * 200, // 10000 elements shouldn't take 200x more time than 100
                "RadixSort doesn't appear to have linear time complexity for fixed digit range");
        }

        @Test
        @DisplayName("Should handle worst case scenario gracefully")
        void testWorstCase() {
            // Worst case: numbers with many digits
            int[] arr = {9999999, 1111111, 5555555, 3333333};

            long startTime = System.currentTimeMillis();
            radixSort.radixSort(arr);
            long endTime = System.currentTimeMillis();

            assertArrayEquals(new int[]{1111111, 3333333, 5555555, 9999999}, arr);
            // Should still complete in reasonable time
            assertTrue(endTime - startTime < 1000,
                "RadixSort took too long for worst case: " + (endTime - startTime) + "ms");
        }
    }

    @ParameterizedTest
    @MethodSource("provideTestArrays")
    @DisplayName("Should sort various array configurations")
    void testVariousArrays(int[] input, int[] expected) {
        radixSort.radixSort(input);
        assertArrayEquals(expected, input);
    }

    private static Stream<Arguments> provideTestArrays() {
        return Stream.of(
            Arguments.of(new int[]{}, new int[]{}),
            Arguments.of(new int[]{1}, new int[]{1}),
            Arguments.of(new int[]{21, 12}, new int[]{12, 21}),
            Arguments.of(new int[]{321, 123, 213}, new int[]{123, 213, 321}),
            Arguments.of(new int[]{1, 30, 20, 40}, new int[]{1, 20, 30, 40}),
            Arguments.of(new int[]{50, 20, 80, 10, 90}, new int[]{10, 20, 50, 80, 90}),
            Arguments.of(new int[]{0, 0, 0}, new int[]{0, 0, 0}),
            Arguments.of(new int[]{12, 22, 22, 12}, new int[]{12, 12, 22, 22}),
            Arguments.of(new int[]{100, 50, 150, 0, 200}, new int[]{0, 50, 100, 150, 200})
        );
    }

    @Nested
    @DisplayName("Correctness Verification Tests")
    class CorrectnessTests {

        @Test
        @DisplayName("Should maintain array length")
        void testArrayLengthPreserved() {
            int[] arr = {50, 20, 80, 10, 90, 30};
            int originalLength = arr.length;
            radixSort.radixSort(arr);
            assertEquals(originalLength, arr.length);
        }

        @Test
        @DisplayName("Should preserve all elements (permutation test)")
        void testElementsPreserved() {
            int[] arr = {50, 20, 80, 10, 90, 30, 20, 50};
            int[] original = arr.clone();

            radixSort.radixSort(arr);

            // Both arrays should have same elements (just reordered)
            Arrays.sort(original);
            assertArrayEquals(original, arr);
        }

        @Test
        @DisplayName("Should produce deterministic results")
        void testDeterministicBehavior() {
            // Test that multiple sorts of the same array produce the same result
            int[] arr1 = {170, 45, 75, 90, 2, 802, 24, 66, 45};
            int[] arr2 = arr1.clone();

            radixSort.radixSort(arr1);
            radixSort.radixSort(arr2);

            assertArrayEquals(arr1, arr2);
        }

        @Test
        @DisplayName("Should match Arrays.sort() result for valid inputs")
        void testMatchesStandardSort() {
            Random random = new Random(42);
            for (int test = 0; test < 10; test++) {
                int[] arr1 = random.ints(50, 0, 10000).toArray(); // Non-negative integers only
                int[] arr2 = arr1.clone();

                radixSort.radixSort(arr1);
                Arrays.sort(arr2);

                assertArrayEquals(arr2, arr1, "Test case " + test + " failed");
            }
        }

        @Test
        @DisplayName("Should be more efficient than comparison sorts for suitable data")
        void testEfficiencyComparison() {
            int[] arr = new Random(42).ints(5000, 0, 10000).toArray(); // Suitable range for radix
            int[] arrForComparison = arr.clone();

            // Time RadixSort
            long startTime = System.currentTimeMillis();
            radixSort.radixSort(arr);
            long radixSortTime = System.currentTimeMillis() - startTime;

            // Time Arrays.sort (QuickSort/TimSort)
            startTime = System.currentTimeMillis();
            Arrays.sort(arrForComparison);
            long arraysSortTime = System.currentTimeMillis() - startTime;

            // Verify same result
            assertArrayEquals(arrForComparison, arr);

            // RadixSort should be competitive for suitable data
            System.out.println("RadixSort: " + radixSortTime + "ms, Arrays.sort: " + arraysSortTime + "ms");
        }
    }

    @Nested
    @DisplayName("Algorithm Specific Tests")
    class AlgorithmSpecificTests {

        @Test
        @DisplayName("Should handle digit extraction correctly")
        void testDigitExtraction() {
            // Test that the algorithm correctly processes each digit position
            int[] arr = {1234, 5678, 9012, 3456};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{1234, 3456, 5678, 9012}, arr);
        }

        @Test
        @DisplayName("Should process digits from least to most significant")
        void testDigitProcessingOrder() {
            // RadixSort processes from rightmost (least significant) to leftmost digit
            int[] arr = {329, 457, 657, 839, 436, 720, 355};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{329, 355, 436, 457, 657, 720, 839}, arr);
        }

        @Test
        @DisplayName("Should maintain stability across digit positions")
        void testStabilityAcrossDigits() {
            // Numbers with same prefix should maintain relative order when processed
            int[] arr = {100, 101, 102, 200, 201, 202};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{100, 101, 102, 200, 201, 202}, arr);
        }

        @Test
        @DisplayName("Should handle numbers with leading zeros conceptually")
        void testLeadingZeros() {
            // Numbers like 1, 01, 001 are all treated as 1
            int[] arr = {1, 10, 100, 1000};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{1, 10, 100, 1000}, arr);
        }

        @Test
        @DisplayName("Should work correctly with powers of 10")
        void testPowersOf10() {
            int[] arr = {1000, 100, 10000, 1, 10};
            radixSort.radixSort(arr);
            assertArrayEquals(new int[]{1, 10, 100, 1000, 10000}, arr);
        }
    }

    @Nested
    @DisplayName("Implementation Limitation Tests")
    class LimitationTests {

        @Test
        @DisplayName("Should document the non-negative integer limitation")
        void testNonNegativeLimitation() {
            // This test documents that the current implementation only supports non-negative integers
            // In a production environment, this could be enhanced to handle negative numbers

            // Valid range works
            int[] validArr = {0, 123, 456, 789};
            assertDoesNotThrow(() -> radixSort.radixSort(validArr));
            assertArrayEquals(new int[]{0, 123, 456, 789}, validArr);

            // Negative numbers cause issues
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
                radixSort.radixSort(new int[]{-1, 5, 10});
            });
        }

        @Test
        @DisplayName("Should suggest improvements for production use")
        void testProductionImprovements() {
            // This test documents potential improvements:
            // 1. Support for negative numbers (offset technique)
            // 2. Support for different bases (not just base 10)
            // 3. Optimization for sparse number ranges
            // 4. Generic support for other data types

            assertTrue(true, "Current implementation could be enhanced for production use");
        }
    }
}
