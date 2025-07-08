package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeNode {
    private int val;
    private BinaryTreeNode left, right;

    public BinaryTreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public List<Integer> preOrderTraversal(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            BinaryTreeNode current = stack.pop();
            result.add(current.getVal());
            if(current.getRight() != null) {
                stack.push(current.getRight());
            }
            if(current.getLeft() != null) {
                stack.push(current.getLeft());
            }
        }
        return result;
    }

    public List<Integer> inOrderTraversal(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode current = root;
        while(current != null || !stack.isEmpty()) {
            while(current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            current = stack.pop();
            result.add(current.getVal());
            current = current.getRight();
        }
        return result;
    }

    public List<Integer> postOrderTraversal(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode current = root, lastVisited = null;
        while(current != null || !stack.isEmpty()) {
            while(current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            current = stack.peek();
            if(current.getRight() == null || current.getRight() == lastVisited) {
                result.add(current.getVal());
                lastVisited = stack.pop();
                current = null; // Set to null to continue with the next node
            } else {
                current = current.getRight();
            }
        }
        return result;
    }

    public List<Integer> levelOrderTraversal(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        List<BinaryTreeNode> queue = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            BinaryTreeNode current = queue.remove(0);
            result.add(current.getVal());
            if(current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if(current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
        return result;
    }
}
