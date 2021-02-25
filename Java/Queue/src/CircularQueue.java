/*
Applications of Circular Queue

1-CPU scheduling
2-Memory management
3-Traffic Management

 */
public class CircularQueue {
    int SIZE = 5;
    int items[] = new int[SIZE];
    int front, rear;

    CircularQueue() {
        front = -1;
        rear = -1;
    }

    boolean isFull() {
        if (front == 0 && rear == SIZE - 1) {
            return true;
        }
        if (front == rear + 1) {
            return true;
        }
        return false;
    }

    boolean isEmpty() {
        return front == -1;
    }

    void enQueue(int element) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            if (front == -1)
                front = 0;
            rear = (rear + 1) % SIZE;
            items[rear] = element;
            System.out.println("Inserted " + element);
        }
    }

    int deQueue() {
        int element;
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return (-1);
        } else {
            element = items[front];
            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % SIZE;
            }
            return (element);
        }
    }

    void display() {
        int i;
        if (isEmpty()) {
            System.out.println("Empty Queue");
        } else {
            System.out.println("Front index-> " + front);
            System.out.println("Items -> ");
            for (i=front; i !=rear; i = (i+1) % SIZE)
                System.out.println(items[i] + " ");
            System.out.println(items[i]);
            System.out.println("Rear -> " + rear);
        }
    }

    public static void main(String[] args) {
        CircularQueue q = new CircularQueue();

        q.deQueue();

        q.enQueue(1);
        q.enQueue(2);
        q.enQueue(3);
        q.enQueue(4);
        q.enQueue(5);

        q.enQueue(6);

        q.display();

        int elem = q.deQueue();
        if (elem != -1) {
            System.out.println("Deleted Element is " + elem);
        }
        q.display();
        q.enQueue(7);
        q.display();
        q.enQueue(8);
    }
}
