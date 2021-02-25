import java.util.ArrayList;

/*
Priority Queue Applications

1-Dijkstra's algorithm
2-for implementing stack
3-for load balancing and interrupt handling in an operating system
4-for data compression in Huffman code
 */
public class PriorityQueue {

    void heapify(ArrayList<Integer> hT, int i) {
        int size = hT.size();

        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < size && hT.get(l) > hT.get(largest))
            largest = l;
        if (r < size && hT.get(r) > hT.get(largest))
            largest = r;

        if (largest != i) {
            int temp = hT.get(largest);
            hT.set(largest, hT.get(i));
            hT.set(i, temp);

            heapify(hT, largest);
        }
    }

    void insert(ArrayList<Integer> hT, int newNum) {
        int size = hT.size();
        if (size == 0) {
            hT.add(newNum);
        } else {
            hT.add(newNum);
            for (int i=size/2-1; i>=0; i-- ) {
                heapify(hT, i);
            }
        }
    }

    void deleteNode(ArrayList<Integer> hT, int num) {
        int size = hT.size();
        int i;
        for (i=0; i < size; i++) {
            if (num == hT.get(i))
                break;
        }

        int temp = hT.get(i);
        hT.set(i, hT.get(size - 1));
        hT.set(size - 1, temp);

        hT.remove(size - 1);
        for (int j = size/2-1; j>=0; j--) {
            heapify(hT,j);
        }
    }

    void printArray(ArrayList<Integer> array, int size) {
        for (Integer i : array) {
            System.out.println(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        ArrayList<Integer> array = new ArrayList<>();
        int size = array.size();

        PriorityQueue p = new PriorityQueue();
        p.insert(array, 3);
        p.insert(array, 4);
        p.insert(array, 9);
        p.insert(array, 5);
        p.insert(array, 2);

        System.out.println("Max-Heap array: ");
        p.printArray(array, size);

        p.deleteNode(array, 4);
        System.out.println("After deleting an element: ");
        p.printArray(array, size);
    }
}
