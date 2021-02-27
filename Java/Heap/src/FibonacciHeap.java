/*
Properties of a Fibonacci Heap
Important properties of a Fibonacci heap are:

It is a set of min heap-ordered trees. (i.e. The parent is always smaller than the children.)
A pointer is maintained at the minimum element node.
It consists of a set of marked nodes. (Decrease key operation)
The trees within a Fibonacci heap are unordered but rooted.
 */

/*
Fibonacci Heap Applications
To improve the asymptotic running time of Dijkstra's algorithm.
 */

class Node {
    Node parent;
    Node left;
    Node right;
    Node child;
    int degree;
    boolean mark;
    int key;

    public Node() {
        this.degree = 0;
        this.mark = false;
        this.parent = null;
        this.left = this;
        this.right = this;
        this.child = null;
        this.key = Integer.MAX_VALUE;
    }

    Node(int x) {
        this();
        this.key = x;
    }

    void setParent(Node x) {
        this.parent = x;
    }

    Node getParent() {
        return this.parent;
    }

    void setLeft(Node x) {
        this.left = x;
    }

    Node getLeft() {
        return this.left;
    }

    void setRight(Node x) {
        this.right = x;
    }

    Node getRight() {
        return this.right;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public boolean getMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
public class FibonacciHeap {
    Node min;
    int n;
    boolean trace;
    Node found;

    public boolean getTrace() {
        return trace;
    }

    public void setTrace(boolean trace) {
        this.trace = trace;
    }

    public static FibonacciHeap createHeap() {
        return new FibonacciHeap();
    }

    FibonacciHeap() {
        min = null;
        n = 0;
        trace = false;
    }

    private void insert(Node x) {
        if (min == null) {
            min = x;
            x.setLeft(min);
            x.setRight(min);
        } else {
            x.setRight(min);
            x.setLeft(min.getLeft());
            min.getLeft().setRight(x);
            min.setLeft(x);
            if (x.getKey() < min.getKey())
                min = x;
        }
        n += 1;
    }

    public void insert(int key) {
        insert(new Node(key));
    }

    public void display() {
        display(min);
        System.out.println();
    }

    public void display(Node c) {
        System.out.print("(");
        if (c == null) {
            System.out.print(")");
            return;
        } else {
            Node temp = c;
            do {
                System.out.println(temp.getKey());
                Node k = temp.getChild();
                display(k);
                System.out.print("->");
                temp = temp.getRight();
            } while (temp != c);
            System.out.print(")");
        }
    }

    public static void mergeHeap(FibonacciHeap h1, FibonacciHeap h2, FibonacciHeap h3) {
        h3.min = h1.min;

        if (h1.min != null && h2.min != null) {
            Node t1 = h1.min.getLeft();
            Node t2 = h2.min.getLeft();
            h1.min.setLeft(t2);
            t1.setRight(h2.min);
            h2.min.setLeft(t1);
            t2.setRight(h1.min);
        }
        if (h1.min == null || (h2.min != null && h2.min.getKey() < h1.min.getKey()))
            h3.min = h2.min;
        h3.n = h1.n + h2.n;
    }

    public int findMin() {
        return this.min.getKey();
    }

    private void displayNode(Node z) {
        System.out.println("right: " + ((z.getRight() == null) ? "-1" : z.getRight().getKey()));
        System.out.println("left: " + ((z.getLeft() == null) ? "-1" : z.getLeft().getKey()));
        System.out.println("child: " + ((z.getChild() == null) ? "-1" : z.getChild().getKey()));
        System.out.println("degree " + z.getDegree());
    }

    public int extractMin() {
        Node z = this.min;
        if (z != null) {
            Node c = z.getChild();
            Node k = c, p;
            if (c != null) {
                do {
                    p = c.getRight();
                    insert(c);
                    c.setParent(null);
                    c = p;
                } while (c != null && c != k);
            }
            z.getLeft().setRight(z.getRight());
            z.getRight().setLeft(z.getLeft());
            z.setChild(null);
            if (z == z.getRight())
                this.min = null;
            else {
                this.min = z.getRight();
                this.consolidate();
            }
            this.n -= 1;
            return z.getKey();
        }
        return Integer.MAX_VALUE;
    }

    public void consolidate() {
        double phi = (1 + Math.sqrt(5)) / 2;
        int Dofn = (int) (Math.log(this.n) / Math.log(phi));
        Node[] A = new Node[Dofn + 1];
        for (int i=0; i <= Dofn; ++i)
            A[i] = null;
        Node w = min;
        if (w != null) {
            Node check = min;
            do {
                Node x = w;
                int d = x.getDegree();
                while (A[d] != null) {
                    Node y = A[d];
                    if (x.getKey() > y.getKey()) {
                        Node temp = x;
                        x = y;
                        y = temp;
                        w = x;
                    }
                    fibHeapLink(y,x);
                    check = x;
                    A[d] = null;
                    d += 1;
                }
                A[d] = x;
                w = w.getRight();
            } while (w != null && w != check);
            this.min = null;
            for (int i=0; i<=Dofn; ++i) {
                if (A[i] != null) {
                    insert(A[i]);
                }
            }
        }
    }

    private void fibHeapLink(Node y, Node x) {
        y.getLeft().setRight(y.getRight());
        y.getRight().setLeft(y.getLeft());

        Node p = x.getChild();
        if (p == null) {
            y.setRight(y);
            y.setLeft(y);
        } else {
            y.setRight(p);
            y.setLeft(p.getLeft());
            p.getLeft().setRight(y);
            p.setLeft(y);
        }
        y.setParent(x);
        x.setChild(y);
        x.setDegree(x.getDegree() + 1);
        y.setMark(false);
    }

    private void find(int key, Node c) {
        if (found != null || c == null) {
            return;
        } else {
            Node temp = c;
            do {
                if (key == temp.getKey())
                    found = temp;
                else {
                    Node k = temp.getChild();
                    find(key, k);
                    temp = temp.getRight();
                }
            } while (temp != c && found == null);
        }
    }

    public Node find(int k) {
        found = null;
        find(k, this.min);
        return found;
    }

    public void decreaseKey(int key, int nval) {
        Node x = find(key);
        decreaseKey(x, nval);
    }

    private void decreaseKey(Node x, int k) {
        if (k > x.getKey()) {
            return;
        }
        x.setKey(k);
        Node y = x.getParent();
        if (y != null && x.getKey() < y.getKey()) {
            cut(x, y);
            cascadingCut(y);
        }
        if (x.getKey() < min.getKey())
            min = x;
    }

    private void cut(Node x, Node y) {
        x.getRight().setLeft(x.getLeft());
        x.getLeft().setRight(x.getRight());

        y.setDegree(y.getDegree() - 1);

        x.setRight(null);
        x.setLeft(null);
        insert(x);
        x.setParent(null);
        x.setMark(false);
    }

    private void cascadingCut(Node y) {
        Node z = y.getParent();
        if (z != null) {
            if (y.getMark() == false)
                y.setMark(true);
            else {
                cut(y, z);
                cascadingCut(z);
            }
        }
    }

    public void delete(Node x) {
        decreaseKey(x, Integer.MIN_VALUE);
        int p = extractMin();
    }

    public static void main(String[] args) {
        FibonacciHeap obj = createHeap();
        obj.insert(7);
        obj.insert(26);
        obj.insert(30);
        obj.insert(39);
        obj.insert(10);
        obj.display();

        System.out.println(obj.extractMin());
        obj.display();
        System.out.println(obj.extractMin());
        obj.display();
        System.out.println(obj.extractMin());
        obj.display();
        System.out.println(obj.extractMin());
        obj.display();
        System.out.println(obj.extractMin());
        obj.display();
    }
}
