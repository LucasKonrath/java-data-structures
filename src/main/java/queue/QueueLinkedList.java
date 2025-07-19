package queue;

import linkedlist.simplylinked.LinkedListNode;

public class QueueLinkedList {
    private int length;
    private LinkedListNode front, rear;

    public QueueLinkedList() {
        this.front = null;
        this.rear = null;
        this.length = 0;
    }

    public void enqueue(int value){
        LinkedListNode newNode = new LinkedListNode(value);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        length++;
    }

    public int dequeue(){
        if (front == null) {
            throw new IllegalStateException("Queue is empty");
        }
        int value = front.getData();
        front = front.getNext();
        if (front == null) {
            rear = null; // Queue is now empty
        }
        length--;
        return value;
    }

    public int first() {
        if (front == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.getData();
    }

    public int peek() {
        return first();
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }
}
