/*
An adjacency matrix is a way of representing a graph G = {V, E} as a matrix of booleans.

Cons of adjacency matrix
The VxV space requirement of the adjacency matrix makes it a memory hog. Graphs out in the wild usually don't
have too many connections and this is the major reason why adjacency lists are the better choice for most tasks.

While basic operations are easy, operations like inEdges and outEdges are expensive when using the adjacency
matrix representation.

Adjacency Matrix Applications
-Creating routing table in networks
-Navigation tasks
 */
public class AdjacencyMatrix {
    private boolean adjMatrix[][];
    private int numVertices;

    private AdjacencyMatrix(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new boolean[numVertices][numVertices];
    }

    public void addEdge(int i, int j) {
        adjMatrix[i][j] = true;
        adjMatrix[j][i] = true;
    }

    public void removeEdge(int i, int j) {
        adjMatrix[i][j] = false;
        adjMatrix[j][i] = false;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i=0; i<numVertices; i++) {
            s.append(i).append(": ");
            for (boolean j: adjMatrix[i]) {
                s.append(j ? 1 : 0).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        AdjacencyMatrix adj = new AdjacencyMatrix(4);

        adj.addEdge(0, 1);
        adj.addEdge(0, 2);
        adj.addEdge(1, 2);
        adj.addEdge(2, 0);
        adj.addEdge(2, 3);

        System.out.print(adj.toString());
    }
}
