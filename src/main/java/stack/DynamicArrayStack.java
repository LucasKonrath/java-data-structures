package stack;

public class DynamicArrayStack {
    private int capacity;
    private static final int DEFAULT_CAPACITY = 16;
    private static final int MIN_CAPACITY = 1;

    protected int[] stack;

    protected int topIndex = -1;

    public DynamicArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArrayStack(int capacity) {
        if (capacity < MIN_CAPACITY) {
            throw new IllegalArgumentException("Capacity must be at least " + MIN_CAPACITY);
        }
        this.capacity = capacity;
        this.stack = new int[capacity];
    }

    public int size() {
        return topIndex + 1;
    }

    public boolean isEmpty() {
        return topIndex < 0;
    }

    public void push(int value) {
        if (size() == capacity) {
            resize();
        }
        stack[++topIndex] = value;
    }

    public void resize(){
        int newCapacity = capacity << 1; // Double the capacity
        int[] newStack = new int[newCapacity];
        System.arraycopy(stack, 0, newStack, 0, size());
        stack = newStack;
        capacity = newCapacity;
    }

    private void shrink(){
        int newCapacity = capacity >> 1; // Halve the capacity
        if (newCapacity < MIN_CAPACITY) {
            newCapacity = MIN_CAPACITY;
        }
        int[] newStack = new int[newCapacity];
        System.arraycopy(stack, 0, newStack, 0, size());
        stack = newStack;
        capacity = newCapacity;
    }


    public int peek(){
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[topIndex];
    }

    public int pop(){
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int value = stack[topIndex];
        stack[topIndex--] = 0; // Clear the popped value
        if (size() < capacity / 4) {
            shrink();
        }
        return value;
    }

}
