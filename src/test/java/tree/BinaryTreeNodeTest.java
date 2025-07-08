package tree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

public class BinaryTreeNodeTest {

    private BinaryTreeNode root;

    @BeforeEach
    public void setUp() {
        root = new BinaryTreeNode(1);
    }

    // Constructor and basic properties tests
    @Test
    public void testConstructor() {
        BinaryTreeNode node = new BinaryTreeNode(5);
        assertEquals(5, node.getVal());
        assertNull(node.getLeft());
        assertNull(node.getRight());
    }

    @Test
    public void testGettersAndSetters() {
        BinaryTreeNode node = new BinaryTreeNode(10);

        // Test value getter/setter
        assertEquals(10, node.getVal());
        node.setVal(20);
        assertEquals(20, node.getVal());

        // Test left child getter/setter
        assertNull(node.getLeft());
        BinaryTreeNode leftChild = new BinaryTreeNode(5);
        node.setLeft(leftChild);
        assertEquals(leftChild, node.getLeft());
        assertEquals(5, node.getLeft().getVal());

        // Test right child getter/setter
        assertNull(node.getRight());
        BinaryTreeNode rightChild = new BinaryTreeNode(15);
        node.setRight(rightChild);
        assertEquals(rightChild, node.getRight());
        assertEquals(15, node.getRight().getVal());
    }

    @Test
    public void testZeroAndNegativeValues() {
        BinaryTreeNode zeroNode = new BinaryTreeNode(0);
        assertEquals(0, zeroNode.getVal());

        BinaryTreeNode negativeNode = new BinaryTreeNode(-10);
        assertEquals(-10, negativeNode.getVal());

        negativeNode.setVal(-20);
        assertEquals(-20, negativeNode.getVal());
    }

    // Pre-order traversal tests
    @Test
    public void testPreOrderTraversalSingleNode() {
        List<Integer> result = root.preOrderTraversal(root);
        assertEquals(Arrays.asList(1), result);
    }

    @Test
    public void testPreOrderTraversalNull() {
        List<Integer> result = root.preOrderTraversal(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testPreOrderTraversalSimpleTree() {
        /*
         *      1
         *     / \
         *    2   3
         */
        root.setLeft(new BinaryTreeNode(2));
        root.setRight(new BinaryTreeNode(3));

        List<Integer> result = root.preOrderTraversal(root);
        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    public void testPreOrderTraversalComplexTree() {
        /*
         *        1
         *       / \
         *      2   3
         *     / \   \
         *    4   5   6
         */
        root.setLeft(new BinaryTreeNode(2));
        root.setRight(new BinaryTreeNode(3));
        root.getLeft().setLeft(new BinaryTreeNode(4));
        root.getLeft().setRight(new BinaryTreeNode(5));
        root.getRight().setRight(new BinaryTreeNode(6));

        List<Integer> result = root.preOrderTraversal(root);
        assertEquals(Arrays.asList(1, 2, 4, 5, 3, 6), result);
    }

    @Test
    public void testPreOrderTraversalLeftSkewedTree() {
        /*
         *    1
         *   /
         *  2
         * /
         *3
         */
        root.setLeft(new BinaryTreeNode(2));
        root.getLeft().setLeft(new BinaryTreeNode(3));

        List<Integer> result = root.preOrderTraversal(root);
        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    public void testPreOrderTraversalRightSkewedTree() {
        /*
         * 1
         *  \
         *   2
         *    \
         *     3
         */
        root.setRight(new BinaryTreeNode(2));
        root.getRight().setRight(new BinaryTreeNode(3));

        List<Integer> result = root.preOrderTraversal(root);
        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    // In-order traversal tests
    @Test
    public void testInOrderTraversalSingleNode() {
        List<Integer> result = root.inOrderTraversal(root);
        assertEquals(Arrays.asList(1), result);
    }

    @Test
    public void testInOrderTraversalNull() {
        List<Integer> result = root.inOrderTraversal(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testInOrderTraversalSimpleTree() {
        /*
         *      2
         *     / \
         *    1   3
         */
        root.setVal(2);
        root.setLeft(new BinaryTreeNode(1));
        root.setRight(new BinaryTreeNode(3));

        List<Integer> result = root.inOrderTraversal(root);
        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    public void testInOrderTraversalComplexTree() {
        /*
         *        4
         *       / \
         *      2   6
         *     / \ / \
         *    1  3 5  7
         */
        root.setVal(4);
        root.setLeft(new BinaryTreeNode(2));
        root.setRight(new BinaryTreeNode(6));
        root.getLeft().setLeft(new BinaryTreeNode(1));
        root.getLeft().setRight(new BinaryTreeNode(3));
        root.getRight().setLeft(new BinaryTreeNode(5));
        root.getRight().setRight(new BinaryTreeNode(7));

        List<Integer> result = root.inOrderTraversal(root);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), result);
    }

    @Test
    public void testInOrderTraversalLeftSkewedTree() {
        /*
         *    3
         *   /
         *  2
         * /
         *1
         */
        root.setVal(3);
        root.setLeft(new BinaryTreeNode(2));
        root.getLeft().setLeft(new BinaryTreeNode(1));

        List<Integer> result = root.inOrderTraversal(root);
        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    // Post-order traversal tests
    @Test
    public void testPostOrderTraversalSingleNode() {
        List<Integer> result = root.postOrderTraversal(root);
        assertEquals(Arrays.asList(1), result);
    }

    @Test
    public void testPostOrderTraversalNull() {
        List<Integer> result = root.postOrderTraversal(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testPostOrderTraversalSimpleTree() {
        /*
         *      1
         *     / \
         *    2   3
         */
        root.setLeft(new BinaryTreeNode(2));
        root.setRight(new BinaryTreeNode(3));

        List<Integer> result = root.postOrderTraversal(root);
        assertEquals(Arrays.asList(2, 3, 1), result);
    }

    @Test
    public void testPostOrderTraversalComplexTree() {
        /*
         *        1
         *       / \
         *      2   3
         *     / \   \
         *    4   5   6
         */
        root.setLeft(new BinaryTreeNode(2));
        root.setRight(new BinaryTreeNode(3));
        root.getLeft().setLeft(new BinaryTreeNode(4));
        root.getLeft().setRight(new BinaryTreeNode(5));
        root.getRight().setRight(new BinaryTreeNode(6));

        List<Integer> result = root.postOrderTraversal(root);
        assertEquals(Arrays.asList(4, 5, 2, 6, 3, 1), result);
    }

    @Test
    public void testPostOrderTraversalRightSkewedTree() {
        /*
         * 1
         *  \
         *   2
         *    \
         *     3
         */
        root.setRight(new BinaryTreeNode(2));
        root.getRight().setRight(new BinaryTreeNode(3));

        List<Integer> result = root.postOrderTraversal(root);
        assertEquals(Arrays.asList(3, 2, 1), result);
    }

    // Level-order traversal tests
    @Test
    public void testLevelOrderTraversalSingleNode() {
        List<Integer> result = root.levelOrderTraversal(root);
        assertEquals(Arrays.asList(1), result);
    }

    @Test
    public void testLevelOrderTraversalNull() {
        List<Integer> result = root.levelOrderTraversal(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testLevelOrderTraversalSimpleTree() {
        /*
         *      1
         *     / \
         *    2   3
         */
        root.setLeft(new BinaryTreeNode(2));
        root.setRight(new BinaryTreeNode(3));

        List<Integer> result = root.levelOrderTraversal(root);
        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    public void testLevelOrderTraversalComplexTree() {
        /*
         *        1
         *       / \
         *      2   3
         *     / \   \
         *    4   5   6
         */
        root.setLeft(new BinaryTreeNode(2));
        root.setRight(new BinaryTreeNode(3));
        root.getLeft().setLeft(new BinaryTreeNode(4));
        root.getLeft().setRight(new BinaryTreeNode(5));
        root.getRight().setRight(new BinaryTreeNode(6));

        List<Integer> result = root.levelOrderTraversal(root);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), result);
    }

    @Test
    public void testLevelOrderTraversalFullBinaryTree() {
        /*
         *        1
         *       / \
         *      2   3
         *     / \ / \
         *    4  5 6  7
         */
        root.setLeft(new BinaryTreeNode(2));
        root.setRight(new BinaryTreeNode(3));
        root.getLeft().setLeft(new BinaryTreeNode(4));
        root.getLeft().setRight(new BinaryTreeNode(5));
        root.getRight().setLeft(new BinaryTreeNode(6));
        root.getRight().setRight(new BinaryTreeNode(7));

        List<Integer> result = root.levelOrderTraversal(root);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), result);
    }

    // Comprehensive traversal comparison tests
    @Test
    public void testAllTraversalsOnSameTree() {
        /*
         *        4
         *       / \
         *      2   6
         *     / \ / \
         *    1  3 5  7
         */
        root.setVal(4);
        root.setLeft(new BinaryTreeNode(2));
        root.setRight(new BinaryTreeNode(6));
        root.getLeft().setLeft(new BinaryTreeNode(1));
        root.getLeft().setRight(new BinaryTreeNode(3));
        root.getRight().setLeft(new BinaryTreeNode(5));
        root.getRight().setRight(new BinaryTreeNode(7));

        List<Integer> preOrder = root.preOrderTraversal(root);
        List<Integer> inOrder = root.inOrderTraversal(root);
        List<Integer> postOrder = root.postOrderTraversal(root);
        List<Integer> levelOrder = root.levelOrderTraversal(root);

        assertEquals(Arrays.asList(4, 2, 1, 3, 6, 5, 7), preOrder);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), inOrder);
        assertEquals(Arrays.asList(1, 3, 2, 5, 7, 6, 4), postOrder);
        assertEquals(Arrays.asList(4, 2, 6, 1, 3, 5, 7), levelOrder);
    }

    @Test
    public void testTraversalsWithNegativeValues() {
        /*
         *      0
         *     / \
         *   -2   2
         *   /   / \
         * -3   1   3
         */
        root.setVal(0);
        root.setLeft(new BinaryTreeNode(-2));
        root.setRight(new BinaryTreeNode(2));
        root.getLeft().setLeft(new BinaryTreeNode(-3));
        root.getRight().setLeft(new BinaryTreeNode(1));
        root.getRight().setRight(new BinaryTreeNode(3));

        List<Integer> inOrder = root.inOrderTraversal(root);
        assertEquals(Arrays.asList(-3, -2, 0, 1, 2, 3), inOrder);

        List<Integer> preOrder = root.preOrderTraversal(root);
        assertEquals(Arrays.asList(0, -2, -3, 2, 1, 3), preOrder);
    }

    @Test
    public void testTraversalsWithDuplicateValues() {
        /*
         *      5
         *     / \
         *    5   5
         *   /     \
         *  5       5
         */
        root.setVal(5);
        root.setLeft(new BinaryTreeNode(5));
        root.setRight(new BinaryTreeNode(5));
        root.getLeft().setLeft(new BinaryTreeNode(5));
        root.getRight().setRight(new BinaryTreeNode(5));

        List<Integer> levelOrder = root.levelOrderTraversal(root);
        assertEquals(Arrays.asList(5, 5, 5, 5, 5), levelOrder);

        List<Integer> inOrder = root.inOrderTraversal(root);
        assertEquals(Arrays.asList(5, 5, 5, 5, 5), inOrder);
    }

    // Edge case tests
    @Test
    public void testLargeTree() {
        /*
         * Create a larger tree to test performance and correctness
         *           10
         *         /    \
         *        5      15
         *       / \    /  \
         *      3   7  12  18
         *     /   /   \    \
         *    1   6    13   20
         */
        root.setVal(10);
        root.setLeft(new BinaryTreeNode(5));
        root.setRight(new BinaryTreeNode(15));
        root.getLeft().setLeft(new BinaryTreeNode(3));
        root.getLeft().setRight(new BinaryTreeNode(7));
        root.getRight().setLeft(new BinaryTreeNode(12));
        root.getRight().setRight(new BinaryTreeNode(18));
        root.getLeft().getLeft().setLeft(new BinaryTreeNode(1));
        root.getLeft().getRight().setLeft(new BinaryTreeNode(6));
        root.getRight().getLeft().setRight(new BinaryTreeNode(13));
        root.getRight().getRight().setRight(new BinaryTreeNode(20));

        List<Integer> inOrder = root.inOrderTraversal(root);
        assertEquals(Arrays.asList(1, 3, 5, 6, 7, 10, 12, 13, 15, 18, 20), inOrder);

        List<Integer> levelOrder = root.levelOrderTraversal(root);
        assertEquals(Arrays.asList(10, 5, 15, 3, 7, 12, 18, 1, 6, 13, 20), levelOrder);
    }

    @Test
    public void testOnlyLeftChildren() {
        /*
         * 1
         * \
         *  2
         *   \
         *    3
         *     \
         *      4
         */
        root.setRight(new BinaryTreeNode(2));
        root.getRight().setRight(new BinaryTreeNode(3));
        root.getRight().getRight().setRight(new BinaryTreeNode(4));

        List<Integer> preOrder = root.preOrderTraversal(root);
        assertEquals(Arrays.asList(1, 2, 3, 4), preOrder);

        List<Integer> inOrder = root.inOrderTraversal(root);
        assertEquals(Arrays.asList(1, 2, 3, 4), inOrder);

        List<Integer> postOrder = root.postOrderTraversal(root);
        assertEquals(Arrays.asList(4, 3, 2, 1), postOrder);

        List<Integer> levelOrder = root.levelOrderTraversal(root);
        assertEquals(Arrays.asList(1, 2, 3, 4), levelOrder);
    }
}
