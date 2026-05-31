class AVLNode {
    int key;
    AVLNode left, right;
    int height;

    AVLNode(int key) {
        this.key = key;
        this.height = 1;
    }
}

public class AVLTree {

    static int height(AVLNode n) {
        return (n == null) ? 0 : n.height;
    }

    static int balance(AVLNode n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    static void updateHeight(AVLNode n) {
        if (n != null)
            n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    static AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode t2 = x.right;

        x.right = y;
        y.left = t2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    static AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode t2 = y.left;

        y.left = x;
        x.right = t2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    static AVLNode insert(AVLNode node, int key) {

        if (node == null)
            return new AVLNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        updateHeight(node);

        int bf = balance(node);

        // LL
        if (bf > 1 && key < node.left.key)
            return rotateRight(node);

        // RR
        if (bf < -1 && key > node.right.key)
            return rotateLeft(node);

        // LR
        if (bf > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // RL
        if (bf < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    static AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    static AVLNode deleteNode(AVLNode root, int key) {

        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteNode(root.left, key);

        else if (key > root.key)
            root.right = deleteNode(root.right, key);

        else {

            if ((root.left == null) || (root.right == null)) {

                AVLNode temp;

                if (root.left != null)
                    temp = root.left;
                else
                    temp = root.right;

                if (temp == null) {
                    root = null;
                } else {
                    root = temp;
                }
            } else {

                AVLNode temp = minValueNode(root.right);

                root.key = temp.key;

                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        updateHeight(root);

        int bf = balance(root);

        // LL
        if (bf > 1 && balance(root.left) >= 0)
            return rotateRight(root);

        // LR
        if (bf > 1 && balance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        // RR
        if (bf < -1 && balance(root.right) <= 0)
            return rotateLeft(root);

        // RL
        if (bf < -1 && balance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    static void preorder(AVLNode root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static void main(String[] args) {

        int[] ids = {20, 30, 35, 40, 45, 50, 60, 65, 70, 75, 80, 85, 90};

        AVLNode root = null;

        for (int id : ids)
            root = insert(root, id);

        System.out.println("AVL Tree after Insertions:");
        preorder(root);

        root = deleteNode(root, 30);
        System.out.println("\n\nAfter deleting 30:");
        preorder(root);

        root = deleteNode(root, 70);
        System.out.println("\n\nAfter deleting 70:");
        preorder(root);

        root = deleteNode(root, 50);
        System.out.println("\n\nAfter deleting 50:");
        preorder(root);

        System.out.println("\n\nHeight of AVL Tree = " + (height(root) - 1));

        int avlDepth = height(root) - 1;
        double avlTimeNs = avlDepth * 200.0;
        double avlTimeMs = avlTimeNs / 1000000.0;
        double avlPercent = (avlTimeMs / 5.0) * 100;

        int bstDepth = 12;
        double bstTimeNs = bstDepth * 200.0;
        double bstTimeMs = bstTimeNs / 1000000.0;
        double bstPercent = (bstTimeMs / 5.0) * 100;

        System.out.println("\nWorst Case Lookup Time:");
        System.out.println("AVL = " + avlTimeNs + " ns (" + avlTimeMs + " ms)");
        System.out.println("BST = " + bstTimeNs + " ns (" + bstTimeMs + " ms)");

        System.out.println("\nAVL consumes " + avlPercent + "% of SLA budget.");
        System.out.println("BST consumes " + bstPercent + "% of SLA budget.");
    }
}