/*
Binary search tree is a data structure that quickly allows us to maintain a sorted list of numbers.

-It is called a binary tree because each tree node has a maximum of two children.

-It is called a search tree because it can be used to search for the presence of a number in O(log(n)) time.
The properties that separate a binary search tree from a regular binary tree is

1.All nodes of left subtree are less than the root node
2.All nodes of right subtree are more than the root node
3.Both subtrees of each node are also BSTs i.e. they have the above two properties

Binary Search Tree Applications
-In multilevel indexing in the database
-For dynamic sorting
-For managing virtual memory areas in Unix kernel

Binary Search Tree Complexities
Time Complexity
Operation	Best Case Complexity	Average Case Complexity	Worst Case Complexity
Search	    O(log n)	            O(log n)	            O(n)
Insertion	O(log n)	            O(log n)	            O(n)
Deletion	O(log n)	            O(log n)	            O(n)

Here, n is the number of nodes in the tree.

Space Complexity
The space complexity for all the operations is O(n).

 */
public class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int key) {
        root = insertKey(root, key);
    }

    Node insertKey(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.item)
            root.left = insertKey(root.left, key);
        else if (key > root.item)
            root.right = insertKey(root.right, key);

        return root;
    }

    void inOrder() {
        inOrderRec(root);
    }

    void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.item + " - > ");
            inOrderRec(root.right);
        }
    }

    void deleteKey(int key) {
        root = deleteRec(root, key);
    }

    Node deleteRec(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.item)
            root.left = deleteRec(root.left, key);
        else if (key > root.item)
            root.right = deleteRec(root.right, key);
        else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.item = minValue(root.right);

            root.right = deleteRec(root.right, root.item);
        }
        return root;
    }

    int minValue(Node root) {
        int minValue = root.item;
        while (root.left != null) {
            minValue = root.left.item;
            root = root.left;
        }
        return minValue;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(8);
        tree.insert(3);
        tree.insert(1);
        tree.insert(6);
        tree.insert(7);
        tree.insert(10);
        tree.insert(14);
        tree.insert(4);

        System.out.println("Inorder treversal: ");
        tree.inOrder();

        System.out.println("\n\nAfter deleting 10");
        tree.deleteKey(10);
        System.out.println("Inorder traversal: ");
        tree.inOrder();
    }
}
