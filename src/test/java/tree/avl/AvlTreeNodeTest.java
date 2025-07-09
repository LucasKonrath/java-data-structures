package tree.avl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class AvlTreeNodeTest {

    private AvlTreeNode avl;

    @BeforeEach
    public void setUp() {
        avl = new AvlTreeNode(10);
    }

    // Constructor and basic properties tests
    @Test
    public void testConstructor() {
        AvlTreeNode node = new AvlTreeNode(5);
        assertEquals(5, node.getData());
        assertNull(node.getLeft());
        assertNull(node.getRight());
        assertEquals(1, node.getHeight()); // New nodes start with height 1
    }

    @Test
    public void testGettersAndSetters() {
        AvlTreeNode node = new AvlTreeNode(10);

        // Test data getter/setter
        assertEquals(10, node.getData());
        node.setData(20);
        assertEquals(20, node.getData());

        // Test height getter/setter
        assertEquals(1, node.getHeight());
        node.setHeight(3);
        assertEquals(3, node.getHeight());

        // Test left child getter/setter
        assertNull(node.getLeft());
        AvlTreeNode leftChild = new AvlTreeNode(5);
        node.setLeft(leftChild);
        assertEquals(leftChild, node.getLeft());
        assertEquals(5, node.getLeft().getData());

        // Test right child getter/setter
        assertNull(node.getRight());
        AvlTreeNode rightChild = new AvlTreeNode(15);
        node.setRight(rightChild);
        assertEquals(rightChild, node.getRight());
        assertEquals(15, node.getRight().getData());
    }

    @Test
    public void testZeroAndNegativeValues() {
        AvlTreeNode zeroNode = new AvlTreeNode(0);
        assertEquals(0, zeroNode.getData());

        AvlTreeNode negativeNode = new AvlTreeNode(-10);
        assertEquals(-10, negativeNode.getData());

        negativeNode.setData(-20);
        assertEquals(-20, negativeNode.getData());
    }

    // Height method tests
    @Test
    public void testHeightNullNode() {
        int height = avl.heigh(null);
        assertEquals(0, height);
    }

    @Test
    public void testHeightSingleNode() {
        AvlTreeNode node = new AvlTreeNode(5);
        int height = avl.heigh(node);
        assertEquals(1, height);
    }

    @Test
    public void testHeightCustomValue() {
        AvlTreeNode node = new AvlTreeNode(5);
        node.setHeight(3);
        int height = avl.heigh(node);
        assertEquals(3, height);
    }

    // Single rotation tests - these work with simple structures
    @Test
    public void testSingleRotateRightBasic() {
        /*
         * Before rotation:
         *       z(3)
         *      /
         *     y(2)
         *    /
         *   x(1)
         *
         * After rotation:
         *     y(2)
         *    /   \
         *   x(1) z(3)
         */
        AvlTreeNode z = new AvlTreeNode(3);
        AvlTreeNode y = new AvlTreeNode(2);
        AvlTreeNode x = new AvlTreeNode(1);

        z.setLeft(y);
        y.setLeft(x);
        // Set heights properly
        x.setHeight(1);
        y.setHeight(2);
        z.setHeight(3);

        AvlTreeNode newRoot = avl.singleRotateRight(z);

        assertEquals(2, newRoot.getData());
        assertEquals(1, newRoot.getLeft().getData());
        assertEquals(3, newRoot.getRight().getData());
    }

    @Test
    public void testSingleRotateLeftBasic() {
        /*
         * Before rotation:
         *   z(1)
         *      \
         *       y(2)
         *        \
         *         x(3)
         *
         * After rotation:
         *     y(2)
         *    /   \
         *   z(1) x(3)
         */
        AvlTreeNode z = new AvlTreeNode(1);
        AvlTreeNode y = new AvlTreeNode(2);
        AvlTreeNode x = new AvlTreeNode(3);

        z.setRight(y);
        y.setRight(x);
        // Set heights properly
        x.setHeight(1);
        y.setHeight(2);
        z.setHeight(3);

        AvlTreeNode newRoot = avl.singleRotateLeft(z);

        assertEquals(2, newRoot.getData());
        assertEquals(1, newRoot.getLeft().getData());
        assertEquals(3, newRoot.getRight().getData());
    }


    // Insert operation tests
    @Test
    public void testInsertIntoEmptyTree() {
        AvlTreeNode root = null;
        root = avl.insert(root, null, 10);

        assertNotNull(root);
        assertEquals(10, root.getData());
        assertEquals(1, root.getHeight());
        assertNull(root.getLeft());
        assertNull(root.getRight());
    }

    @Test
    public void testInsertSmallerValue() {
        AvlTreeNode root = avl.insert(null, null, 10);
        root = avl.insert(root, null, 5);

        assertEquals(10, root.getData());
        assertNotNull(root.getLeft());
        assertEquals(5, root.getLeft().getData());
        assertNull(root.getRight());
        assertEquals(2, root.getHeight());
    }

    @Test
    public void testInsertLargerValue() {
        AvlTreeNode root = avl.insert(null, null, 10);
        root = avl.insert(root, null, 15);

        assertEquals(10, root.getData());
        assertNull(root.getLeft());
        assertNotNull(root.getRight());
        assertEquals(15, root.getRight().getData());
        assertEquals(2, root.getHeight());
    }

    @Test
    public void testInsertDuplicateValue() {
        AvlTreeNode root = avl.insert(null, null, 10);
        root = avl.insert(root, null, 10);

        // Should not insert duplicate, tree structure unchanged
        assertEquals(10, root.getData());
        assertNull(root.getLeft());
        assertNull(root.getRight());
        assertEquals(1, root.getHeight());
    }

    @Test
    public void testInsertTriggersLeftLeftRotation() {
        /*
         * Insert sequence: 30, 20, 10
         * This should trigger a Right rotation (LL case)
         *
         * Before rotation:
         *   30
         *   /
         *  20
         *  /
         * 10
         *
         * After rotation:
         *    20
         *   /  \
         *  10  30
         */
        AvlTreeNode root = null;
        root = avl.insert(root, null, 30);
        root = avl.insert(root, null, 20);
        root = avl.insert(root, null, 10);

        assertEquals(20, root.getData());
        assertEquals(10, root.getLeft().getData());
        assertEquals(30, root.getRight().getData());
        assertEquals(2, root.getHeight());
    }

    @Test
    public void testInsertTriggersRightRightRotation() {
        /*
         * Insert sequence: 10, 20, 30
         * This should trigger a Left rotation (RR case)
         *
         * Before rotation:
         * 10
         *  \
         *  20
         *   \
         *   30
         *
         * After rotation:
         *    20
         *   /  \
         *  10  30
         */
        AvlTreeNode root = null;
        root = avl.insert(root, null, 10);
        root = avl.insert(root, null, 20);
        root = avl.insert(root, null, 30);

        assertEquals(20, root.getData());
        assertEquals(10, root.getLeft().getData());
        assertEquals(30, root.getRight().getData());
        assertEquals(2, root.getHeight());
    }




    @Test
    public void testComplexInsertionSequence() {
        /*
         * Insert sequence: 50, 25, 75, 10, 30, 60, 80, 5, 15, 27, 35
         * This should maintain AVL balance throughout
         */
        AvlTreeNode root = null;
        int[] values = {50, 25, 75, 10, 30, 60, 80, 5, 15, 27, 35};

        for (int value : values) {
            root = avl.insert(root, null, value);
            assertTrue(isAVLBalanced(root), "Tree should remain balanced after inserting " + value);
        }

        // Verify all values are present
        for (int value : values) {
            assertTrue(contains(root, value), "Tree should contain " + value);
        }
    }

    @Test
    public void testInsertNegativeValues() {
        AvlTreeNode root = null;
        int[] values = {0, -10, 10, -5, 5, -15, 15};

        for (int value : values) {
            root = avl.insert(root, null, value);
            assertTrue(isAVLBalanced(root), "Tree should remain balanced with negative values");
        }

        assertEquals(0, root.getData()); // Root should be 0 for this sequence
        assertTrue(contains(root, -15));
        assertTrue(contains(root, 15));
    }

    @Test
    public void testInsertLargeSequence() {
        AvlTreeNode root = null;

        // Insert values 1 through 15 in order
        for (int i = 1; i <= 15; i++) {
            root = avl.insert(root, null, i);
            assertTrue(isAVLBalanced(root), "Tree should remain balanced after inserting " + i);
        }

        // Verify all values are present
        for (int i = 1; i <= 15; i++) {
            assertTrue(contains(root, i), "Tree should contain " + i);
        }

        // Tree should be well-balanced
        int height = getTreeHeight(root);
        assertTrue(height <= 4, "Height should be logarithmic: " + height);
    }

    @Test
    public void testInsertReverseSequence() {
        AvlTreeNode root = null;

        // Insert values 15 through 1 in reverse order
        for (int i = 15; i >= 1; i--) {
            root = avl.insert(root, null, i);
            assertTrue(isAVLBalanced(root), "Tree should remain balanced after inserting " + i);
        }

        // Verify all values are present
        for (int i = 1; i <= 15; i++) {
            assertTrue(contains(root, i), "Tree should contain " + i);
        }

        // Tree should be well-balanced
        int height = getTreeHeight(root);
        assertTrue(height <= 4, "Height should be logarithmic: " + height);
    }

    @Test
    public void testHeightUpdateAfterRotations() {
        AvlTreeNode root = null;
        root = avl.insert(root, null, 10);
        root = avl.insert(root, null, 20);
        root = avl.insert(root, null, 30);

        // After RR rotation, heights should be correctly updated
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(1, root.getRight().getHeight());

        // Add more nodes to test height propagation
        root = avl.insert(root, null, 40);
        root = avl.insert(root, null, 50);

        assertTrue(isHeightCorrect(root), "Heights should be correctly maintained");
    }

    @Test
    public void testBalancePropertyMaintained() {
        AvlTreeNode root = null;

        // Insert random sequence
        int[] values = {15, 10, 20, 8, 12, 16, 25, 6, 11, 13, 27};

        for (int value : values) {
            root = avl.insert(root, null, value);

            // Verify AVL property is maintained
            assertTrue(isAVLBalanced(root), "AVL balance property violated after inserting " + value);
            assertTrue(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE), "BST property violated");
        }
    }

    // Helper methods for testing
    private boolean contains(AvlTreeNode root, int value) {
        if (root == null) {
            return false;
        }
        if (root.getData() == value) {
            return true;
        }
        if (value < root.getData()) {
            return contains(root.getLeft(), value);
        } else {
            return contains(root.getRight(), value);
        }
    }

    private boolean isAVLBalanced(AvlTreeNode root) {
        if (root == null) {
            return true;
        }

        int leftHeight = avl.heigh(root.getLeft());
        int rightHeight = avl.heigh(root.getRight());
        int balance = leftHeight - rightHeight;

        return Math.abs(balance) <= 1 &&
               isAVLBalanced(root.getLeft()) &&
               isAVLBalanced(root.getRight());
    }

    private boolean isBST(AvlTreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }

        return root.getData() > min &&
               root.getData() < max &&
               isBST(root.getLeft(), min, root.getData()) &&
               isBST(root.getRight(), root.getData(), max);
    }

    private int getTreeHeight(AvlTreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getTreeHeight(root.getLeft()), getTreeHeight(root.getRight())) + 1;
    }

    private boolean isHeightCorrect(AvlTreeNode root) {
        if (root == null) {
            return true;
        }

        int leftHeight = avl.heigh(root.getLeft());
        int rightHeight = avl.heigh(root.getRight());
        int expectedHeight = Math.max(leftHeight, rightHeight) + 1;

        return root.getHeight() == expectedHeight &&
               isHeightCorrect(root.getLeft()) &&
               isHeightCorrect(root.getRight());
    }
}
