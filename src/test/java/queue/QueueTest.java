package queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {

    private Queue queue;

    @BeforeEach
    public void setUp() {
        queue = new Queue();
    }

    // Constructor and initial state tests
    @Test
    public void testDefaultConstructor() {
        assertNotNull(queue);
        assertTrue(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(0, queue.size());
    }

    @Test
    public void testConstructorWithCapacity() {
        Queue customQueue = new Queue(5);
        assertNotNull(customQueue);
        assertTrue(customQueue.isEmpty());
        assertFalse(customQueue.isFull());
        assertEquals(0, customQueue.size());
    }

    @Test
    public void testInitialState() {
        assertTrue(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(0, queue.size());
    }

    // Enqueue operation tests
    @Test
    public void testEnqueueSingleElement() {
        queue.enqueue(10);

        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(1, queue.size());
    }

    @Test
    public void testEnqueueMultipleElements() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());
    }

    @Test
    public void testEnqueueZeroValue() {
        queue.enqueue(0);

        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testEnqueueNegativeValue() {
        queue.enqueue(-10);

        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testEnqueueLargeNumbers() {
        queue.enqueue(Integer.MAX_VALUE);
        queue.enqueue(Integer.MIN_VALUE);

        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testEnqueueToFullCapacity() {
        Queue smallQueue = new Queue(3);

        smallQueue.enqueue(10);
        smallQueue.enqueue(20);
        smallQueue.enqueue(30);

        assertTrue(smallQueue.isFull());
        assertEquals(3, smallQueue.size());
    }

    @Test
    public void testEnqueueWhenFull() {
        Queue smallQueue = new Queue(2);
        smallQueue.enqueue(10);
        smallQueue.enqueue(20);

        assertThrows(IllegalStateException.class, () -> {
            smallQueue.enqueue(30);
        });
    }

    // Dequeue operation tests
    @Test
    public void testDequeueSingleElement() {
        queue.enqueue(10);
        int dequeuedValue = queue.dequeue();

        assertEquals(10, dequeuedValue);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testDequeueMultipleElements() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(10, queue.dequeue());
        assertEquals(20, queue.dequeue());
        assertEquals(30, queue.dequeue());
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testDequeueZeroValue() {
        queue.enqueue(0);
        int dequeuedValue = queue.dequeue();

        assertEquals(0, dequeuedValue);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDequeueNegativeValue() {
        queue.enqueue(-10);
        int dequeuedValue = queue.dequeue();

        assertEquals(-10, dequeuedValue);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDequeueLargeNumbers() {
        queue.enqueue(Integer.MAX_VALUE);
        queue.enqueue(Integer.MIN_VALUE);

        assertEquals(Integer.MAX_VALUE, queue.dequeue());
        assertEquals(Integer.MIN_VALUE, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDequeueFromEmptyQueue() {
        assertThrows(IllegalStateException.class, () -> {
            queue.dequeue();
        });
    }

    @Test
    public void testDequeueFromEmptyQueueAfterOperations() {
        queue.enqueue(10);
        queue.dequeue();

        assertThrows(IllegalStateException.class, () -> {
            queue.dequeue();
        });
    }

    // FIFO (First In, First Out) behavior tests
    @Test
    public void testFIFOBehavior() {
        int[] values = {1, 2, 3, 4, 5};

        // Enqueue values
        for (int value : values) {
            queue.enqueue(value);
        }

        // Dequeue values and verify FIFO order
        for (int value : values) {
            assertEquals(value, queue.dequeue());
        }
    }

    @Test
    public void testFIFOWithMixedValues() {
        queue.enqueue(-5);
        queue.enqueue(0);
        queue.enqueue(10);
        queue.enqueue(-20);

        assertEquals(-5, queue.dequeue());
        assertEquals(0, queue.dequeue());
        assertEquals(10, queue.dequeue());
        assertEquals(-20, queue.dequeue());
    }

    // Size method tests
    @Test
    public void testSizeAfterEnqueueOperations() {
        assertEquals(0, queue.size());

        queue.enqueue(10);
        assertEquals(1, queue.size());

        queue.enqueue(20);
        assertEquals(2, queue.size());

        queue.enqueue(30);
        assertEquals(3, queue.size());
    }

    @Test
    public void testSizeAfterDequeueOperations() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        assertEquals(3, queue.size());

        queue.dequeue();
        assertEquals(2, queue.size());

        queue.dequeue();
        assertEquals(1, queue.size());

        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    public void testSizeAfterMixedOperations() {
        assertEquals(0, queue.size());

        queue.enqueue(10);
        assertEquals(1, queue.size());

        queue.enqueue(20);
        assertEquals(2, queue.size());

        queue.dequeue();
        assertEquals(1, queue.size());

        queue.enqueue(30);
        assertEquals(2, queue.size());

        queue.dequeue();
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    // isEmpty method tests
    @Test
    public void testIsEmptyInitially() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsEmptyAfterEnqueue() {
        queue.enqueue(10);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testIsEmptyAfterEnqueueAndDequeue() {
        queue.enqueue(10);
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsEmptyAfterMultipleOperations() {
        queue.enqueue(10);
        queue.enqueue(20);
        assertFalse(queue.isEmpty());

        queue.dequeue();
        assertFalse(queue.isEmpty());

        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    // isFull method tests
    @Test
    public void testIsFullInitially() {
        assertFalse(queue.isFull());
    }

    @Test
    public void testIsFullWithSmallQueue() {
        Queue smallQueue = new Queue(2);
        assertFalse(smallQueue.isFull());

        smallQueue.enqueue(10);
        assertFalse(smallQueue.isFull());

        smallQueue.enqueue(20);
        assertTrue(smallQueue.isFull());
    }

    @Test
    public void testIsFullAfterDequeue() {
        Queue smallQueue = new Queue(2);
        smallQueue.enqueue(10);
        smallQueue.enqueue(20);
        assertTrue(smallQueue.isFull());

        smallQueue.dequeue();
        assertFalse(smallQueue.isFull());
    }

    // Circular array behavior tests
    @Test
    public void testCircularArrayBehavior() {
        Queue smallQueue = new Queue(3);

        // Fill the queue
        smallQueue.enqueue(10);
        smallQueue.enqueue(20);
        smallQueue.enqueue(30);
        assertTrue(smallQueue.isFull());

        // Dequeue some elements
        assertEquals(10, smallQueue.dequeue());
        assertEquals(20, smallQueue.dequeue());

        // Enqueue more elements (should wrap around)
        smallQueue.enqueue(40);
        smallQueue.enqueue(50);
        assertTrue(smallQueue.isFull());

        // Verify correct order
        assertEquals(30, smallQueue.dequeue());
        assertEquals(40, smallQueue.dequeue());
        assertEquals(50, smallQueue.dequeue());
        assertTrue(smallQueue.isEmpty());
    }

    @Test
    public void testCircularArrayMultipleCycles() {
        Queue smallQueue = new Queue(3);

        // Multiple cycles of fill and empty
        for (int cycle = 0; cycle < 5; cycle++) {
            smallQueue.enqueue(1 + cycle * 10);
            smallQueue.enqueue(2 + cycle * 10);
            smallQueue.enqueue(3 + cycle * 10);

            assertEquals(1 + cycle * 10, smallQueue.dequeue());
            assertEquals(2 + cycle * 10, smallQueue.dequeue());
            assertEquals(3 + cycle * 10, smallQueue.dequeue());

            assertTrue(smallQueue.isEmpty());
        }
    }

    // Edge cases and stress tests
    @Test
    public void testAlternatingEnqueueDequeue() {
        queue.enqueue(1);
        assertEquals(1, queue.dequeue());
        assertTrue(queue.isEmpty());

        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(2, queue.dequeue());
        assertEquals(1, queue.size());

        queue.enqueue(4);
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testLargeNumberOfElements() {
        // Test with default capacity (16)
        int numberOfElements = 10;

        // Enqueue elements
        for (int i = 0; i < numberOfElements; i++) {
            queue.enqueue(i);
        }

        assertEquals(numberOfElements, queue.size());
        assertFalse(queue.isEmpty());

        // Dequeue all elements and verify order
        for (int i = 0; i < numberOfElements; i++) {
            assertEquals(i, queue.dequeue());
        }

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testQueueIntegrity() {
        // Test that queue maintains integrity after various operations
        queue.enqueue(100);
        queue.enqueue(200);
        queue.enqueue(300);

        assertEquals(100, queue.dequeue());
        assertEquals(2, queue.size());

        queue.enqueue(400);
        queue.enqueue(500);
        assertEquals(4, queue.size());

        assertEquals(200, queue.dequeue());
        assertEquals(300, queue.dequeue());
        assertEquals(400, queue.dequeue());
        assertEquals(500, queue.dequeue());

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testConsistentStateAfterOperations() {
        // Ensure isEmpty(), isFull(), and size() are always consistent
        assertTrue(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(0, queue.size());

        queue.enqueue(42);
        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(1, queue.size());

        queue.enqueue(84);
        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(2, queue.size());

        queue.dequeue();
        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(1, queue.size());

        queue.dequeue();
        assertTrue(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(0, queue.size());
    }

    @Test
    public void testWrappingWithPartialFill() {
        Queue smallQueue = new Queue(4);

        // Partially fill queue
        smallQueue.enqueue(1);
        smallQueue.enqueue(2);

        // Dequeue one element
        assertEquals(1, smallQueue.dequeue());

        // Fill to capacity
        smallQueue.enqueue(3);
        smallQueue.enqueue(4);
        smallQueue.enqueue(5);
        assertTrue(smallQueue.isFull());

        // Dequeue all and verify order
        assertEquals(2, smallQueue.dequeue());
        assertEquals(3, smallQueue.dequeue());
        assertEquals(4, smallQueue.dequeue());
        assertEquals(5, smallQueue.dequeue());
        assertTrue(smallQueue.isEmpty());
    }
}
