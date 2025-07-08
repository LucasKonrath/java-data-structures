package queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class QueueLinkedListTest {

    private QueueLinkedList queue;

    @BeforeEach
    public void setUp() {
        queue = new QueueLinkedList();
    }

    // Constructor and initial state tests
    @Test
    public void testConstructor() {
        assertNotNull(queue);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testInitialState() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    // Enqueue operation tests
    @Test
    public void testEnqueueSingleElement() {
        queue.enqueue(10);

        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertEquals(10, queue.first());
    }

    @Test
    public void testEnqueueMultipleElements() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals(10, queue.first()); // First element should still be 10
    }

    @Test
    public void testEnqueueZeroValue() {
        queue.enqueue(0);

        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals(0, queue.first());
    }

    @Test
    public void testEnqueueNegativeValue() {
        queue.enqueue(-10);

        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals(-10, queue.first());
    }

    @Test
    public void testEnqueueLargeNumbers() {
        queue.enqueue(Integer.MAX_VALUE);
        queue.enqueue(Integer.MIN_VALUE);

        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals(Integer.MAX_VALUE, queue.first());
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

    // First (peek) operation tests
    @Test
    public void testFirstWithSingleElement() {
        queue.enqueue(10);

        assertEquals(10, queue.first());
        assertEquals(10, queue.first()); // Should return same value
        assertEquals(1, queue.size()); // Size should remain same
    }

    @Test
    public void testFirstWithMultipleElements() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(10, queue.first()); // Should always return first element
        assertEquals(3, queue.size()); // Size should remain same
    }

    @Test
    public void testFirstAfterDequeue() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        queue.dequeue(); // Remove 10
        assertEquals(20, queue.first()); // Now 20 should be first

        queue.dequeue(); // Remove 20
        assertEquals(30, queue.first()); // Now 30 should be first
    }

    @Test
    public void testFirstFromEmptyQueue() {
        assertThrows(IllegalStateException.class, () -> {
            queue.first();
        });
    }

    @Test
    public void testFirstFromEmptyQueueAfterOperations() {
        queue.enqueue(10);
        queue.dequeue();

        assertThrows(IllegalStateException.class, () -> {
            queue.first();
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
        int numberOfElements = 1000;

        // Enqueue elements
        for (int i = 0; i < numberOfElements; i++) {
            queue.enqueue(i);
        }

        assertEquals(numberOfElements, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals(0, queue.first()); // First element should be 0

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

        assertEquals(100, queue.first());
        assertEquals(100, queue.dequeue());
        assertEquals(2, queue.size());

        queue.enqueue(400);
        queue.enqueue(500);
        assertEquals(4, queue.size());
        assertEquals(200, queue.first());

        assertEquals(200, queue.dequeue());
        assertEquals(300, queue.dequeue());
        assertEquals(400, queue.dequeue());
        assertEquals(500, queue.dequeue());

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testConsistentStateAfterOperations() {
        // Ensure isEmpty() and size() are always consistent
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());

        queue.enqueue(42);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());

        queue.enqueue(84);
        assertFalse(queue.isEmpty());
        assertEquals(2, queue.size());

        queue.dequeue();
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());

        queue.dequeue();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testSequentialOperations() {
        // Test multiple cycles of enqueue/dequeue
        for (int cycle = 0; cycle < 5; cycle++) {
            for (int i = 0; i < 10; i++) {
                queue.enqueue(i + cycle * 10);
            }

            assertEquals(10, queue.size());
            assertEquals(cycle * 10, queue.first());

            for (int i = 0; i < 10; i++) {
                assertEquals(i + cycle * 10, queue.dequeue());
            }

            assertTrue(queue.isEmpty());
        }
    }

    @Test
    public void testFirstDoesNotModifyQueue() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        // Multiple calls to first() should not change the queue
        assertEquals(10, queue.first());
        assertEquals(10, queue.first());
        assertEquals(10, queue.first());
        assertEquals(3, queue.size());

        // Dequeue should still return the first element
        assertEquals(10, queue.dequeue());
        assertEquals(20, queue.first());
    }

    @Test
    public void testEmptyAfterDequeueAll() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        // Dequeue all elements
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        // Queue should be empty
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());

        // Operations on empty queue should throw exceptions
        assertThrows(IllegalStateException.class, () -> queue.dequeue());
        assertThrows(IllegalStateException.class, () -> queue.first());

        // Should be able to enqueue again
        queue.enqueue(100);
        assertEquals(100, queue.first());
        assertEquals(1, queue.size());
    }
}
