import java.util.ArrayList;

/*
An adjacency list is efficient in terms of storage because we only need to store the values for the edges. For a sparse graph with millions of vertices and edges, this can mean a lot of saved space.
 */
public class AdjacencyList {

    static void addEdge(ArrayList<ArrayList<Integer>> am, int s, int d) {
        am.get(s).add(d);
        am.get(d).add(s);
    }

    static void printGraph(ArrayList<ArrayList<Integer>> am) {
        for (int i=0; i<am.size(); i++) {
            System.out.println("\nVertex " + i + ":");
            for (int j=0; j<am.get(i).size(); j++) {
                System.out.print(" -> " + am.get(i).get(j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Integer>> am = new ArrayList<>(V);
        for (int i=0; i<V; i++)
            am.add(new ArrayList<>());

        addEdge(am, 0, 1);
        addEdge(am, 0, 2);
        addEdge(am, 0, 3);
        addEdge(am, 1, 2);

        printGraph(am);
    }
}
