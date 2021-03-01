/*
AVL tree is a self-balancing binary search tree in which each node maintains extra information called a balance
factor whose value is either -1, 0 or +1.
Balance Factor = (Height of Left Subtree - Height of Right Subtree) or (Height of Right Subtree - Height of Left Subtree)
 */

/*
Complexities of Different Operations on an AVL Tree
Insertion	Deletion	Search
O(log n)	O(log n)	O(log n)

AVL Tree Applications
-For indexing large records in databases
-For searching in large databases
 */
import javax.lang.model.type.MirroredTypeException;

class AVLNode{
    int item;
    int height;
    AVLNode left;
    AVLNode right;

    AVLNode(int d) {
        item = d;
        height = 1;
    }
}
public class AVLTree {

    AVLNode root;

    int height(AVLNode n) {
        if (n == null)
            return 0;
        return n.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    int getBalanceFactor(AVLNode n) {
        if (n == null)
            return 0;
        return height(n.left) - height(n.right);
    }

    AVLNode insertNode(AVLNode node, int item) {
        if (node == null)
            return (new AVLNode(item));
        if (item < node.item)
            node.left = insertNode(node.left, item);
        else if (item > node.item)
            node.right = insertNode(node.right, item);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (item < node.left.item){
                return rightRotate(node);
            } else if (item > node.left.item) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        if (balanceFactor < -1) {
            if (item > node.right.item) {
                return leftRotate(node);
            } else if (item < node.right.item) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    AVLNode nodeWithMimumValue(AVLNode node) {
        AVLNode current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    AVLNode deleteNode(AVLNode root, int item) {
        if (root == null)
            return root;
        if (item < root.item)
            root.left = deleteNode(root.left, item);
        else if (item > root.item)
            root.right = deleteNode(root.right, item);
        else {
            if ((root.left == null) || (root.right == null)) {
                AVLNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                AVLNode temp = nodeWithMimumValue(root.right);
                root.item = temp.item;
                root.right = deleteNode(root.right, temp.item);
            }
        }

        if (root == null) {
            return root;
        }

        root.height = max(height(root.left), height(root.right)) + 1;
        int balanceFactor = getBalanceFactor(root);
        if (balanceFactor > 1) {
            if (getBalanceFactor(root.left) >= 0) {
                return rightRotate(root);
            } else {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }

        if (balanceFactor < -1) {
            if (getBalanceFactor(root.right) <= 0 ) {
                return leftRotate(root);
            } else {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }
        return root;
    }

    void preOrder(AVLNode node) {
        if (node != null) {
            System.out.println(node.item + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private void printTree(AVLNode currentPtr, String indent, boolean last) {
        if (currentPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R------");
                indent += "     ";
            } else {
                System.out.print("L------");
                indent += "|    ";
            }
            System.out.println(currentPtr.item);
            printTree(currentPtr.left, indent, false);
            printTree(currentPtr.right, indent, true);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.root = tree.insertNode(tree.root, 33);
        tree.root = tree.insertNode(tree.root, 13);
        tree.root = tree.insertNode(tree.root, 53);
        tree.root = tree.insertNode(tree.root, 9);
        tree.root = tree.insertNode(tree.root, 21);
        tree.root = tree.insertNode(tree.root, 61);
        tree.root = tree.insertNode(tree.root, 8);
        tree.root = tree.insertNode(tree.root, 11);
        tree.printTree(tree.root, "", true);
        tree.root = tree.deleteNode(tree.root, 13);
        System.out.println("After deletion:");
        tree.printTree(tree.root, "",true);
    }
}
