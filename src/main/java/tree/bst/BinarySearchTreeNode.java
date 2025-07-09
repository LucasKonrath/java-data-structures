package tree.bst;

public class BinarySearchTreeNode {

    private int data;
    private BinarySearchTreeNode left, right;

    public BinarySearchTreeNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinarySearchTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinarySearchTreeNode left) {
        this.left = left;
    }

    public BinarySearchTreeNode getRight() {
        return right;
    }

    public void setRight(BinarySearchTreeNode right) {
        this.right = right;
    }

    public BinarySearchTreeNode find(BinarySearchTreeNode root, int data) {
        if (root == null || root.getData() == data) {
            return root;
        }
        if (data < root.getData()) {
            return find(root.getLeft(), data);
        } else {
            return find(root.getRight(), data);
        }
    }

    public BinarySearchTreeNode findNonRecur(BinarySearchTreeNode root, int data) {
        while (root != null) {
            if (data < root.getData()) {
                root = root.getLeft();
            } else if (data > root.getData()) {
                root = root.getRight();
            } else {
                return root; // Found the node
            }
        }
        return null; // Not found
    }

    public BinarySearchTreeNode findMin(BinarySearchTreeNode root) {
        if (root == null) {
            return null;
        }
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root;
    }

    public BinarySearchTreeNode findMax(BinarySearchTreeNode root) {
        if (root == null) {
            return null;
        }
        while (root.getRight() != null) {
            root = root.getRight();
        }
        return root;
    }

    public BinarySearchTreeNode insert(BinarySearchTreeNode root, int data) {
        if (root == null) {
            return new BinarySearchTreeNode(data);
        }
        if (data < root.getData()) {
            root.setLeft(insert(root.getLeft(), data));
        } else if (data > root.getData()) {
            root.setRight(insert(root.getRight(), data));
        }
        return root; // Return the unchanged node pointer
    }

    public BinarySearchTreeNode delete(BinarySearchTreeNode root, int data) {
        if (root == null) {
            return null; // Node not found
        }
        if (data < root.getData()) {
            root.setLeft(delete(root.getLeft(), data));
        } else if (data > root.getData()) {
            root.setRight(delete(root.getRight(), data));
        } else {
            // Node with one child or no child
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }
            // Node with two children: Get the inorder successor (smallest in the right subtree)
            BinarySearchTreeNode minNode = findMin(root.getRight());
            root.setData(minNode.getData());
            root.setRight(delete(root.getRight(), minNode.getData()));
        }
        return root; // Return the unchanged node pointer
    }

}
