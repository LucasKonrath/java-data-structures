package queue;

public class Queue {
    private int[] array;
    private int size, front, rear;

    private static final int DEFAULT_CAPACITY = 16;

    public Queue() {
        this(DEFAULT_CAPACITY);
    }

    public Queue(int capacity) {
        array = new int[capacity];
        size = 0;
        front = 0;
        rear = -1;
    }

    public void enqueue(int value){
        if (size == array.length) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % array.length;
        array[rear] = value;
        size++;
    }

    public int dequeue() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        int value = array[front];
        array[front] = Integer.MIN_VALUE; // Optional: clear the value
        front = (front + 1) % array.length;
        size--;
        return value;
    }

    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        return array[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public int size() {
        return size;
    }


}
