public class LinkedList {
    Node head;

    class Node {
        int item;
        Node next;

        Node(int data) {
            item = data;
            next = null;
        }
    }

    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void insertAfter(Node prevNode, int data) {
        if (prevNode == null) {
            System.out.println("The given previous node cannot be null");
            return;
        }
        Node newNode = new Node(data);
        newNode.next = prevNode.next;
        prevNode.next = newNode;
    }

    public void insertAtEnd(int data) {
        Node newNode = new Node(data);

        if (head == null){
            head = new Node(data);
            return;
        }

        newNode.next = null;

        Node last = head;
        while (last.next != null) {
            last = last.next;
        }

        last.next = newNode;
    }

    void deleteNode(int position) {
        if (head == null)
            return;

        Node node = head;

        if (position == 0) {
            head = node.next;
            return;
        }

        for (int i=0; node != null && i<position-i; i++) {
            node = node.next;
        }

        if (node == null || node.next == null) {
            return;
        }

        Node next = node.next.next;

        node.next = next;
    }

    public void printList() {
        Node node = head;
        while (node != null) {
            System.out.println(node.item + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        LinkedList llist = new LinkedList();

        llist.insertAtEnd(1);
        llist.insertAtBeginning(2);
        llist.insertAtBeginning(3);
        llist.insertAtEnd(4);
        llist.insertAfter(llist.head.next, 5);

        System.out.println("Linked list: ");
        llist.printList();

        System.out.println("\nAfter deleting an element: ");
        llist.deleteNode(3);
        llist.printList();
    }
}
