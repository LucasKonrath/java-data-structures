package tree.avl;

public class AvlTreeNode {
    private int data;
    private AvlTreeNode left, right;
    private int height;

    public AvlTreeNode(int data) {
        this.data = data;
        this.height = 1; // New node is initially added at leaf
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public AvlTreeNode getLeft() {
        return left;
    }

    public void setLeft(AvlTreeNode left) {
        this.left = left;
    }

    public AvlTreeNode getRight() {
        return right;
    }

    public void setRight(AvlTreeNode right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int heigh(AvlTreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    public AvlTreeNode singleRotateLeft(AvlTreeNode z) {
        AvlTreeNode y = z.getRight();
        z.setRight(y.getLeft());
        y.setLeft(z);
        z.setHeight(Math.max(heigh(z.getLeft()), heigh(z.getRight())) + 1);
        y.setHeight(Math.max(heigh(y.getLeft()), heigh(y.getRight())) + 1);
        return y; // New root
    }

    public AvlTreeNode singleRotateRight(AvlTreeNode z) {
        AvlTreeNode y = z.getLeft();
        z.setLeft(y.getRight());
        y.setRight(z);
        z.setHeight(Math.max(heigh(z.getLeft()), heigh(z.getRight())) + 1);
        y.setHeight(Math.max(heigh(y.getLeft()), heigh(y.getRight())) + 1);
        return y; // New root
    }

    public AvlTreeNode doubleRotateWithLeft(AvlTreeNode z) {
        z.setLeft(singleRotateRight(z.getLeft()));
        return singleRotateLeft(z);
    }

    public AvlTreeNode doubleRotateWithRight(AvlTreeNode z) {
        z.setRight(singleRotateLeft(z.getRight()));
        return singleRotateRight(z);
    }

    public AvlTreeNode insert(AvlTreeNode root, AvlTreeNode parent, int data){
        if (root == null) {
            return new AvlTreeNode(data);
        }

        if (data < root.getData()) {
            root.setLeft(insert(root.getLeft(), root, data));
        } else if (data > root.getData()) {
            root.setRight(insert(root.getRight(), root, data));
        } else {
            // Duplicate data is not allowed in AVL tree
            return root;
        }

        // Update height of this ancestor node
        root.setHeight(Math.max(heigh(root.getLeft()), heigh(root.getRight())) + 1);

        // Get the balance factor
        int balance = heigh(root.getLeft()) - heigh(root.getRight());

        // If the node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && data < root.getLeft().getData()) {
            return singleRotateRight(root);
        }

        // Right Right Case
        if (balance < -1 && data > root.getRight().getData()) {
            return singleRotateLeft(root);
        }

        // Left Right Case
        if (balance > 1 && data > root.getLeft().getData()) {
            return doubleRotateWithLeft(root);
        }

        // Right Left Case
        if (balance < -1 && data < root.getRight().getData()) {
            return doubleRotateWithRight(root);
        }

        return root; // Return the unchanged node pointer
    }
}
