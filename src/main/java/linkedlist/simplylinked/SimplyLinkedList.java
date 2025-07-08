package linkedlist.simplylinked;

public class SimplyLinkedList {
    private LinkedListNode head;
    private int length;

    public SimplyLinkedList(){
        this.head = null;
        this.length = 0;
    }

    public LinkedListNode getHead() {
        return head;
    }

    public void insertAtBeginning(int data) {
        LinkedListNode newNode = new LinkedListNode(data);
        if (head == null) {
            head = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        length++;
    }

    public void insertData(int data, int position){
        if (position < 0 || position > length) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        LinkedListNode newNode = new LinkedListNode(data);
        if (position == 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            LinkedListNode current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        length++;
    }

    public void insertAtEnd(int data) {
        LinkedListNode newNode = new LinkedListNode(data);
        if (head == null) {
            head = newNode;
        } else {
            LinkedListNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        length++;
    }

    public LinkedListNode removeFromBeginning() {
        if (head == null) {
            return null; // List is empty
        }
        LinkedListNode removedNode = head;
        head = head.getNext();
        length--;
        return removedNode;
    }

    public LinkedListNode removeFromEnd() {
        if (head == null) {
            return null; // List is empty
        }
        if (head.getNext() == null) {
            LinkedListNode removedNode = head;
            head = null;
            length--;
            return removedNode;
        }
        LinkedListNode current = head;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }
        LinkedListNode removedNode = current.getNext();
        current.setNext(null);
        length--;
        return removedNode;
    }

    public void remove(int position){
        if (position < 0 || position >= length) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        if (position == 0) {
            removeFromBeginning();
            return;
        }
        LinkedListNode current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.getNext();
        }
        LinkedListNode removedNode = current.getNext();
        current.setNext(removedNode.getNext());
        length--;
    }
}
