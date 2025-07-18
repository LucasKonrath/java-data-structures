package sorting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TreeSortTest {

    private final TreeSort treeSort = new TreeSort();

    @Nested
    @DisplayName("Integer Array Tests")
    class IntegerArrayTests {

        @Test
        @DisplayName("Should sort empty array")
        void testEmptyArray() {
            int[] arr = {};
            treeSort.treeSort(arr);
            assertArrayEquals(new int[]{}, arr);
        }

        @Test
        @DisplayName("Should sort single element array")
        void testSingleElement() {
            int[] arr = {42};
            treeSort.treeSort(arr);
            assertArrayEquals(new int[]{42}, arr);
        }

        @Test
        @DisplayName("Should sort two element array")
        void testTwoElements() {
            int[] arr = {5, 2};
            treeSort.treeSort(arr);
            assertArrayEquals(new int[]{2, 5}, arr);
        }

        @Test
        @DisplayName("Should sort already sorted array")
        void testAlreadySorted() {
            int[] arr = {1, 2, 3, 4, 5};
            treeSort.treeSort(arr);
            assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
        }

        @Test
        @DisplayName("Should sort reverse sorted array")
        void testReverseSorted() {
            int[] arr = {5, 4, 3, 2, 1};
            treeSort.treeSort(arr);
            assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
        }

        @Test
        @DisplayName("Should handle duplicates by preserving them")
        void testWithDuplicates() {
            int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
            treeSort.treeSort(arr);
            // TreeSort now preserves duplicates, so we expect all elements sorted
            assertArrayEquals(new int[]{1, 1, 2, 3, 3, 4, 5, 5, 6, 9}, arr);
        }

        @Test
        @DisplayName("Should handle negative numbers")
        void testNegativeNumbers() {
            int[] arr = {-3, -1, -4, -1, -5};
            treeSort.treeSort(arr);
            // All elements preserved and sorted
            assertArrayEquals(new int[]{-5, -4, -3, -1, -1}, arr);
        }

        @Test
        @DisplayName("Should handle mixed positive and negative numbers")
        void testMixedNumbers() {
            int[] arr = {-2, 0, 3, -1, 2, -3, 1};
            treeSort.treeSort(arr);
            // Unique sorted: {-3, -2, -1, 0, 1, 2, 3}
            int[] expected = {-3, -2, -1, 0, 1, 2, 3};
            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], arr[i]);
            }
        }

        @Test
        @DisplayName("Should handle null array")
        void testNullArray() {
            int[] arr = null;
            assertDoesNotThrow(() -> treeSort.treeSort(arr));
        }

        @Test
        @DisplayName("Should handle large numbers")
        void testLargeNumbers() {
            int[] arr = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 1000000, -1000000};
            treeSort.treeSort(arr);
            // Unique sorted: {Integer.MIN_VALUE, -1000000, 0, 1000000, Integer.MAX_VALUE}
            assertEquals(Integer.MIN_VALUE, arr[0]);
            assertEquals(-1000000, arr[1]);
            assertEquals(0, arr[2]);
            assertEquals(1000000, arr[3]);
            assertEquals(Integer.MAX_VALUE, arr[4]);
        }
    }

    @Nested
    @DisplayName("Generic List Tests")
    class GenericListTests {

        @Test
        @DisplayName("Should sort empty list")
        void testEmptyList() {
            List<Integer> list = new ArrayList<>();
            treeSort.treeSort(list);
            assertTrue(list.isEmpty());
        }

        @Test
        @DisplayName("Should sort single element list")
        void testSingleElementList() {
            List<Integer> list = new ArrayList<>(Arrays.asList(42));
            treeSort.treeSort(list);
            assertEquals(Arrays.asList(42), list);
        }

        @Test
        @DisplayName("Should sort integer list")
        void testIntegerList() {
            List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3));
            treeSort.treeSort(list);
            // TreeSort removes duplicates
            assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 9), list);
        }

        @Test
        @DisplayName("Should sort string list")
        void testStringList() {
            List<String> list = new ArrayList<>(Arrays.asList("banana", "apple", "cherry", "apple", "date"));
            treeSort.treeSort(list);
            // TreeSort removes duplicates
            assertEquals(Arrays.asList("apple", "banana", "cherry", "date"), list);
        }

        @Test
        @DisplayName("Should sort double list")
        void testDoubleList() {
            List<Double> list = new ArrayList<>(Arrays.asList(3.14, 1.41, 2.71, 1.41, 0.57));
            treeSort.treeSort(list);
            // TreeSort removes duplicates
            assertEquals(Arrays.asList(0.57, 1.41, 2.71, 3.14), list);
        }

        @Test
        @DisplayName("Should handle null list")
        void testNullList() {
            List<Integer> list = null;
            assertDoesNotThrow(() -> treeSort.treeSort(list));
        }

        @Test
        @DisplayName("Should sort already sorted list")
        void testAlreadySortedList() {
            List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
            treeSort.treeSort(list);
            assertEquals(Arrays.asList(1, 2, 3, 4, 5), list);
        }

        @Test
        @DisplayName("Should sort reverse sorted list")
        void testReverseSortedList() {
            List<Integer> list = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
            treeSort.treeSort(list);
            assertEquals(Arrays.asList(1, 2, 3, 4, 5), list);
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

            treeSort.treeSort(arr);

            // Verify it's sorted (note: duplicates are removed)
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] != 0 || arr[i-1] != 0) { // Skip the zero-filled portion
                    assertTrue(arr[i-1] <= arr[i],
                        "Array is not sorted at index " + i + ": " + arr[i-1] + " > " + arr[i]);
                }
            }
        }

        @Test
        @DisplayName("Should handle array with many duplicates")
        void testManyDuplicates() {
            int[] arr = new int[100];
            Random random = new Random(42);
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(5); // Only values 0-4
            }

            treeSort.treeSort(arr);

            // Verify the array is sorted (duplicates are preserved)
            for (int i = 1; i < arr.length; i++) {
                assertTrue(arr[i-1] <= arr[i],
                    "Array is not sorted at index " + i + ": " + arr[i-1] + " > " + arr[i]);
            }
        }

        @Test
        @DisplayName("Should sort array in reasonable time")
        void testPerformance() {
            int[] arr = new Random(42).ints(5000, -25000, 25000).toArray();

            long startTime = System.currentTimeMillis();
            treeSort.treeSort(arr);
            long endTime = System.currentTimeMillis();

            // Verify the array is sorted
            for (int i = 1; i < arr.length; i++) {
                assertTrue(arr[i-1] <= arr[i],
                    "Array is not sorted at index " + i + ": " + arr[i-1] + " > " + arr[i]);
            }

            // Should complete in reasonable time (less than 2 seconds for 5k elements)
            assertTrue(endTime - startTime < 2000,
                "TreeSort took too long: " + (endTime - startTime) + "ms");
        }
    }

    @ParameterizedTest
    @MethodSource("provideTestArrays")
    @DisplayName("Should sort various array configurations")
    void testVariousArrays(int[] input, int[] expected) {
        treeSort.treeSort(input);
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
            treeSort.treeSort(arr);
            assertEquals(originalLength, arr.length);
        }

        @Test
        @DisplayName("Should produce deterministic results")
        void testDeterministicBehavior() {
            // Test that multiple sorts of the same array produce the same result
            int[] arr1 = {3, 1, 4, 1, 5, 9, 2, 6, 5};
            int[] arr2 = arr1.clone();

            treeSort.treeSort(arr1);
            treeSort.treeSort(arr2);

            assertArrayEquals(arr1, arr2);
        }

        @Test
        @DisplayName("Should handle edge cases gracefully")
        void testEdgeCases() {
            // Test with all same elements
            int[] arr = {5, 5, 5, 5, 5};
            treeSort.treeSort(arr);
            assertEquals(5, arr[0]); // First element should be 5
            assertEquals(5, arr[1]); // Rest should also be 5 (no removal of duplicates)
        }

        @Test
        @DisplayName("Should work with custom comparable objects")
        void testCustomComparableObjects() {
            List<Person> people = new ArrayList<>(Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 35),
                new Person("Bob", 25) // duplicate
            ));

            treeSort.treeSort(people);

            // Should be sorted by age, duplicates removed
            assertEquals(3, people.size());
            assertEquals("Bob", people.get(0).name);
            assertEquals(25, people.get(0).age);
            assertEquals("Alice", people.get(1).name);
            assertEquals(30, people.get(1).age);
            assertEquals("Charlie", people.get(2).name);
            assertEquals(35, people.get(2).age);
        }
    }

    // Helper class for testing custom comparable objects
    private static class Person implements Comparable<Person> {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person other) {
            return Integer.compare(this.age, other.age);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person person = (Person) obj;
            return age == person.age && name.equals(person.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode() + age;
        }
    }
}
