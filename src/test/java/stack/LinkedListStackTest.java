package stack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListStackTest {

    private LinkedListStack stack;

    @BeforeEach
    public void setUp() {
        stack = new LinkedListStack();
    }

    // Constructor and initial state tests
    @Test
    public void testConstructor() {
        assertNotNull(stack);
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void testInitialState() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    // Push operation tests
    @Test
    public void testPushSingleElement() {
        stack.push(10);

        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
    }

    @Test
    public void testPushMultipleElements() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(3, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testPushZeroValue() {
        stack.push(0);

        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testPushNegativeValue() {
        stack.push(-10);

        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testPushLargeNumbers() {
        stack.push(Integer.MAX_VALUE);
        stack.push(Integer.MIN_VALUE);

        assertEquals(2, stack.size());
        assertFalse(stack.isEmpty());
    }

    // Pop operation tests
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
        assertEquals(0, stack.size());
    }

    @Test
    public void testPopZeroValue() {
        stack.push(0);
        int poppedValue = stack.pop();

        assertEquals(0, poppedValue);
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPopNegativeValue() {
        stack.push(-10);
        int poppedValue = stack.pop();

        assertEquals(-10, poppedValue);
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPopLargeNumbers() {
        stack.push(Integer.MAX_VALUE);
        stack.push(Integer.MIN_VALUE);

        assertEquals(Integer.MIN_VALUE, stack.pop());
        assertEquals(Integer.MAX_VALUE, stack.pop());
        assertTrue(stack.isEmpty());
    }

    // Exception handling tests
    @Test
    public void testPopFromEmptyStack() {
        assertThrows(IllegalStateException.class, () -> {
            stack.pop();
        });
    }

    @Test
    public void testPopFromEmptyStackAfterOperations() {
        stack.push(10);
        stack.pop();

        assertThrows(IllegalStateException.class, () -> {
            stack.pop();
        });
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

    @Test
    public void testLIFOWithMixedValues() {
        stack.push(-5);
        stack.push(0);
        stack.push(10);
        stack.push(-20);

        assertEquals(-20, stack.pop());
        assertEquals(10, stack.pop());
        assertEquals(0, stack.pop());
        assertEquals(-5, stack.pop());
    }

    // Size method tests
    @Test
    public void testSizeAfterPushOperations() {
        assertEquals(0, stack.size());

        stack.push(10);
        assertEquals(1, stack.size());

        stack.push(20);
        assertEquals(2, stack.size());

        stack.push(30);
        assertEquals(3, stack.size());
    }

    @Test
    public void testSizeAfterPopOperations() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        assertEquals(3, stack.size());

        stack.pop();
        assertEquals(2, stack.size());

        stack.pop();
        assertEquals(1, stack.size());

        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    public void testSizeAfterMixedOperations() {
        assertEquals(0, stack.size());

        stack.push(10);
        assertEquals(1, stack.size());

        stack.push(20);
        assertEquals(2, stack.size());

        stack.pop();
        assertEquals(1, stack.size());

        stack.push(30);
        assertEquals(2, stack.size());

        stack.pop();
        stack.pop();
        assertEquals(0, stack.size());
    }

    // isEmpty method tests
    @Test
    public void testIsEmptyInitially() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsEmptyAfterPush() {
        stack.push(10);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testIsEmptyAfterPushAndPop() {
        stack.push(10);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsEmptyAfterMultipleOperations() {
        stack.push(10);
        stack.push(20);
        assertFalse(stack.isEmpty());

        stack.pop();
        assertFalse(stack.isEmpty());

        stack.pop();
        assertTrue(stack.isEmpty());
    }

    // Edge cases and stress tests
    @Test
    public void testAlternatingPushPop() {
        stack.push(1);
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());

        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(1, stack.size());

        stack.push(4);
        assertEquals(4, stack.pop());
        assertEquals(2, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testLargeNumberOfElements() {
        int numberOfElements = 1000;

        // Push many elements
        for (int i = 0; i < numberOfElements; i++) {
            stack.push(i);
        }

        assertEquals(numberOfElements, stack.size());
        assertFalse(stack.isEmpty());

        // Pop all elements and verify order
        for (int i = numberOfElements - 1; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void testSequentialOperations() {
        // Test multiple cycles of push/pop
        for (int cycle = 0; cycle < 5; cycle++) {
            for (int i = 0; i < 10; i++) {
                stack.push(i + cycle * 10);
            }

            assertEquals(10, stack.size());

            for (int i = 9; i >= 0; i--) {
                assertEquals(i + cycle * 10, stack.pop());
            }

            assertTrue(stack.isEmpty());
        }
    }

    @Test
    public void testStackIntegrity() {
        // Test that stack maintains integrity after various operations
        stack.push(100);
        stack.push(200);
        stack.push(300);

        assertEquals(300, stack.pop());
        assertEquals(2, stack.size());

        stack.push(400);
        stack.push(500);
        assertEquals(4, stack.size());

        assertEquals(500, stack.pop());
        assertEquals(400, stack.pop());
        assertEquals(200, stack.pop());
        assertEquals(100, stack.pop());

        assertTrue(stack.isEmpty());
    }

    @Test
    public void testConsistentStateAfterOperations() {
        // Ensure isEmpty() and size() are always consistent
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        stack.push(42);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());

        stack.push(84);
        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());

        stack.pop();
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());

        stack.pop();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }
}
