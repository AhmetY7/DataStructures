class Node {
    int item;
    Node left, right;

    public Node() {}

    public Node(int key) {
        item = key;
        left = right = null;
    }
}

/*
A binary tree is a tree data structure in which each parent node can have at most two children.
 */
public class BinaryTree {
    Node root;

    BinaryTree() {
        root = null;
    }

    BinaryTree(int key) {
        root = new Node(key);
    }

    void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.item + "->");
    }

    void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.print(node.item + "->");
        inOrder(node.right);
    }

    void preOrder(Node node) {
        if (node == null)
            return;

        System.out.print(node.item + "->");
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(12);
        tree.root.right = new Node(9);
        tree.root.left.left = new Node(5);
        tree.root.left.right = new Node(6);

        System.out.println("Inorder traversal");
        tree.inOrder(tree.root);

        System.out.println("\nPreorder traversal");
        tree.preOrder(tree.root);

        System.out.println("\nPostorder traversal");
        tree.postOrder(tree.root);
    }
}
