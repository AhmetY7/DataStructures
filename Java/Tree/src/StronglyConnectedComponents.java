// Kosaraju's algorithm to find strongly connected components

/*
Kosaraju's Algorithm Complexity
Kosaraju's algorithm runs in linear time i.e. O(V+E).

Strongly Connected Components Applications
-Vehicle routing applications
-Maps
-Model-checking in formal verification
 */
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

class StronglyConnectedComponents {
    private int V;
    private LinkedList[] adj;

    StronglyConnectedComponents(int s) {
        V = s;
        adj = new LinkedList[s];
        for (int i=0; i<s; ++i)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int s, int d) {
        adj[s].add(d);
    }

    void DFSUtil(int s, boolean[] visitedVertices) {
        visitedVertices[s] = true;
        System.out.print(s + " ");
        int n;

        Iterator<Integer> i = adj[s].iterator();
        while (i.hasNext()) {
            n = i.next();
            if (!visitedVertices[n]) {
                DFSUtil(n, visitedVertices);
            }
        }
    }

    StronglyConnectedComponents transpose() {
        StronglyConnectedComponents g = new StronglyConnectedComponents(V);
        for (int s=0; s<V; s++) {
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
                g.adj[i.next()].add(s);
        }
        return g;
    }

    void fillOrder(int s, boolean visitedVertices[], Stack<Integer> stack) {
        visitedVertices[s] = true;

        Iterator<Integer> i = adj[s].iterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visitedVertices[n])
                fillOrder(n, visitedVertices, stack);
        }
        stack.push(s);
    }

    void printSCC()  {
        Stack stack = new Stack();

        boolean visitedVertices[] = new boolean[V];
        for (int i=0; i<V; i++) {
            visitedVertices[i] = false;
        }

        for (int i=0; i<V; i++) {
            if (visitedVertices[i] == false)
                fillOrder(i, visitedVertices, stack);
        }

        StronglyConnectedComponents gr = transpose();

        for (int i=0; i<V; i++)
            visitedVertices[i] = false;

        while (!stack.empty()) {
            int s = (int) stack.pop();

            if (visitedVertices[s] == false) {
                gr.DFSUtil(s, visitedVertices);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        StronglyConnectedComponents scc = new StronglyConnectedComponents(8);
        scc.addEdge(0,1);
        scc.addEdge(1,2);
        scc.addEdge(2,3);
        scc.addEdge(2,4);
        scc.addEdge(3,0);
        scc.addEdge(4,5);
        scc.addEdge(5,6);
        scc.addEdge(6,4);
        scc.addEdge(6,7);

        System.out.println("Strongly Connected Components:");
        scc.printSCC();
    }

}
