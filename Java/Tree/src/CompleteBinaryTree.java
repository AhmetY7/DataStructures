/*
A complete binary tree is a binary tree in which all the levels are completely filled
except possibly the lowest one, which is filled from the left.
 */
public class CompleteBinaryTree {
    Node root;

    int countNumNodes(Node root) {
        if (root == null)
            return (0);
        return (1 + countNumNodes(root.left) + countNumNodes(root.right));
    }

    boolean checkComplete(Node root, int index, int numberNodes) {
        if (root == null)
            return true;

        if (index >= numberNodes)
            return false;

        return (checkComplete(root.left, 2*index+1, numberNodes)
        && checkComplete(root.right, 2*index+2, numberNodes));
    }

    public static void main(String[] args) {
        CompleteBinaryTree tree = new CompleteBinaryTree();

        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.right = new Node(2);
        tree.root.left.left = new Node(2);
        tree.root.right.left = new Node(3);

        int nodeCount = tree.countNumNodes(tree.root);
        int index = 0;
        if (tree.checkComplete(tree.root, index, nodeCount))
            System.out.println("The tree is a complete binary tree");
        else
            System.out.println("The tree is not a complete binary tree");

    }
}
