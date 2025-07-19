package stack;

import linkedlist.simplylinked.LinkedListNode;

public class LinkedListStack {
    private int length;
    private LinkedListNode top;

    public LinkedListStack() {
        this.length = 0;
        this.top = null;
    }

    public void push(int value) {
        LinkedListNode newNode = new LinkedListNode(value);
        newNode.setNext(top);
        top = newNode;
        length++;
    }

    public int pop(){
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int value = top.getData();
        top = top.getNext();
        length--;
        return value;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.getData();
    }
}
