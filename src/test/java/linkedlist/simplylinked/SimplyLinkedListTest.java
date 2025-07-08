package linkedlist.simplylinked;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class SimplyLinkedListTest {

    private SimplyLinkedList list;

    @BeforeEach
    public void setUp() {
        list = new SimplyLinkedList();
    }

    // Constructor and initial state tests
    @Test
    public void testConstructor() {
        assertNull(list.getHead());
    }

    @Test
    public void testInitialState() {
        assertNull(list.getHead());
    }

    // Insert at beginning tests
    @Test
    public void testInsertAtBeginningEmptyList() {
        list.insertAtBeginning(10);

        assertNotNull(list.getHead());
        assertEquals(10, list.getHead().getData());
        assertNull(list.getHead().getNext());
    }

    @Test
    public void testInsertAtBeginningNonEmptyList() {
        list.insertAtBeginning(10);
        list.insertAtBeginning(20);
        list.insertAtBeginning(30);

        assertEquals(30, list.getHead().getData());
        assertEquals(20, list.getHead().getNext().getData());
        assertEquals(10, list.getHead().getNext().getNext().getData());
        assertNull(list.getHead().getNext().getNext().getNext());
    }

    // Insert at end tests
    @Test
    public void testInsertAtEndEmptyList() {
        list.insertAtEnd(10);

        assertNotNull(list.getHead());
        assertEquals(10, list.getHead().getData());
        assertNull(list.getHead().getNext());
    }

    @Test
    public void testInsertAtEndNonEmptyList() {
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);

        assertEquals(10, list.getHead().getData());
        assertEquals(20, list.getHead().getNext().getData());
        assertEquals(30, list.getHead().getNext().getNext().getData());
        assertNull(list.getHead().getNext().getNext().getNext());
    }

    // Insert at position tests
    @Test
    public void testInsertAtPositionBeginning() {
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertData(5, 0);

        assertEquals(5, list.getHead().getData());
        assertEquals(10, list.getHead().getNext().getData());
        assertEquals(20, list.getHead().getNext().getNext().getData());
    }

    @Test
    public void testInsertAtPositionMiddle() {
        list.insertAtEnd(10);
        list.insertAtEnd(30);
        list.insertData(20, 1);

        assertEquals(10, list.getHead().getData());
        assertEquals(20, list.getHead().getNext().getData());
        assertEquals(30, list.getHead().getNext().getNext().getData());
    }

    @Test
    public void testInsertAtPositionEnd() {
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertData(30, 2);

        assertEquals(10, list.getHead().getData());
        assertEquals(20, list.getHead().getNext().getData());
        assertEquals(30, list.getHead().getNext().getNext().getData());
    }

    @Test
    public void testInsertAtPositionEmptyList() {
        list.insertData(10, 0);

        assertNotNull(list.getHead());
        assertEquals(10, list.getHead().getData());
        assertNull(list.getHead().getNext());
    }

    @Test
    public void testInsertAtPositionOutOfBounds() {
        list.insertAtEnd(10);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.insertData(20, 2);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.insertData(20, -1);
        });
    }

    // Remove from beginning tests
    @Test
    public void testRemoveFromBeginningEmptyList() {
        LinkedListNode removed = list.removeFromBeginning();
        assertNull(removed);
        assertNull(list.getHead());
    }

    @Test
    public void testRemoveFromBeginningOneElement() {
        list.insertAtEnd(10);
        LinkedListNode removed = list.removeFromBeginning();

        assertNotNull(removed);
        assertEquals(10, removed.getData());
        assertNull(list.getHead());
    }

    @Test
    public void testRemoveFromBeginningMultipleElements() {
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);

        LinkedListNode removed = list.removeFromBeginning();

        assertNotNull(removed);
        assertEquals(10, removed.getData());
        assertEquals(20, list.getHead().getData());
        assertEquals(30, list.getHead().getNext().getData());
    }

    // Remove from end tests
    @Test
    public void testRemoveFromEndEmptyList() {
        LinkedListNode removed = list.removeFromEnd();
        assertNull(removed);
        assertNull(list.getHead());
    }

    @Test
    public void testRemoveFromEndOneElement() {
        list.insertAtEnd(10);
        LinkedListNode removed = list.removeFromEnd();

        assertNotNull(removed);
        assertEquals(10, removed.getData());
        assertNull(list.getHead());
    }

    @Test
    public void testRemoveFromEndTwoElements() {
        list.insertAtEnd(10);
        list.insertAtEnd(20);

        LinkedListNode removed = list.removeFromEnd();

        assertNotNull(removed);
        assertEquals(20, removed.getData());
        assertEquals(10, list.getHead().getData());
        assertNull(list.getHead().getNext());
    }

    @Test
    public void testRemoveFromEndMultipleElements() {
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);

        LinkedListNode removed = list.removeFromEnd();

        assertNotNull(removed);
        assertEquals(30, removed.getData());
        assertEquals(10, list.getHead().getData());
        assertEquals(20, list.getHead().getNext().getData());
        assertNull(list.getHead().getNext().getNext());
    }

    // Remove at position tests
    @Test
    public void testRemoveAtPositionBeginning() {
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);

        list.remove(0);

        assertEquals(20, list.getHead().getData());
        assertEquals(30, list.getHead().getNext().getData());
        assertNull(list.getHead().getNext().getNext());
    }

    @Test
    public void testRemoveAtPositionMiddle() {
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);

        list.remove(1);

        assertEquals(10, list.getHead().getData());
        assertEquals(30, list.getHead().getNext().getData());
        assertNull(list.getHead().getNext().getNext());
    }

    @Test
    public void testRemoveAtPositionEnd() {
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);

        list.remove(2);

        assertEquals(10, list.getHead().getData());
        assertEquals(20, list.getHead().getNext().getData());
        assertNull(list.getHead().getNext().getNext());
    }

    @Test
    public void testRemoveAtPositionOutOfBounds() {
        list.insertAtEnd(10);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-1);
        });
    }

    @Test
    public void testRemoveAtPositionEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(0);
        });
    }

    // Complex scenario tests
    @Test
    public void testMixedOperations() {
        // Insert some elements
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtBeginning(5);
        list.insertData(15, 2);

        // List should be: 5 -> 10 -> 15 -> 20
        assertEquals(5, list.getHead().getData());
        assertEquals(10, list.getHead().getNext().getData());
        assertEquals(15, list.getHead().getNext().getNext().getData());
        assertEquals(20, list.getHead().getNext().getNext().getNext().getData());

        // Remove some elements
        list.remove(1); // Remove 10
        LinkedListNode removed = list.removeFromBeginning(); // Remove 5

        assertEquals(5, removed.getData());
        assertEquals(15, list.getHead().getData());
        assertEquals(20, list.getHead().getNext().getData());
        assertNull(list.getHead().getNext().getNext());
    }

    @Test
    public void testLargeList() {
        // Insert many elements
        for (int i = 0; i < 100; i++) {
            list.insertAtEnd(i);
        }

        // Verify first and last elements
        assertEquals(0, list.getHead().getData());

        // Navigate to last element
        LinkedListNode current = list.getHead();
        int count = 0;
        while (current.getNext() != null) {
            current = current.getNext();
            count++;
        }
        assertEquals(99, current.getData());
        assertEquals(99, count);
    }

    @Test
    public void testRemoveAllElements() {
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);

        list.removeFromBeginning();
        list.removeFromEnd();
        list.remove(0);

        assertNull(list.getHead());
    }

    @Test
    public void testAlternatingInsertRemove() {
        list.insertAtEnd(10);
        assertEquals(10, list.getHead().getData());

        list.removeFromBeginning();
        assertNull(list.getHead());

        list.insertAtBeginning(20);
        assertEquals(20, list.getHead().getData());

        list.removeFromEnd();
        assertNull(list.getHead());
    }

    @Test
    public void testInsertAtSamePosition() {
        list.insertData(10, 0);
        list.insertData(20, 0);
        list.insertData(30, 0);

        assertEquals(30, list.getHead().getData());
        assertEquals(20, list.getHead().getNext().getData());
        assertEquals(10, list.getHead().getNext().getNext().getData());
    }
}
