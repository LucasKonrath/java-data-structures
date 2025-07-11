package heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeapTest {

    private Heap minHeap;
    private Heap maxHeap;

    @BeforeEach
    void setUp() {
        minHeap = new Heap(10, true);
        maxHeap = new Heap(10, false);
    }

    @Test
    void testConstructor() {
        assertEquals(10, minHeap.capacity);
        assertEquals(0, minHeap.count);
        assertTrue(minHeap.isMinHeap);
        assertFalse(minHeap.isMaxHeap);
        assertNotNull(minHeap.array);

        assertEquals(10, maxHeap.capacity);
        assertEquals(0, maxHeap.count);
        assertFalse(maxHeap.isMinHeap);
        assertTrue(maxHeap.isMaxHeap);
        assertNotNull(maxHeap.array);
    }

    @Test
    void testInsertSingleElement() {
        assertEquals(5, minHeap.insert(5));
        assertEquals(1, minHeap.count);
        assertEquals(5, minHeap.array[0]);

        assertEquals(10, maxHeap.insert(10));
        assertEquals(1, maxHeap.count);
        assertEquals(10, maxHeap.array[0]);
    }

    @Test
    void testInsertMultipleElementsMinHeap() {
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(15);
        minHeap.insert(2);
        minHeap.insert(8);

        assertEquals(5, minHeap.count);
        assertEquals(2, minHeap.array[0]); // Root should be minimum
    }

    @Test
    void testInsertMultipleElementsMaxHeap() {
        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(15);
        maxHeap.insert(20);
        maxHeap.insert(8);

        assertEquals(5, maxHeap.count);
        assertEquals(20, maxHeap.array[0]); // Root should be maximum
    }

    @Test
    void testGetMinFromMinHeap() {
        assertEquals(-1, minHeap.getMin()); // Empty heap

        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(15);

        assertEquals(5, minHeap.getMin());
    }

    @Test
    void testGetMinFromMaxHeap() {
        assertEquals(-1, maxHeap.getMin()); // Should return -1 for max heap
    }

    @Test
    void testGetMaxFromMaxHeap() {
        assertEquals(-1, maxHeap.getMax()); // Empty heap

        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(15);

        assertEquals(15, maxHeap.getMax());
    }

    @Test
    void testGetMaxFromMinHeap() {
        assertEquals(-1, minHeap.getMax()); // Should return -1 for min heap
    }

    @Test
    void testDeleteMinFromMinHeap() {
        assertEquals(-1, minHeap.deleteMin()); // Empty heap

        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(15);
        minHeap.insert(2);

        assertEquals(2, minHeap.deleteMin());
        assertEquals(3, minHeap.count);
        assertEquals(5, minHeap.array[0]); // New minimum should be at root
    }

    @Test
    void testDeleteMinFromMaxHeap() {
        assertEquals(-1, maxHeap.deleteMin()); // Should return -1 for max heap
    }

    @Test
    void testDeleteMaxFromMaxHeap() {
        assertEquals(-1, maxHeap.deleteMax()); // Empty heap

        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(15);
        maxHeap.insert(20);

        assertEquals(20, maxHeap.deleteMax());
        assertEquals(3, maxHeap.count);
        assertEquals(15, maxHeap.array[0]); // New maximum should be at root
    }

    @Test
    void testDeleteMaxFromMinHeap() {
        assertEquals(-1, minHeap.deleteMax()); // Should return -1 for min heap
    }

    @Test
    void testParent() {
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(15);

        assertEquals(-1, minHeap.parent(0)); // Root has no parent
        assertEquals(minHeap.array[0], minHeap.parent(1));
        assertEquals(minHeap.array[0], minHeap.parent(2));
        assertEquals(-1, minHeap.parent(-1)); // Invalid index
        assertEquals(-1, minHeap.parent(10)); // Out of bounds
    }

    @Test
    void testLeftChild() {
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(15);
        minHeap.insert(2);

        assertEquals(minHeap.array[1], minHeap.LeftChild(0));
        assertEquals(minHeap.array[3], minHeap.LeftChild(1));
        assertEquals(-1, minHeap.LeftChild(2)); // No left child
        assertEquals(-1, minHeap.LeftChild(10)); // Out of bounds
    }

    @Test
    void testRightChild() {
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(15);
        minHeap.insert(2);
        minHeap.insert(8);

        assertEquals(minHeap.array[2], minHeap.RightChild(0));
        assertEquals(minHeap.array[4], minHeap.RightChild(1));
        assertEquals(-1, minHeap.RightChild(2)); // No right child
        assertEquals(-1, minHeap.RightChild(10)); // Out of bounds
    }

    @Test
    void testSwap() {
        minHeap.insert(10);
        minHeap.insert(5);

        int beforeFirst = minHeap.array[0];
        int beforeSecond = minHeap.array[1];

        minHeap.swap(0, 1);

        assertEquals(beforeSecond, minHeap.array[0]);
        assertEquals(beforeFirst, minHeap.array[1]);
    }

    @Test
    void testSwapInvalidIndices() {
        minHeap.insert(10);

        assertThrows(IndexOutOfBoundsException.class, () -> minHeap.swap(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> minHeap.swap(0, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> minHeap.swap(10, 0));
    }

    @Test
    void testResizeHeap() {
        // Fill the heap to capacity
        for (int i = 0; i < 10; i++) {
            minHeap.insert(i);
        }

        assertEquals(10, minHeap.capacity);
        assertEquals(10, minHeap.count);

        // Insert one more to trigger resize
        minHeap.insert(10);

        assertEquals(20, minHeap.capacity);
        assertEquals(11, minHeap.count);
    }

    @Test
    void testBuildMinHeap() {
        int[] inputArray = {10, 5, 15, 2, 8, 6, 20};
        minHeap.buildMinHeap(inputArray);

        assertEquals(7, minHeap.count);
        assertEquals(7, minHeap.capacity);
        assertEquals(2, minHeap.array[0]); // Should be minimum at root

        // Test with null array
        minHeap.buildMinHeap(null);
        assertEquals(7, minHeap.count); // Should remain unchanged

        // Test with empty array
        minHeap.buildMinHeap(new int[0]);
        assertEquals(7, minHeap.count); // Should remain unchanged
    }

    @Test
    void testHeapSort() {
        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(15);
        maxHeap.insert(2);
        maxHeap.insert(8);

        int originalCount = maxHeap.count;
        maxHeap.heapSort();

        assertEquals(originalCount, maxHeap.count); // Count should be restored

        // Array should be sorted in ascending order
        for (int i = 0; i < maxHeap.count - 1; i++) {
            assertTrue(maxHeap.array[i] <= maxHeap.array[i + 1]);
        }
    }

    @Test
    void testHeapSortEmptyHeap() {
        maxHeap.heapSort();
        assertEquals(0, maxHeap.count);
    }

    @Test
    void testDestroyHeap() {
        minHeap.insert(10);
        minHeap.insert(5);

        minHeap.destroyHeap();

        assertNull(minHeap.array);
        assertEquals(0, minHeap.count);
        assertEquals(0, minHeap.capacity);
        assertFalse(minHeap.isMinHeap);
        assertFalse(minHeap.isMaxHeap);
    }

    @Test
    void testMinHeapProperty() {
        // Insert multiple elements and verify min heap property
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35};

        for (int value : values) {
            minHeap.insert(value);
        }

        // Verify heap property: parent <= children
        for (int i = 0; i < minHeap.count / 2; i++) {
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;

            if (leftChild < minHeap.count) {
                assertTrue(minHeap.array[i] <= minHeap.array[leftChild]);
            }
            if (rightChild < minHeap.count) {
                assertTrue(minHeap.array[i] <= minHeap.array[rightChild]);
            }
        }
    }

    @Test
    void testMaxHeapProperty() {
        // Insert multiple elements and verify max heap property
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35};

        for (int value : values) {
            maxHeap.insert(value);
        }

        // Verify heap property: parent >= children
        for (int i = 0; i < maxHeap.count / 2; i++) {
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;

            if (leftChild < maxHeap.count) {
                assertTrue(maxHeap.array[i] >= maxHeap.array[leftChild]);
            }
            if (rightChild < maxHeap.count) {
                assertTrue(maxHeap.array[i] >= maxHeap.array[rightChild]);
            }
        }
    }

    @Test
    void testSequentialInsertionAndDeletion() {
        // Test inserting and deleting in sequence
        int[] values = {15, 10, 20, 8, 25, 5, 30};

        for (int value : values) {
            minHeap.insert(value);
        }

        // Delete all elements and verify they come out in sorted order
        int previous = minHeap.deleteMin();
        while (minHeap.count > 0) {
            int current = minHeap.deleteMin();
            assertTrue(previous <= current);
            previous = current;
        }
    }

    @Test
    void testPercolateUp() {
        // Test percolate up with invalid indices
        minHeap.percolateUp(-1); // Should not crash
        minHeap.percolateUp(0); // Should not crash when heap is empty

        minHeap.insert(10);
        minHeap.percolateUp(0); // Root element, should not change anything
        assertEquals(10, minHeap.array[0]);
    }

    @Test
    void testPercolateDown() {
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(15);

        // At this point, the heap should be: [5, 10, 15]
        // Manually set root to a large value to test percolate down
        minHeap.array[0] = 20;
        minHeap.percolateDown(0);

        // The heap property should be restored
        // Since we changed root to 20, it should percolate down
        // The new root should be the smaller of the two children (10 or 15)
        assertEquals(10, minHeap.array[0]);
    }
}
