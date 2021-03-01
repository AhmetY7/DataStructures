public class PerfectBinaryTree {

    static int depth(Node node) {
        int d = 0;
        while (node != null) {
            d++;
            node = node.left;
        }
        return d;
    }

    static boolean isPerfect(Node root, int d, int level) {
        if (root == null)
            return true;

        if (root.left == null && root.right == null) {
            return (d == level+1);
        }

        if (root.left == null || root.right == null)
            return false;

        return isPerfect(root.left, d, level+1) && isPerfect(root.right, d, level+1);
    }

    static boolean isPerfect(Node root) {
        int d = depth(root);
        return isPerfect(root, d, 0);
    }

    static Node newNode(int k) {
        Node node = new Node();
        node.item = k;
        node.right = null;
        node.left = null;
        return node;
    }

    public static void main(String[] args) {
        Node root = null;
        root = newNode(1);
        root.left = newNode(2);
        root.right = newNode(3);
        root.right.left = newNode(7);
        root.right.right = newNode(9);
        root.left.left = newNode(4);
        root.left.right = newNode(5);

        if (isPerfect(root))
            System.out.println("The tree is a perfect binary tree");
        else
            System.out.println("The tree is not perfect binary tree");
    }
}
