package recursion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IsArraySortedTest {

    @Test
    public void testSingleElementArray() {
        // Single element array should always be considered sorted
        int[] array = {5};
        assertTrue(IsArraySorted.isArraySorted(array, 1));
    }

    @Test
    public void testTwoElementsSorted() {
        // Two elements in ascending order
        int[] array = {1, 2};
        assertTrue(IsArraySorted.isArraySorted(array, 2));
    }

    @Test
    public void testTwoElementsUnsorted() {
        // Two elements in descending order
        int[] array = {2, 1};
        assertFalse(IsArraySorted.isArraySorted(array, 2));
    }

    @Test
    public void testTwoElementsEqual() {
        // Two equal elements should be considered sorted
        int[] array = {5, 5};
        assertTrue(IsArraySorted.isArraySorted(array, 2));
    }

    @Test
    public void testSortedArrayAscending() {
        // Array sorted in ascending order
        int[] array = {1, 2, 3, 4, 5};
        assertTrue(IsArraySorted.isArraySorted(array, 5));
    }

    @Test
    public void testUnsortedArray() {
        // Array not sorted
        int[] array = {1, 3, 2, 4, 5};
        assertFalse(IsArraySorted.isArraySorted(array, 5));
    }

    @Test
    public void testSortedArrayWithDuplicates() {
        // Array with duplicate elements in sorted order
        int[] array = {1, 1, 2, 2, 3, 3};
        assertTrue(IsArraySorted.isArraySorted(array, 6));
    }

    @Test
    public void testUnsortedArrayWithDuplicates() {
        // Array with duplicates but not sorted
        int[] array = {1, 2, 2, 1, 3};
        assertFalse(IsArraySorted.isArraySorted(array, 5));
    }

    @Test
    public void testAllElementsEqual() {
        // All elements are the same
        int[] array = {7, 7, 7, 7, 7};
        assertTrue(IsArraySorted.isArraySorted(array, 5));
    }

    @Test
    public void testDescendingArray() {
        // Array sorted in descending order should return false
        int[] array = {5, 4, 3, 2, 1};
        assertFalse(IsArraySorted.isArraySorted(array, 5));
    }

    @Test
    public void testLargeArray() {
        // Test with a larger array
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertTrue(IsArraySorted.isArraySorted(array, 10));
    }

    @Test
    public void testLargeUnsortedArray() {
        // Test with a larger unsorted array
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 10, 9};
        assertFalse(IsArraySorted.isArraySorted(array, 10));
    }

    @Test
    public void testNegativeNumbers() {
        // Array with negative numbers
        int[] array = {-5, -3, -1, 0, 2, 4};
        assertTrue(IsArraySorted.isArraySorted(array, 6));
    }

    @Test
    public void testUnsortedNegativeNumbers() {
        // Unsorted array with negative numbers
        int[] array = {-1, -5, -3, 0, 2, 4};
        assertFalse(IsArraySorted.isArraySorted(array, 6));
    }

    @Test
    public void testPartialArrayCheck() {
        // Check only part of the array (first 3 elements)
        int[] array = {1, 2, 3, 1, 0};
        assertTrue(IsArraySorted.isArraySorted(array, 3));
    }

    @Test
    public void testPartialArrayCheckUnsorted() {
        // Check only part of the array (first 3 elements) - unsorted
        int[] array = {1, 3, 2, 4, 5};
        assertFalse(IsArraySorted.isArraySorted(array, 3));
    }

    @Test
    public void testEdgeCaseIndex1() {
        // Test with index = 1 (base case)
        int[] array = {10, 20, 30};
        assertTrue(IsArraySorted.isArraySorted(array, 1));
    }

    @Test
    public void testMixedPositiveNegative() {
        // Mixed positive and negative numbers
        int[] array = {-10, -5, 0, 5, 10, 15};
        assertTrue(IsArraySorted.isArraySorted(array, 6));
    }

    @Test
    public void testUnsortedMixedPositiveNegative() {
        // Unsorted mixed positive and negative numbers
        int[] array = {-10, 0, -5, 5, 10, 15};
        assertFalse(IsArraySorted.isArraySorted(array, 6));
    }

    @Test
    public void testZeroElements() {
        // Array with zeros
        int[] array = {0, 0, 0, 0};
        assertTrue(IsArraySorted.isArraySorted(array, 4));
    }
}
