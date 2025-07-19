package tree;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

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

    private int[] testData;
    private int searchTarget;

    @Setup(Level.Trial)
    public void setUp() {
        Random random = new Random(42);
        testData = random.ints(LARGE_SIZE, 0, 10000).toArray();
        searchTarget = testData[LARGE_SIZE / 2]; // Target for search operations
    }

    @Setup(Level.Invocation)
    public void setUpTrees() {
        // Build Binary Trees
        smallBinaryTree = buildBinaryTree(SMALL_SIZE);
        mediumBinaryTree = buildBinaryTree(MEDIUM_SIZE);
        largeBinaryTree = buildBinaryTree(LARGE_SIZE);
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

    // Binary Tree Creation Benchmarks
    @Benchmark
    public BinaryTreeNode binaryTreeCreationSmall() {
        return buildBinaryTree(SMALL_SIZE);
    }

    @Benchmark
    public BinaryTreeNode binaryTreeCreationMedium() {
        return buildBinaryTree(MEDIUM_SIZE);
    }

    @Benchmark
    public BinaryTreeNode binaryTreeCreationLarge() {
        return buildBinaryTree(LARGE_SIZE);
    }

    // Binary Tree Traversal Benchmarks
    @Benchmark
    public int binaryTreeInorderTraversalSmall() {
        return inorderTraversal(smallBinaryTree);
    }

    @Benchmark
    public int binaryTreeInorderTraversalMedium() {
        return inorderTraversal(mediumBinaryTree);
    }

    @Benchmark
    public int binaryTreeInorderTraversalLarge() {
        return inorderTraversal(largeBinaryTree);
    }

    @Benchmark
    public int binaryTreePreorderTraversalSmall() {
        return preorderTraversal(smallBinaryTree);
    }

    @Benchmark
    public int binaryTreePreorderTraversalMedium() {
        return preorderTraversal(mediumBinaryTree);
    }

    @Benchmark
    public int binaryTreePreorderTraversalLarge() {
        return preorderTraversal(largeBinaryTree);
    }

    @Benchmark
    public int binaryTreePostorderTraversalSmall() {
        return postorderTraversal(smallBinaryTree);
    }

    @Benchmark
    public int binaryTreePostorderTraversalMedium() {
        return postorderTraversal(mediumBinaryTree);
    }

    @Benchmark
    public int binaryTreePostorderTraversalLarge() {
        return postorderTraversal(largeBinaryTree);
    }

    // Binary Tree Search Benchmarks (Linear search through tree)
    @Benchmark
    public boolean binaryTreeSearchSmall() {
        return searchInTree(smallBinaryTree, searchTarget);
    }

    @Benchmark
    public boolean binaryTreeSearchMedium() {
        return searchInTree(mediumBinaryTree, searchTarget);
    }

    @Benchmark
    public boolean binaryTreeSearchLarge() {
        return searchInTree(largeBinaryTree, searchTarget);
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

    // Tree Node Count Benchmarks
    @Benchmark
    public int binaryTreeNodeCountSmall() {
        return countNodes(smallBinaryTree);
    }

    @Benchmark
    public int binaryTreeNodeCountMedium() {
        return countNodes(mediumBinaryTree);
    }

    @Benchmark
    public int binaryTreeNodeCountLarge() {
        return countNodes(largeBinaryTree);
    }

    // Helper methods for traversals (return count to avoid dead code elimination)
    private int inorderTraversal(BinaryTreeNode node) {
        if (node == null) return 0;
        int count = 1;
        count += inorderTraversal(node.getLeft());
        count += inorderTraversal(node.getRight());
        return count;
    }

    private int preorderTraversal(BinaryTreeNode node) {
        if (node == null) return 0;
        int count = 1;
        count += preorderTraversal(node.getLeft());
        count += preorderTraversal(node.getRight());
        return count;
    }

    private int postorderTraversal(BinaryTreeNode node) {
        if (node == null) return 0;
        int count = 1;
        count += postorderTraversal(node.getLeft());
        count += postorderTraversal(node.getRight());
        return count;
    }

    private boolean searchInTree(BinaryTreeNode node, int target) {
        if (node == null) return false;
        if (node.getVal() == target) return true;
        return searchInTree(node.getLeft(), target) || searchInTree(node.getRight(), target);
    }

    private int calculateHeight(BinaryTreeNode node) {
        if (node == null) return -1;
        return 1 + Math.max(calculateHeight(node.getLeft()), calculateHeight(node.getRight()));
    }

    private int countNodes(BinaryTreeNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TreeBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
