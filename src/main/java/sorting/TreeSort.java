package sorting;

import tree.bst.BinarySearchTreeNode;
import java.util.ArrayList;
import java.util.List;

public class TreeSort {


    /**
     * Sorts an array of integers using Tree Sort algorithm.
     * Creates a BST from the array elements and performs in-order traversal.
     * Note: This implementation preserves duplicates.
     */
    public void treeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Create BST from array elements, preserving duplicates
        BSTWithDuplicates root = null;

        for (int value : arr) {
            root = insertWithDuplicates(root, value);
        }

        // Perform in-order traversal to get sorted elements
        List<Integer> sortedList = new ArrayList<>();
        inOrderTraversalWithDuplicates(root, sortedList);

        // Copy sorted elements back to array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sortedList.get(i);
        }
    }

    /**
     * BST Node that handles duplicates by maintaining count
     */
    private static class BSTWithDuplicates {
        int data;
        int count;
        BSTWithDuplicates left, right;

        BSTWithDuplicates(int data) {
            this.data = data;
            this.count = 1;
        }
    }

    /**
     * Insert method that handles duplicates by incrementing count
     */
    private BSTWithDuplicates insertWithDuplicates(BSTWithDuplicates root, int data) {
        if (root == null) {
            return new BSTWithDuplicates(data);
        }

        if (data < root.data) {
            root.left = insertWithDuplicates(root.left, data);
        } else if (data > root.data) {
            root.right = insertWithDuplicates(root.right, data);
        } else {
            // Equal value - increment count to preserve duplicates
            root.count++;
        }

        return root;
    }

    /**
     * In-order traversal that handles duplicates
     */
    private void inOrderTraversalWithDuplicates(BSTWithDuplicates node, List<Integer> result) {
        if (node != null) {
            inOrderTraversalWithDuplicates(node.left, result);

            // Add the value 'count' number of times to preserve duplicates
            for (int i = 0; i < node.count; i++) {
                result.add(node.data);
            }

            inOrderTraversalWithDuplicates(node.right, result);
        }
    }

    /**
     * Performs in-order traversal of BST to get elements in sorted order
     */
    private void inOrderTraversal(BinarySearchTreeNode node, List<Integer> result) {
        if (node != null) {
            inOrderTraversal(node.getLeft(), result);
            result.add(node.getData());
            inOrderTraversal(node.getRight(), result);
        }
    }

    /**
     * Generic version for List of Comparable objects
     */
    public <T extends Comparable<T>> void treeSort(List<T> list) {
        if (list == null || list.size() <= 1) {
            return;
        }

        // Create a generic BST for this sorting operation
        GenericBSTNode<T> root = null;

        for (T value : list) {
            root = insertGeneric(root, value);
        }

        // Perform in-order traversal to get sorted elements
        List<T> sortedList = new ArrayList<>();
        inOrderTraversalGeneric(root, sortedList);

        // Replace list contents with sorted elements
        list.clear();
        list.addAll(sortedList);
    }

    /**
     * Generic BST Node for handling Comparable types
     */
    private static class GenericBSTNode<T extends Comparable<T>> {
        T data;
        GenericBSTNode<T> left, right;

        GenericBSTNode(T data) {
            this.data = data;
        }
    }

    /**
     * Insert method for generic BST
     */
    private <T extends Comparable<T>> GenericBSTNode<T> insertGeneric(GenericBSTNode<T> root, T data) {
        if (root == null) {
            return new GenericBSTNode<>(data);
        }

        int comparison = data.compareTo(root.data);
        if (comparison < 0) {
            root.left = insertGeneric(root.left, data);
        } else if (comparison > 0) {
            root.right = insertGeneric(root.right, data);
        }
        // If equal, we don't insert duplicates (or you could modify to handle duplicates)

        return root;
    }

    /**
     * In-order traversal for generic BST
     */
    private <T extends Comparable<T>> void inOrderTraversalGeneric(GenericBSTNode<T> node, List<T> result) {
        if (node != null) {
            inOrderTraversalGeneric(node.left, result);
            result.add(node.data);
            inOrderTraversalGeneric(node.right, result);
        }
    }
}