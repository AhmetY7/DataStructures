/*
Applications of Queue

1-CPU scheduling, Disk Scheduling
2-When data is transferred asynchronously between two processes.The queue is used for synchronization. For example: IO Buffers, pipes, file IO, etc
3-Handling of interrupts in real-time systems.
4-Call Center phone systems use Queues to hold people calling them in order.
 */
public class SimpleQueue {
    int SIZE = 5;
    int items[] = new int[SIZE];
    int front, rear;

    SimpleQueue() {
        front = -1;
        rear = -1;
    }

    boolean isFull() {
        return front == 0 && rear == SIZE - 1;
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
            rear++;
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
            if (front >= rear) {
                front = -1;
                rear = -1;
            } else {
                front++;
            }
            System.out.println("Deleted -> " + element);
            return (element);
        }
    }

    void display() {
        int i;
        if (isEmpty()) {
            System.out.println("Empty Queue");
        } else {
            System.out.println("\nFront index-> " + front);
            System.out.println("Items -> ");
            for (i=front; i <=rear; i++)
                System.out.println(items[i] + " ");
            System.out.println("\nRear index-> " + rear);
        }
    }

    public static void main(String[] args) {
        SimpleQueue q = new SimpleQueue();

        q.deQueue();

        q.enQueue(1);
        q.enQueue(2);
        q.enQueue(3);
        q.enQueue(4);
        q.enQueue(5);

        q.enQueue(6);

        q.display();

        q.deQueue();

        q.display();
    }

}
