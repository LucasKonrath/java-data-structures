package stack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class DynamicArrayStackTest {

    private DynamicArrayStack stack;

    @BeforeEach
    public void setUp() {
        stack = new DynamicArrayStack();
    }

    // Constructor tests
    @Test
    public void testDefaultConstructor() {
        assertNotNull(stack);
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void testConstructorWithValidCapacity() {
        DynamicArrayStack customStack = new DynamicArrayStack(10);
        assertNotNull(customStack);
        assertTrue(customStack.isEmpty());
        assertEquals(0, customStack.size());
    }

    @Test
    public void testConstructorWithMinimumCapacity() {
        DynamicArrayStack minStack = new DynamicArrayStack(1);
        assertNotNull(minStack);
        assertTrue(minStack.isEmpty());
        assertEquals(0, minStack.size());
    }

    @Test
    public void testConstructorWithInvalidCapacity() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DynamicArrayStack(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new DynamicArrayStack(-1);
        });
    }

    // Basic operations tests
    @Test
    public void testPushSingleElement() {
        stack.push(10);

        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals(10, stack.peek());
    }

    @Test
    public void testPushMultipleElements() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(3, stack.size());
        assertEquals(30, stack.peek());
    }

    @Test
    public void testPopSingleElement() {
        stack.push(10);
        int poppedValue = stack.pop();

        assertEquals(10, poppedValue);
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void testPopMultipleElements() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPeekWithoutModifying() {
        stack.push(10);
        stack.push(20);

        assertEquals(20, stack.peek());
        assertEquals(20, stack.peek()); // Should return same value
        assertEquals(2, stack.size()); // Size should remain same
    }

    // Exception tests
    @Test
    public void testPopFromEmptyStack() {
        assertThrows(IllegalStateException.class, () -> {
            stack.pop();
        });
    }

    @Test
    public void testPeekFromEmptyStack() {
        assertThrows(IllegalStateException.class, () -> {
            stack.peek();
        });
    }

    @Test
    public void testPopFromStackThatBecomesEmpty() {
        stack.push(10);
        stack.pop();

        assertThrows(IllegalStateException.class, () -> {
            stack.pop();
        });
    }

    // Dynamic resizing tests
    @Test
    public void testResizeOnFullCapacity() {
        // Create a small stack to test resizing
        DynamicArrayStack smallStack = new DynamicArrayStack(2);

        // Fill to capacity
        smallStack.push(10);
        smallStack.push(20);
        assertEquals(2, smallStack.size());

        // This should trigger resize
        smallStack.push(30);
        assertEquals(3, smallStack.size());
        assertEquals(30, smallStack.peek());
    }

    @Test
    public void testMultipleResizes() {
        // Start with small capacity
        DynamicArrayStack smallStack = new DynamicArrayStack(1);

        // Add elements to trigger multiple resizes
        for (int i = 1; i <= 10; i++) {
            smallStack.push(i);
        }

        assertEquals(10, smallStack.size());
        assertEquals(10, smallStack.peek());

        // Verify all elements are still there
        for (int i = 10; i >= 1; i--) {
            assertEquals(i, smallStack.pop());
        }
    }

    @Test
    public void testShrinkingWhenSizeBecomesQuarterOfCapacity() {
        // Create stack and fill it to trigger expansion
        DynamicArrayStack testStack = new DynamicArrayStack(4);

        // Fill beyond initial capacity to trigger resize
        for (int i = 1; i <= 10; i++) {
            testStack.push(i);
        }

        // Pop elements to trigger shrinking
        for (int i = 10; i >= 3; i--) {
            testStack.pop();
        }

        // Stack should still function correctly
        assertEquals(2, testStack.size());
        assertEquals(2, testStack.peek());
    }

    @Test
    public void testShrinkingDoesNotGoBelowMinCapacity() {
        DynamicArrayStack testStack = new DynamicArrayStack(1);

        // Add and remove elements
        testStack.push(10);
        testStack.push(20);
        testStack.push(30);

        // Pop all elements
        testStack.pop();
        testStack.pop();
        testStack.pop();

        // Stack should still be functional
        assertTrue(testStack.isEmpty());
        testStack.push(40);
        assertEquals(40, testStack.peek());
    }

    // LIFO (Last In, First Out) behavior tests
    @Test
    public void testLIFOBehavior() {
        int[] values = {1, 2, 3, 4, 5};

        // Push values
        for (int value : values) {
            stack.push(value);
        }

        // Pop values and verify LIFO order
        for (int i = values.length - 1; i >= 0; i--) {
            assertEquals(values[i], stack.pop());
        }
    }

    // Edge case tests
    @Test
    public void testPushZeroValue() {
        stack.push(0);

        assertEquals(1, stack.size());
        assertEquals(0, stack.peek());
        assertEquals(0, stack.pop());
    }

    @Test
    public void testPushNegativeValues() {
        stack.push(-10);
        stack.push(-20);

        assertEquals(2, stack.size());
        assertEquals(-20, stack.peek());
        assertEquals(-20, stack.pop());
        assertEquals(-10, stack.pop());
    }

    @Test
    public void testMixedPositiveNegativeValues() {
        stack.push(-5);
        stack.push(0);
        stack.push(5);
        stack.push(-10);

        assertEquals(-10, stack.pop());
        assertEquals(5, stack.pop());
        assertEquals(0, stack.pop());
        assertEquals(-5, stack.pop());
    }

    // Large data tests
    @Test
    public void testLargeNumberOfElements() {
        int numberOfElements = 1000;

        // Push many elements
        for (int i = 0; i < numberOfElements; i++) {
            stack.push(i);
        }

        assertEquals(numberOfElements, stack.size());
        assertEquals(numberOfElements - 1, stack.peek());

        // Pop all elements
        for (int i = numberOfElements - 1; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }

        assertTrue(stack.isEmpty());
    }

    // State verification tests
    @Test
    public void testIsEmptyAfterOperations() {
        assertTrue(stack.isEmpty());

        stack.push(10);
        assertFalse(stack.isEmpty());

        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testSizeAfterOperations() {
        assertEquals(0, stack.size());

        stack.push(10);
        assertEquals(1, stack.size());

        stack.push(20);
        assertEquals(2, stack.size());

        stack.pop();
        assertEquals(1, stack.size());

        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    public void testAlternatingPushPop() {
        stack.push(1);
        assertEquals(1, stack.pop());

        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());

        stack.push(4);
        assertEquals(4, stack.pop());
        assertEquals(2, stack.pop());

        assertTrue(stack.isEmpty());
    }

    @Test
    public void testStackIntegrity() {
        // Test that internal array is properly managed
        stack.push(100);
        stack.push(200);
        stack.push(300);

        assertEquals(300, stack.peek());
        assertEquals(300, stack.pop());
        assertEquals(200, stack.peek());

        stack.push(400);
        assertEquals(400, stack.peek());
        assertEquals(400, stack.pop());
        assertEquals(200, stack.pop());
        assertEquals(100, stack.pop());

        assertTrue(stack.isEmpty());
    }
}
