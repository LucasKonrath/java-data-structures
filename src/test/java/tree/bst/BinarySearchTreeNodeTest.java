package tree.bst;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeNodeTest {

    private BinarySearchTreeNode bst;

    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTreeNode(10);
    }

    // Constructor and basic properties tests
    @Test
    public void testConstructor() {
        BinarySearchTreeNode node = new BinarySearchTreeNode(5);
        assertEquals(5, node.getData());
        assertNull(node.getLeft());
        assertNull(node.getRight());
    }

    @Test
    public void testGettersAndSetters() {
        BinarySearchTreeNode node = new BinarySearchTreeNode(10);

        // Test data getter/setter
        assertEquals(10, node.getData());
        node.setData(20);
        assertEquals(20, node.getData());

        // Test left child getter/setter
        assertNull(node.getLeft());
        BinarySearchTreeNode leftChild = new BinarySearchTreeNode(5);
        node.setLeft(leftChild);
        assertEquals(leftChild, node.getLeft());
        assertEquals(5, node.getLeft().getData());

        // Test right child getter/setter
        assertNull(node.getRight());
        BinarySearchTreeNode rightChild = new BinarySearchTreeNode(15);
        node.setRight(rightChild);
        assertEquals(rightChild, node.getRight());
        assertEquals(15, node.getRight().getData());
    }

    @Test
    public void testZeroAndNegativeValues() {
        BinarySearchTreeNode zeroNode = new BinarySearchTreeNode(0);
        assertEquals(0, zeroNode.getData());

        BinarySearchTreeNode negativeNode = new BinarySearchTreeNode(-10);
        assertEquals(-10, negativeNode.getData());

        negativeNode.setData(-20);
        assertEquals(-20, negativeNode.getData());
    }

    // Insert operation tests
    @Test
    public void testInsertIntoEmptyTree() {
        BinarySearchTreeNode root = null;
        root = bst.insert(root, 10);

        assertNotNull(root);
        assertEquals(10, root.getData());
        assertNull(root.getLeft());
        assertNull(root.getRight());
    }

    @Test
    public void testInsertSmallerValue() {
        BinarySearchTreeNode root = bst.insert(null, 10);
        root = bst.insert(root, 5);

        assertEquals(10, root.getData());
        assertNotNull(root.getLeft());
        assertEquals(5, root.getLeft().getData());
        assertNull(root.getRight());
    }

    @Test
    public void testInsertLargerValue() {
        BinarySearchTreeNode root = bst.insert(null, 10);
        root = bst.insert(root, 15);

        assertEquals(10, root.getData());
        assertNull(root.getLeft());
        assertNotNull(root.getRight());
        assertEquals(15, root.getRight().getData());
    }

    @Test
    public void testInsertDuplicateValue() {
        BinarySearchTreeNode root = bst.insert(null, 10);
        root = bst.insert(root, 10);

        // Should not insert duplicate, tree structure unchanged
        assertEquals(10, root.getData());
        assertNull(root.getLeft());
        assertNull(root.getRight());
    }

    @Test
    public void testInsertMultipleValues() {
        /*
         * Building tree:
         *       10
         *      /  \
         *     5    15
         *    / \   / \
         *   3   7 12  20
         */
        BinarySearchTreeNode root = null;
        int[] values = {10, 5, 15, 3, 7, 12, 20};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        assertEquals(10, root.getData());
        assertEquals(5, root.getLeft().getData());
        assertEquals(15, root.getRight().getData());
        assertEquals(3, root.getLeft().getLeft().getData());
        assertEquals(7, root.getLeft().getRight().getData());
        assertEquals(12, root.getRight().getLeft().getData());
        assertEquals(20, root.getRight().getRight().getData());
    }

    @Test
    public void testInsertNegativeValues() {
        BinarySearchTreeNode root = null;
        int[] values = {0, -5, 5, -10, -3, 3, 10};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        assertEquals(0, root.getData());
        assertEquals(-5, root.getLeft().getData());
        assertEquals(5, root.getRight().getData());
    }

    // Find (recursive) operation tests
    @Test
    public void testFindInEmptyTree() {
        BinarySearchTreeNode result = bst.find(null, 10);
        assertNull(result);
    }

    @Test
    public void testFindRootNode() {
        BinarySearchTreeNode root = bst.insert(null, 10);
        BinarySearchTreeNode result = bst.find(root, 10);

        assertNotNull(result);
        assertEquals(10, result.getData());
    }

    @Test
    public void testFindExistingNode() {
        BinarySearchTreeNode root = null;
        int[] values = {10, 5, 15, 3, 7, 12, 20};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        BinarySearchTreeNode result = bst.find(root, 7);
        assertNotNull(result);
        assertEquals(7, result.getData());

        result = bst.find(root, 15);
        assertNotNull(result);
        assertEquals(15, result.getData());
    }

    @Test
    public void testFindNonExistingNode() {
        BinarySearchTreeNode root = null;
        int[] values = {10, 5, 15};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        BinarySearchTreeNode result = bst.find(root, 8);
        assertNull(result);

        result = bst.find(root, 25);
        assertNull(result);
    }

    // Find (non-recursive) operation tests
    @Test
    public void testFindNonRecurInEmptyTree() {
        BinarySearchTreeNode result = bst.findNonRecur(null, 10);
        assertNull(result);
    }

    @Test
    public void testFindNonRecurRootNode() {
        BinarySearchTreeNode root = bst.insert(null, 10);
        BinarySearchTreeNode result = bst.findNonRecur(root, 10);

        assertNotNull(result);
        assertEquals(10, result.getData());
    }

    @Test
    public void testFindNonRecurExistingNode() {
        BinarySearchTreeNode root = null;
        int[] values = {10, 5, 15, 3, 7, 12, 20};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        BinarySearchTreeNode result = bst.findNonRecur(root, 7);
        assertNotNull(result);
        assertEquals(7, result.getData());

        result = bst.findNonRecur(root, 20);
        assertNotNull(result);
        assertEquals(20, result.getData());
    }

    @Test
    public void testFindNonRecurNonExistingNode() {
        BinarySearchTreeNode root = null;
        int[] values = {10, 5, 15};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        BinarySearchTreeNode result = bst.findNonRecur(root, 8);
        assertNull(result);

        result = bst.findNonRecur(root, 25);
        assertNull(result);
    }

    // FindMin operation tests
    @Test
    public void testFindMinInEmptyTree() {
        BinarySearchTreeNode result = bst.findMin(null);
        assertNull(result);
    }

    @Test
    public void testFindMinSingleNode() {
        BinarySearchTreeNode root = bst.insert(null, 10);
        BinarySearchTreeNode result = bst.findMin(root);

        assertNotNull(result);
        assertEquals(10, result.getData());
    }

    @Test
    public void testFindMinInBalancedTree() {
        BinarySearchTreeNode root = null;
        int[] values = {10, 5, 15, 3, 7, 12, 20};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        BinarySearchTreeNode result = bst.findMin(root);
        assertNotNull(result);
        assertEquals(3, result.getData());
    }

    @Test
    public void testFindMinInLeftSkewedTree() {
        BinarySearchTreeNode root = null;
        int[] values = {10, 8, 6, 4, 2};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        BinarySearchTreeNode result = bst.findMin(root);
        assertNotNull(result);
        assertEquals(2, result.getData());
    }

    // FindMax operation tests
    @Test
    public void testFindMaxInEmptyTree() {
        BinarySearchTreeNode result = bst.findMax(null);
        assertNull(result);
    }

    @Test
    public void testFindMaxSingleNode() {
        BinarySearchTreeNode root = bst.insert(null, 10);
        BinarySearchTreeNode result = bst.findMax(root);

        assertNotNull(result);
        assertEquals(10, result.getData());
    }

    @Test
    public void testFindMaxInBalancedTree() {
        BinarySearchTreeNode root = null;
        int[] values = {10, 5, 15, 3, 7, 12, 20};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        BinarySearchTreeNode result = bst.findMax(root);
        assertNotNull(result);
        assertEquals(20, result.getData());
    }

    @Test
    public void testFindMaxInRightSkewedTree() {
        BinarySearchTreeNode root = null;
        int[] values = {2, 4, 6, 8, 10};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        BinarySearchTreeNode result = bst.findMax(root);
        assertNotNull(result);
        assertEquals(10, result.getData());
    }

    // Delete operation tests
    @Test
    public void testDeleteFromEmptyTree() {
        BinarySearchTreeNode result = bst.delete(null, 10);
        assertNull(result);
    }

    @Test
    public void testDeleteLeafNode() {
        BinarySearchTreeNode root = null;
        int[] values = {10, 5, 15, 3, 7};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        // Delete leaf node 3
        root = bst.delete(root, 3);
        assertNull(bst.find(root, 3));
        assertNull(root.getLeft().getLeft());
    }

    @Test
    public void testDeleteNodeWithOneChild() {
        BinarySearchTreeNode root = null;
        int[] values = {10, 5, 15, 3};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        // Delete node 5 which has only left child
        root = bst.delete(root, 5);
        assertNull(bst.find(root, 5));
        assertEquals(3, root.getLeft().getData());
    }

    @Test
    public void testDeleteNodeWithTwoChildren() {
        BinarySearchTreeNode root = null;
        int[] values = {10, 5, 15, 3, 7, 12, 20};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        // Delete node 5 which has two children
        root = bst.delete(root, 5);
        assertNull(bst.find(root, 5));

        // The inorder successor (7) should replace 5
        assertEquals(7, root.getLeft().getData());
        assertEquals(3, root.getLeft().getLeft().getData());
        assertNull(root.getLeft().getRight());
    }

    @Test
    public void testDeleteRootNode() {
        BinarySearchTreeNode root = null;
        int[] values = {10, 5, 15, 3, 7, 12, 20};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        // Delete root node
        root = bst.delete(root, 10);
        assertNull(bst.find(root, 10));

        // The inorder successor (12) should replace 10
        assertEquals(12, root.getData());
        assertEquals(5, root.getLeft().getData());
        assertEquals(15, root.getRight().getData());
    }

    @Test
    public void testDeleteNonExistingNode() {
        BinarySearchTreeNode root = null;
        int[] values = {10, 5, 15};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        // Try to delete non-existing node
        BinarySearchTreeNode originalRoot = root;
        root = bst.delete(root, 8);

        // Tree should remain unchanged
        assertEquals(originalRoot, root);
        assertEquals(10, root.getData());
        assertEquals(5, root.getLeft().getData());
        assertEquals(15, root.getRight().getData());
    }

    @Test
    public void testDeleteAllNodes() {
        BinarySearchTreeNode root = null;
        int[] values = {10, 5, 15};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        // Delete all nodes
        root = bst.delete(root, 5);
        root = bst.delete(root, 15);
        root = bst.delete(root, 10);

        assertNull(root);
    }

    // Complex scenario tests
    @Test
    public void testComplexOperationsSequence() {
        BinarySearchTreeNode root = null;

        // Insert values
        int[] insertValues = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45};
        for (int value : insertValues) {
            root = bst.insert(root, value);
        }

        // Verify some finds
        assertNotNull(bst.find(root, 25));
        assertNotNull(bst.findNonRecur(root, 35));
        assertNull(bst.find(root, 100));

        // Verify min/max
        assertEquals(10, bst.findMin(root).getData());
        assertEquals(80, bst.findMax(root).getData());

        // Delete some nodes
        root = bst.delete(root, 10); // leaf
        root = bst.delete(root, 30); // node with two children
        root = bst.delete(root, 70); // node with two children

        // Verify deletions
        assertNull(bst.find(root, 10));
        assertNull(bst.find(root, 30));
        assertNull(bst.find(root, 70));

        // Verify remaining structure
        assertNotNull(bst.find(root, 50));
        assertNotNull(bst.find(root, 20));
        assertNotNull(bst.find(root, 80));
    }

    @Test
    public void testBSTPropertyMaintained() {
        BinarySearchTreeNode root = null;
        int[] values = {10, 5, 15, 3, 7, 12, 20, 1, 4, 6, 8, 11, 13, 18, 25};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        // Verify BST property: all left subtree values < root < all right subtree values
        assertTrue(verifyBSTProperty(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void testOperationsWithNegativeNumbers() {
        BinarySearchTreeNode root = null;
        int[] values = {0, -10, 10, -15, -5, 5, 15};

        for (int value : values) {
            root = bst.insert(root, value);
        }

        assertEquals(-15, bst.findMin(root).getData());
        assertEquals(15, bst.findMax(root).getData());

        assertNotNull(bst.find(root, -5));
        assertNotNull(bst.findNonRecur(root, -10));

        root = bst.delete(root, -10);
        assertNull(bst.find(root, -10));
    }

    // Helper method to verify BST property
    private boolean verifyBSTProperty(BinarySearchTreeNode node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.getData() <= min || node.getData() >= max) {
            return false;
        }

        return verifyBSTProperty(node.getLeft(), min, node.getData()) &&
               verifyBSTProperty(node.getRight(), node.getData(), max);
    }
}
