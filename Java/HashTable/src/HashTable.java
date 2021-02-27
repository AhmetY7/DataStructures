import java.util.Hashtable;

public class HashTable {
    /*
    In a hash table, the keys are processed to produce a new index that maps to the required element.
    This process is called hashing.
     */
    /*
    Advantages of hash table over direct address table:

    The main issues with direct address table are the size of the array and the possibly large value of a key.
    The hash function reduces the range of index and thus the size of the array is also reduced.
    For example, If k = 9845648451321, then h(k) = 11 (by using some hash function). This helps in saving the memory
    wasted while providing the index of 9845648451321 to the array
     */

    /*
    Collision resolution by chaining
    In this technique, if a hash function produces the same index for multiple elements, these elements are stored
    in the same index by using a doubly linked list.

    If j is the slot for multiple elements, it contains a pointer to the head of the list of elements. If no element
    is present, j contains NIL.
     */

    /*
    Hash Table Applications
    Hash tables are implemented where

    -constant time lookup and insertion is required
    -cryptographic applications
    -indexing data is required
     */
    public static void main(String[] args) {
        Hashtable<Integer, Integer> ht = new Hashtable<>();

        ht.put(123, 432);
        ht.put(12, 2345);
        ht.put(15, 5643);
        ht.put(3, 321);

        ht.remove(12);

        System.out.println(ht);

    }
}
