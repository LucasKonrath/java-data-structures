package sorting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class BogoSortTest {

    @Test
    void testSortEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        new BogoSort().bogoSort(input);

        assertEquals(expected, input);
    }

    @Test
    void testSortSingleElementList() {
        List<Integer> input = Arrays.asList(5);
        List<Integer> expected = Arrays.asList(5);

        new BogoSort().bogoSort(input);

        assertEquals(expected, input);
    }

    @Test
    void testSortAlreadySortedList() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<Integer> expected = Arrays.asList(1, 2, 3);

        new BogoSort().bogoSort(input);

        assertEquals(expected, input);
    }

    @Test
    void testSortReverseSortedList() {
        List<Integer> input = new ArrayList<>(Arrays.asList(3, 2, 1));
        List<Integer> expected = Arrays.asList(1, 2, 3);

        new BogoSort().bogoSort(input);

        assertEquals(expected, input);
    }

    @Test
    void testSortListWithDuplicates() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 1, 2));
        List<Integer> expected = Arrays.asList(1, 2, 2);

        new BogoSort().bogoSort(input);

        assertEquals(expected, input);
    }
}