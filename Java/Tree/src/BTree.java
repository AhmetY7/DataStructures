/*
B-tree is a special type of self-balancing search tree in which each node can contain more than one key and can have
more than two children.

Why B-tree?
The need for B-tree arose with the rise in the need for lesser time in accessing the physical storage media like a
hard disk. The secondary storage devices are slower with a larger capacity. There was a need for such types of
data structures that minimize the disk accesses.

Other data structures such as a binary search tree, avl tree, red-black tree, etc can store only one key in one node.
If you have to store a large number of keys, then the height of such trees becomes very large and the access time increases.

However, B-tree can store many keys in a single node and can have multiple child nodes. This decreases the height
significantly allowing faster disk accesses.

B Tree Applications
-databases and file systems
-to store blocks of data (secondary storage media)
-multilevel indexing
 */

import java.util.Stack;

/*
Searching Complexity on B Tree
Worst case Time complexity: Θ(log n)

Average case Time complexity: Θ(log n)

Best case Time complexity: Θ(log n)

Average case Space complexity: Θ(n)

Worst case Space complexity: Θ(n)

Deletion Complexity
Best case Time complexity: Θ(log n)

Average case Space complexity: Θ(n)

Worst case Space complexity: Θ(n)
 */
public class BTree {
    private int T;

    public class Node{
        int n;
        int key[] = new int[2 * T - 1];
        Node child[] = new Node[2 *  T];
        boolean leaf = true;

        public int find(int k) {
            for (int i=0; i<this.n; i++) {
                if (this.key[i] == k) {
                    return i;
                }
            }
            return -1;
        }
    }

    private Node root;

    public BTree(int t) {
        T = t;
        root = new Node();
        root.n = 0;
        root.leaf = true;
    }

    private Node search(Node x, int key) {
        int i=0;
        if (x == null)
            return x;
        for (i=0; i<x.n; i++) {
            if (key < x.key[i]) {
                break;
            }
            if (key == x.key[i]) {
                return x;
            }
        }
        if (x.leaf) {
            return null;
        } else {
            return search(x.child[i], key);
        }
    }

    private void split(Node x, int pos, Node y) {
        Node z = new Node();
        z.leaf = y.leaf;
        z.n = T - 1;
        for (int j=0; j<T-1; j++) {
            z.key[j] = y.key[j + T];
        }
        if (!y.leaf) {
            for (int j=0; j<T; j++) {
                z.child[j] = y.child[j + T];
            }
        }

        y.n = T -1;
        for (int j=x.n; j>=pos+1; j--) {
            x.child[j+1] = x.child[j];
        }
        x.child[pos+1] = z;

        for (int j=x.n-1; j>pos; j--) {
            x.key[j+1] = x.key[j];
        }
        x.key[pos] = y.key[T-1];
        x.n = x.n+1;
    }

    public void insert(final int key) {
        Node r = root;
        if (r.n == 2 * T - 1) {
            Node s = new Node();
            root = s;
            s.leaf = false;
            s.n = 0;
            s.child[0] = r;
            split(s, 0, r);
            _insert(s, key);
        } else {
            _insert(r, key);
        }
    }

    final private void _insert(Node x, int k) {
        if (x.leaf) {
            int i=0;
            for (i=x.n-1; i>=0 && k<x.key[i]; i--) {
                x.key[i+1] = x.key[i];
            }
            x.key[i+1] = k;
            x.n = x.n+1;
        } else {
            int i=0;
            for (i=x.n-1; i>=0 && k<x.key[i]; i--) {

            }
            i++;
            Node temp = x.child[i];
            if (temp.n == 2*T-1) {
                split(x, i, temp);
                if (k > x.key[i]) {
                    i++;
                }
            }
            _insert(x.child[i], k);
        }
    }

    public void show() {
        show(root);
    }

    private void remove(Node x, int key) {
        int pos = x.find(key);
        if (pos != -1) {
            if (x.leaf) {
                int i=0;
                for (i=0; i<x.n && x.key[i] != key; i++) {
                }
                for (; i<x.n; i++) {
                    if (i != 2*T-2) {
                        x.key[i] = x.key[i+1];
                    }
                }
                x.n--;
                return;
            }
            if(!x.leaf) {
                Node pred = x.child[pos];
                int predKey = 0;
                if (pred.n >= T) {
                    while (true) {
                        if (pred.leaf) {
                            System.out.println(pred.n);
                            predKey = pred.key[pred.n - 1];
                            break;
                        } else {
                            pred = pred.child[pred.n];
                        }
                    }
                    remove(pred, predKey);
                    x.key[pos] = predKey;
                    return;
                }

                Node nextNode = x.child[pos+1];
                if (nextNode.n >= T) {
                    int nextKey = nextNode.key[0];
                    if (!nextNode.leaf) {
                        nextNode = nextNode.child[0];
                        while (true) {
                            if (nextNode.leaf) {
                                nextKey = nextNode.key[nextNode.n-1];
                                break;
                            } else {
                                nextNode = nextNode.child[nextNode.n];
                            }
                        }
                    }
                    remove(nextNode, nextKey);
                    x.key[pos] = nextKey;
                    return;
                }

                int temp = pred.n+1;
                pred.key[pred.n++] = x.key[pos];
                for (int i=0, j=pred.n; i<nextNode.n; i++) {
                    pred.key[j++] = nextNode.key[i];
                    pred.n++;
                }
                for (int i=0, j=pred.n; i<nextNode.n; i++) {
                    pred.key[j++] = nextNode.key[i];
                    pred.n++;
                }
                for (int i=0; i<nextNode.n+1; i++) {
                    pred.child[temp++] = nextNode.child[i];
                }

                x.child[pos] = pred;
                for (int i=pos; i<x.n; i++) {
                    if (i != 2*T-2) {
                        x.key[i] = x.key[i+1];
                    }
                }

                for (int i=pos+1; i<x.n+1; i++) {
                    if (i != 2*T-1) {
                        x.child[i] = x.child[i+1];
                    }
                }
                x.n--;
                if (x.n == 0) {
                    if (x == root) {
                        root = x.child[0];
                    }
                    x = x.child[0];
                }
                remove(pred, key);
                return;
            }
        } else {
            for (pos=0; pos<x.n; pos++) {
                if (x.key[pos] > key) {
                    break;
                }
            }

            Node temp = x.child[pos];
            if (temp.n >= T) {
                remove(temp, key);
                return;
            }

            if (true) {
                Node nb = null;
                int devider = -1;

                if (pos != x.n && x.child[pos+1].n >= T) {
                    devider = x.key[pos];
                    nb = x.child[pos+1];
                    x.key[pos] = nb.key[0];
                    temp.key[temp.n++] = devider;
                    temp.child[temp.n] = nb.child[0];
                    for (int i=1; i< nb.n; i++) {
                        nb.key[i-1] = nb.key[i];
                    }
                    for (int i=1; i<= nb.n; i++) {
                        nb.child[i-1] = nb.child[i];
                    }
                    nb.n--;
                    remove(temp, key);
                    return;
                } else if (pos != 0 && x.child[pos -1].n >= T) {
                    devider = x.key[pos-1];
                    nb = x.child[pos-1];
                    x.key[pos-1] = nb.key[nb.n -1];
                    Node child = nb.child[nb.n];
                    nb.n--;
                    for (int i=temp.n; i>0; i--) {
                        temp.key[i] = temp.key[i-1];
                    }
                    temp.key[0] = devider;
                    for (int i=temp.n+1; i>0; i--) {
                        temp.child[i] = temp.child[i-1];
                    }
                    temp.child[0] = child;
                    temp.n++;
                    remove(temp,key);
                    return;
                } else {
                    Node lt = null;
                    Node rt = null;
                    boolean last = false;
                    if (pos != x.n) {
                        devider = x.key[pos];
                        lt = x.child[pos];
                        rt = x.child[pos +1];
                    } else {
                        devider = x.key[pos-1];
                        rt = x.child[pos];
                        lt = x.child[pos-1];
                        last = true;
                        pos--;
                    }
                    for (int i=pos; i<x.n-1; i++) {
                        x.key[i] = x.key[i+1];
                    }
                    for (int i=pos+1; i<x.n; i++) {
                        x.child[i] = x.child[i+1];
                    }
                    x.n--;
                    lt.key[lt.n++] = devider;

                    for (int i=0, j=lt.n; i<rt.n+1; i++, j++) {
                        if (i<rt.n) {
                            lt.key[j] = rt.key[i];
                        }
                        lt.child[j] = rt.child[i];
                    }
                    lt.n += rt.n;
                    if (x.n == 0) {
                        if (x == root) {
                            root = x.child[0];
                        }
                        x = x.child[0];
                    }
                    remove(lt, key);
                    return;
                }
            }
        }
    }

    public void remove(int key) {
        Node x = search(root, key);
        if (x == null) {
            return;
        }
        remove(root, key);
    }

    public void task(int a, int b) {
        Stack<Integer> st = new Stack<>();
        findKeys(a, b, root, st);
        while (st.isEmpty() == false) {
            this.remove(root, st.pop());
        }
    }

    private void findKeys(int a, int b, Node x, Stack<Integer> st) {
        int i=0;
        for (i=0; i<x.n && x.key[i] < b; i++) {
            if (x.key[i] > a) {
                st.push(x.key[i]);
            }
        }
        if (!x.leaf) {
            for (int j=0; j<i+1; j++) {
                findKeys(a, b, x.child[j], st);
            }
        }
    }

    public boolean contain(int k) {
        return this.search(root, k) != null;
    }

    private void show(Node x) {
        assert (x == null);
        for (int i=0; i<x.n; i++) {
            System.out.println(x.key[i] + " ");
        }
        if (!x.leaf) {
            for (int i=0; i<x.n+1; i++) {
                show(x.child[i]);
            }
        }
    }

    public static void main(String[] args) {
        BTree bTree = new BTree(3);
        bTree.insert(8);
        bTree.insert(9);
        bTree.insert(10);
        bTree.insert(11);
        bTree.insert(15);
        bTree.insert(20);
        bTree.insert(7);

        bTree.show();;

        bTree.remove(10);
        System.out.println();
        bTree.show();
    }
}
