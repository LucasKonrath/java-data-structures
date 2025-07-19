package tree;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import tree.bst.BinarySearchTreeNode;
import tree.avl.AvlTreeNode;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class TreeBenchmark {

    private static final int SMALL_SIZE = 100;
    private static final int MEDIUM_SIZE = 1000;
    private static final int LARGE_SIZE = 10000;

    private BinaryTreeNode smallBinaryTree;
    private BinaryTreeNode mediumBinaryTree;
    private BinaryTreeNode largeBinaryTree;

    private BinarySearchTreeNode smallBST;
    private BinarySearchTreeNode mediumBST;
    private BinarySearchTreeNode largeBST;

    private AvlTreeNode smallAVL;
    private AvlTreeNode mediumAVL;
    private AvlTreeNode largeAVL;

    private int[] testData;
    private int[] sortedData;

    @Setup(Level.Trial)
    public void setUp() {
        Random random = new Random(42);
        testData = random.ints(LARGE_SIZE, 0, 10000).toArray();

        // Create sorted data for worst-case scenarios
        sortedData = new int[LARGE_SIZE];
        for (int i = 0; i < LARGE_SIZE; i++) {
            sortedData[i] = i;
        }
    }

    @Setup(Level.Invocation)
    public void setUpTrees() {
        // Build Binary Trees
        smallBinaryTree = buildBinaryTree(SMALL_SIZE);
        mediumBinaryTree = buildBinaryTree(MEDIUM_SIZE);
        largeBinaryTree = buildBinaryTree(LARGE_SIZE);

        // Build BSTs
        smallBST = buildBST(SMALL_SIZE);
        mediumBST = buildBST(MEDIUM_SIZE);
        largeBST = buildBST(LARGE_SIZE);

        // Build AVL Trees
        smallAVL = buildAVL(SMALL_SIZE);
        mediumAVL = buildAVL(MEDIUM_SIZE);
        largeAVL = buildAVL(LARGE_SIZE);
    }

    private BinaryTreeNode buildBinaryTree(int size) {
        if (size == 0) return null;
        BinaryTreeNode root = new BinaryTreeNode(testData[0]);

        // Build a complete binary tree
        BinaryTreeNode[] nodes = new BinaryTreeNode[size];
        nodes[0] = root;

        for (int i = 0; i < size; i++) {
            if (nodes[i] == null) continue;

            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;

            if (leftIndex < size) {
                nodes[leftIndex] = new BinaryTreeNode(testData[leftIndex]);
                nodes[i].setLeft(nodes[leftIndex]);
            }

            if (rightIndex < size) {
                nodes[rightIndex] = new BinaryTreeNode(testData[rightIndex]);
                nodes[i].setRight(nodes[rightIndex]);
            }
        }

        return root;
    }

    private BinarySearchTreeNode buildBST(int size) {
        BinarySearchTreeNode root = null;
        for (int i = 0; i < size; i++) {
            root = BinarySearchTreeNode.insert(root, testData[i]);
        }
        return root;
    }

    private AvlTreeNode buildAVL(int size) {
        AvlTreeNode root = null;
        for (int i = 0; i < size; i++) {
            root = AvlTreeNode.insert(root, testData[i]);
        }
        return root;
    }

    // Binary Tree Traversal Benchmarks
    @Benchmark
    public void binaryTreeInorderTraversalSmall() {
        inorderTraversal(smallBinaryTree);
    }

    @Benchmark
    public void binaryTreeInorderTraversalMedium() {
        inorderTraversal(mediumBinaryTree);
    }

    @Benchmark
    public void binaryTreeInorderTraversalLarge() {
        inorderTraversal(largeBinaryTree);
    }

    @Benchmark
    public void binaryTreePreorderTraversalSmall() {
        preorderTraversal(smallBinaryTree);
    }

    @Benchmark
    public void binaryTreePreorderTraversalMedium() {
        preorderTraversal(mediumBinaryTree);
    }

    @Benchmark
    public void binaryTreePreorderTraversalLarge() {
        preorderTraversal(largeBinaryTree);
    }

    @Benchmark
    public void binaryTreePostorderTraversalSmall() {
        postorderTraversal(smallBinaryTree);
    }

    @Benchmark
    public void binaryTreePostorderTraversalMedium() {
        postorderTraversal(mediumBinaryTree);
    }

    @Benchmark
    public void binaryTreePostorderTraversalLarge() {
        postorderTraversal(largeBinaryTree);
    }

    // BST Search Benchmarks
    @Benchmark
    public boolean bstSearchSmall() {
        return BinarySearchTreeNode.search(smallBST, testData[SMALL_SIZE / 2]);
    }

    @Benchmark
    public boolean bstSearchMedium() {
        return BinarySearchTreeNode.search(mediumBST, testData[MEDIUM_SIZE / 2]);
    }

    @Benchmark
    public boolean bstSearchLarge() {
        return BinarySearchTreeNode.search(largeBST, testData[LARGE_SIZE / 2]);
    }

    // BST Insertion Benchmarks
    @Benchmark
    public void bstInsertionSmall() {
        BinarySearchTreeNode root = null;
        for (int i = 0; i < SMALL_SIZE; i++) {
            root = BinarySearchTreeNode.insert(root, testData[i]);
        }
    }

    @Benchmark
    public void bstInsertionMedium() {
        BinarySearchTreeNode root = null;
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            root = BinarySearchTreeNode.insert(root, testData[i]);
        }
    }

    @Benchmark
    public void bstInsertionLarge() {
        BinarySearchTreeNode root = null;
        for (int i = 0; i < LARGE_SIZE; i++) {
            root = BinarySearchTreeNode.insert(root, testData[i]);
        }
    }

    // BST Worst Case (Sorted Data) Benchmarks
    @Benchmark
    public void bstWorstCaseInsertionSmall() {
        BinarySearchTreeNode root = null;
        for (int i = 0; i < SMALL_SIZE; i++) {
            root = BinarySearchTreeNode.insert(root, sortedData[i]);
        }
    }

    @Benchmark
    public void bstWorstCaseInsertionMedium() {
        BinarySearchTreeNode root = null;
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            root = BinarySearchTreeNode.insert(root, sortedData[i]);
        }
    }

    // AVL Tree Search Benchmarks
    @Benchmark
    public boolean avlSearchSmall() {
        return AvlTreeNode.search(smallAVL, testData[SMALL_SIZE / 2]);
    }

    @Benchmark
    public boolean avlSearchMedium() {
        return AvlTreeNode.search(mediumAVL, testData[MEDIUM_SIZE / 2]);
    }

    @Benchmark
    public boolean avlSearchLarge() {
        return AvlTreeNode.search(largeAVL, testData[LARGE_SIZE / 2]);
    }

    // AVL Tree Insertion Benchmarks
    @Benchmark
    public void avlInsertionSmall() {
        AvlTreeNode root = null;
        for (int i = 0; i < SMALL_SIZE; i++) {
            root = AvlTreeNode.insert(root, testData[i]);
        }
    }

    @Benchmark
    public void avlInsertionMedium() {
        AvlTreeNode root = null;
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            root = AvlTreeNode.insert(root, testData[i]);
        }
    }

    @Benchmark
    public void avlInsertionLarge() {
        AvlTreeNode root = null;
        for (int i = 0; i < LARGE_SIZE; i++) {
            root = AvlTreeNode.insert(root, testData[i]);
        }
    }

    // AVL Tree Worst Case (Sorted Data) Benchmarks
    @Benchmark
    public void avlWorstCaseInsertionSmall() {
        AvlTreeNode root = null;
        for (int i = 0; i < SMALL_SIZE; i++) {
            root = AvlTreeNode.insert(root, sortedData[i]);
        }
    }

    @Benchmark
    public void avlWorstCaseInsertionMedium() {
        AvlTreeNode root = null;
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            root = AvlTreeNode.insert(root, sortedData[i]);
        }
    }

    // Tree Height Calculation Benchmarks
    @Benchmark
    public int binaryTreeHeightSmall() {
        return calculateHeight(smallBinaryTree);
    }

    @Benchmark
    public int binaryTreeHeightMedium() {
        return calculateHeight(mediumBinaryTree);
    }

    @Benchmark
    public int binaryTreeHeightLarge() {
        return calculateHeight(largeBinaryTree);
    }

    // Helper methods for traversals
    private void inorderTraversal(BinaryTreeNode node) {
        if (node != null) {
            inorderTraversal(node.getLeft());
            node.getVal(); // Process node
            inorderTraversal(node.getRight());
        }
    }

    private void preorderTraversal(BinaryTreeNode node) {
        if (node != null) {
            node.getVal(); // Process node
            preorderTraversal(node.getLeft());
            preorderTraversal(node.getRight());
        }
    }

    private void postorderTraversal(BinaryTreeNode node) {
        if (node != null) {
            postorderTraversal(node.getLeft());
            postorderTraversal(node.getRight());
            node.getVal(); // Process node
        }
    }

    private int calculateHeight(BinaryTreeNode node) {
        if (node == null) return -1;
        return 1 + Math.max(calculateHeight(node.getLeft()), calculateHeight(node.getRight()));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TreeBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
