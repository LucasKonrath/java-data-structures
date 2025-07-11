package sets.disjoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DisjointSetTest {

    private DisjointSet disjointSet;

    @BeforeEach
    void setUp() {
        disjointSet = new DisjointSet();
    }

    @Test
    void testMakeSet() {
        disjointSet.makeSet(5);

        assertEquals(5, disjointSet.size);
        assertNotNull(disjointSet.s);
        assertEquals(5, disjointSet.s.length);

        // Each element should be its own parent initially (represented by -1)
        for (int i = 0; i < 5; i++) {
            assertEquals(-1, disjointSet.s[i]);
        }
    }

    @Test
    void testMakeSetWithZeroSize() {
        disjointSet.makeSet(0);

        assertEquals(0, disjointSet.size);
        assertNotNull(disjointSet.s);
        assertEquals(0, disjointSet.s.length);
    }

    @Test
    void testMakeSetWithLargeSize() {
        disjointSet.makeSet(1000);

        assertEquals(1000, disjointSet.size);
        assertEquals(1000, disjointSet.s.length);

        // Verify all elements are initialized correctly
        for (int i = 0; i < 1000; i++) {
            assertEquals(-1, disjointSet.s[i]);
        }
    }

    @Test
    void testFindOnSingletonSets() {
        disjointSet.makeSet(5);

        // Each element should be its own root initially
        for (int i = 0; i < 5; i++) {
            assertEquals(i, disjointSet.find(i));
        }
    }

    @Test
    void testFindAfterUnion() {
        disjointSet.makeSet(5);

        // Union elements 0 and 1
        disjointSet.union(0, 1);

        // Both elements should have the same root
        int root0 = disjointSet.find(0);
        int root1 = disjointSet.find(1);
        assertEquals(root0, root1);

        // The root should be either 0 or 1
        assertTrue(root0 == 0 || root0 == 1);
    }

    @Test
    void testUnionBasic() {
        disjointSet.makeSet(4);

        // Initially, all elements are in separate sets
        assertNotEquals(disjointSet.find(0), disjointSet.find(1));
        assertNotEquals(disjointSet.find(2), disjointSet.find(3));

        // Union 0 and 1
        disjointSet.union(0, 1);
        assertEquals(disjointSet.find(0), disjointSet.find(1));

        // Union 2 and 3
        disjointSet.union(2, 3);
        assertEquals(disjointSet.find(2), disjointSet.find(3));

        // 0,1 should still be separate from 2,3
        assertNotEquals(disjointSet.find(0), disjointSet.find(2));
    }

    @Test
    void testUnionSameSet() {
        disjointSet.makeSet(3);

        // Union 0 and 1
        disjointSet.union(0, 1);
        int originalRoot = disjointSet.find(0);

        // Union the same elements again - should not change anything
        disjointSet.union(0, 1);
        assertEquals(originalRoot, disjointSet.find(0));
        assertEquals(originalRoot, disjointSet.find(1));
    }

    @Test
    void testUnionBySize() {
        disjointSet.makeSet(4);

        // Create a set with 3 elements: union 0,1 then union result with 2
        disjointSet.union(0, 1);
        disjointSet.union(0, 2);

        // Now union this larger set with singleton 3
        disjointSet.union(0, 3);

        // All elements should be in the same set
        int root = disjointSet.find(0);
        assertEquals(root, disjointSet.find(1));
        assertEquals(root, disjointSet.find(2));
        assertEquals(root, disjointSet.find(3));

        // The root should have size -4 (negative because it's a root)
        assertTrue(disjointSet.s[root] <= -4);
    }

    @Test
    void testPathCompression() {
        disjointSet.makeSet(5);

        // Create a chain: 0 -> 1 -> 2 -> 3 -> 4
        disjointSet.union(0, 1);
        disjointSet.union(1, 2);
        disjointSet.union(2, 3);
        disjointSet.union(3, 4);

        // Find should trigger path compression
        int root = disjointSet.find(0);

        // After path compression, calling find again should be more efficient
        // All elements should point directly to the root
        assertEquals(root, disjointSet.find(0));
        assertEquals(root, disjointSet.find(1));
        assertEquals(root, disjointSet.find(2));
        assertEquals(root, disjointSet.find(3));
        assertEquals(root, disjointSet.find(4));
    }

    @Test
    void testMultipleUnions() {
        disjointSet.makeSet(8);

        // Create two separate components
        disjointSet.union(0, 1);
        disjointSet.union(1, 2);
        disjointSet.union(2, 3);

        disjointSet.union(4, 5);
        disjointSet.union(5, 6);
        disjointSet.union(6, 7);

        // Verify two separate components
        int root1 = disjointSet.find(0);
        int root2 = disjointSet.find(4);
        assertNotEquals(root1, root2);

        // All elements in first component should have same root
        assertEquals(root1, disjointSet.find(0));
        assertEquals(root1, disjointSet.find(1));
        assertEquals(root1, disjointSet.find(2));
        assertEquals(root1, disjointSet.find(3));

        // All elements in second component should have same root
        assertEquals(root2, disjointSet.find(4));
        assertEquals(root2, disjointSet.find(5));
        assertEquals(root2, disjointSet.find(6));
        assertEquals(root2, disjointSet.find(7));

        // Now union the two components
        disjointSet.union(0, 4);

        // All elements should now be in the same set
        int finalRoot = disjointSet.find(0);
        for (int i = 0; i < 8; i++) {
            assertEquals(finalRoot, disjointSet.find(i));
        }
    }

    @Test
    void testConnectedComponents() {
        disjointSet.makeSet(6);

        // Create connections: (0,1), (2,3), (4,5)
        disjointSet.union(0, 1);
        disjointSet.union(2, 3);
        disjointSet.union(4, 5);

        // Should have 3 separate components
        assertNotEquals(disjointSet.find(0), disjointSet.find(2));
        assertNotEquals(disjointSet.find(0), disjointSet.find(4));
        assertNotEquals(disjointSet.find(2), disjointSet.find(4));

        // But elements within each component should be connected
        assertEquals(disjointSet.find(0), disjointSet.find(1));
        assertEquals(disjointSet.find(2), disjointSet.find(3));
        assertEquals(disjointSet.find(4), disjointSet.find(5));
    }

    @Test
    void testSetSizes() {
        disjointSet.makeSet(5);

        // Initially all sets have size 1 (represented as -1)
        for (int i = 0; i < 5; i++) {
            assertEquals(-1, disjointSet.s[i]);
        }

        // Union 0 and 1 - should create a set of size 2
        disjointSet.union(0, 1);
        int root = disjointSet.find(0);
        assertTrue(disjointSet.s[root] <= -2);

        // Union with another element - should create a set of size 3
        disjointSet.union(0, 2);
        root = disjointSet.find(0);
        assertTrue(disjointSet.s[root] <= -3);
    }

    @Test
    void testIsConnected() {
        disjointSet.makeSet(5);

        // Create a helper method to check if two elements are connected
        // (they are connected if they have the same root)

        // Initially, no elements are connected
        assertFalse(areConnected(0, 1));
        assertFalse(areConnected(0, 2));
        assertFalse(areConnected(1, 2));

        // Connect 0 and 1
        disjointSet.union(0, 1);
        assertTrue(areConnected(0, 1));
        assertFalse(areConnected(0, 2));

        // Connect 1 and 2 (which connects 0 and 2 transitively)
        disjointSet.union(1, 2);
        assertTrue(areConnected(0, 1));
        assertTrue(areConnected(1, 2));
        assertTrue(areConnected(0, 2));
    }

    @Test
    void testLargeDataset() {
        int n = 1000;
        disjointSet.makeSet(n);

        // Union pairs: (0,1), (2,3), (4,5), ..., (998,999)
        for (int i = 0; i < n; i += 2) {
            disjointSet.union(i, i + 1);
        }

        // Should have n/2 components
        // Verify pairs are connected
        for (int i = 0; i < n; i += 2) {
            assertTrue(areConnected(i, i + 1));
        }

        // Verify non-pairs are not connected
        for (int i = 0; i < n - 2; i += 2) {
            assertFalse(areConnected(i, i + 2));
        }
    }

    @Test
    void testUnionChain() {
        disjointSet.makeSet(10);

        // Create a chain: 0-1-2-3-4-5-6-7-8-9
        for (int i = 0; i < 9; i++) {
            disjointSet.union(i, i + 1);
        }

        // All elements should be in the same set
        int root = disjointSet.find(0);
        for (int i = 1; i < 10; i++) {
            assertEquals(root, disjointSet.find(i));
        }

        // The root should represent a set of size 10
        assertTrue(disjointSet.s[root] <= -10);
    }

    @Test
    void testRepeatedOperations() {
        disjointSet.makeSet(5);

        // Perform multiple operations
        disjointSet.union(0, 1);
        disjointSet.union(2, 3);
        disjointSet.union(0, 2); // This connects all four elements

        // All should be connected
        int root = disjointSet.find(0);
        assertEquals(root, disjointSet.find(1));
        assertEquals(root, disjointSet.find(2));
        assertEquals(root, disjointSet.find(3));

        // Element 4 should still be separate
        assertNotEquals(root, disjointSet.find(4));

        // Connect element 4
        disjointSet.union(0, 4);
        assertEquals(root, disjointSet.find(4));
    }

    @Test
    void testSingleElementSet() {
        disjointSet.makeSet(1);

        assertEquals(1, disjointSet.size);
        assertEquals(-1, disjointSet.s[0]);
        assertEquals(0, disjointSet.find(0));

        // Union with itself should not change anything
        disjointSet.union(0, 0);
        assertEquals(0, disjointSet.find(0));
        assertEquals(-1, disjointSet.s[0]);
    }

    // Helper method to check if two elements are connected
    private boolean areConnected(int x, int y) {
        return disjointSet.find(x) == disjointSet.find(y);
    }
}
