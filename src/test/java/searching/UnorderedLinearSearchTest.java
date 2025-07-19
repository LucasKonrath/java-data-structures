package searching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnorderedLinearSearchTest {

    private SearchAlgorithm search;

    @BeforeEach
    void setUp() {
        search = new UnorderedLinearSearch();
    }

    @Test
    void testSearchEmptyArray() {
        int[] input = {};
        int target = 5;

        int result = search.search(input, target);

        assertEquals(-1, result, "Should return -1 for empty array");
    }

    @Test
    void testSearchSingleElementFound() {
        int[] input = {42};
        int target = 42;

        int result = search.search(input, target);

        assertEquals(0, result, "Should return index 0 when target is the only element");
    }

    @Test
    void testSearchSingleElementNotFound() {
        int[] input = {42};
        int target = 10;

        int result = search.search(input, target);

        assertEquals(-1, result, "Should return -1 when target is not found");
    }

    @Test
    void testSearchTargetAtBeginning() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 10;

        int result = search.search(input, target);

        assertEquals(0, result, "Should return index 0 when target is at beginning");
    }

    @Test
    void testSearchTargetAtEnd() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 50;

        int result = search.search(input, target);

        assertEquals(4, result, "Should return last index when target is at end");
    }

    @Test
    void testSearchTargetInMiddle() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 30;

        int result = search.search(input, target);

        assertEquals(2, result, "Should return correct index when target is in middle");
    }

    @Test
    void testSearchTargetNotFound() {
        int[] input = {10, 20, 30, 40, 50};
        int target = 99;

        int result = search.search(input, target);

        assertEquals(-1, result, "Should return -1 when target is not in array");
    }

    @Test
    void testSearchWithDuplicates() {
        int[] input = {5, 3, 5, 8, 5, 1};
        int target = 5;

        int result = search.search(input, target);

        assertEquals(0, result, "Should return first occurrence index when duplicates exist");
    }

    @Test
    void testSearchWithNegativeNumbers() {
        int[] input = {-10, -5, 0, 5, 10};
        int target = -5;

        int result = search.search(input, target);

        assertEquals(1, result, "Should handle negative numbers correctly");
    }

    @Test
    void testSearchZero() {
        int[] input = {-1, 0, 1};
        int target = 0;

        int result = search.search(input, target);

        assertEquals(1, result, "Should find zero correctly");
    }

    @Test
    void testSearchLargeArray() {
        int[] input = new int[1000];
        for (int i = 0; i < 1000; i++) {
            input[i] = i * 2; // Even numbers 0, 2, 4, 6, ...
        }
        int target = 998; // This should be at index 499

        int result = search.search(input, target);

        assertEquals(499, result, "Should handle large arrays correctly");
    }

    @Test
    void testSearchUnorderedArray() {
        int[] input = {50, 10, 30, 20, 40};
        int target = 20;

        int result = search.search(input, target);

        assertEquals(3, result, "Should work correctly with unordered array");
    }
}
