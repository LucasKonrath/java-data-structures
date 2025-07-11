package heap;

public class Heap {
    public int[] array;
    public int count;
    public int capacity;
    public boolean isMinHeap;
    public boolean isMaxHeap;

    public Heap(int capacity, boolean isMinHeap) {
        this.isMinHeap = isMinHeap;
        this.isMaxHeap = !isMinHeap;
        this.capacity = capacity;
        this.array = new int[capacity];
    }

    public int parent(int index) {
        if (index <= 0 || index >= count) {
            return -1; // Invalid index
        }
        return array[(index - 1) / 2];
    }

    public int LeftChild(int index) {
        int leftIndex = 2 * index + 1;
        if (leftIndex >= count) {
            return -1; // No left child
        }
        return array[leftIndex];
    }

    public int RightChild(int index) {
        int rightIndex = 2 * index + 2;
        if (rightIndex >= count) {
            return -1; // No right child
        }
        return array[rightIndex];
    }

    public int getMax() {
        if( isMinHeap) {
            return -1; // This is a min-heap, no max value
        }

        if (count == 0) {
            return -1; // Heap is empty
        }
        return array[0];
    }

    public int getMin() {
        if (isMaxHeap){
            return -1; // This is a max-heap, no min value
        }
        if (count == 0) {
            return -1; // Heap is empty
        }
        return array[0];
    }

    public void percolateDown(int i){
        int leftChildIndex = 2 * i + 1;
        int rightChildIndex = 2 * i + 2;
        int largestOrSmallest = i;

        if (isMaxHeap) {
            if (leftChildIndex < count && array[leftChildIndex] > array[largestOrSmallest]) {
                largestOrSmallest = leftChildIndex;
            }
            if (rightChildIndex < count && array[rightChildIndex] > array[largestOrSmallest]) {
                largestOrSmallest = rightChildIndex;
            }
        } else {
            if (leftChildIndex < count && array[leftChildIndex] < array[largestOrSmallest]) {
                largestOrSmallest = leftChildIndex;
            }
            if (rightChildIndex < count && array[rightChildIndex] < array[largestOrSmallest]) {
                largestOrSmallest = rightChildIndex;
            }
        }

        if (largestOrSmallest != i) {
            swap(i, largestOrSmallest);
            percolateDown(largestOrSmallest);
        }
    }

    public void swap(int i, int j){
        if (i < 0 || i >= count || j < 0 || j >= count) {
            throw new IndexOutOfBoundsException("Index out of bounds for swap operation.");
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void percolateUp(int i){
        if (i <= 0 || i >= count) {
            return; // Invalid index
        }

        int parentIndex = (i - 1) / 2;

        if (isMaxHeap) {
            if (array[i] > array[parentIndex]) {
                swap(i, parentIndex);
                percolateUp(parentIndex);
            }
        } else {
            if (array[i] < array[parentIndex]) {
                swap(i, parentIndex);
                percolateUp(parentIndex);
            }
        }
    }

    public int deleteMax(){
        if (isMinHeap) {
            return -1; // This is a min-heap, no max value to delete
        }
        if (count == 0) {
            return -1; // Heap is empty
        }

        int maxValue = array[0];
        array[0] = array[count - 1];
        count--;
        percolateDown(0);
        return maxValue;
    }

    public int deleteMin() {
        if (isMaxHeap) {
            return -1; // This is a max-heap, no min value to delete
        }
        if (count == 0) {
            return -1; // Heap is empty
        }

        int minValue = array[0];
        array[0] = array[count - 1];
        count--;
        percolateDown(0);
        return minValue;
    }

    public int insert(int value) {
        if (count == capacity) {
            resizeHeap();
        }
        array[count] = value;
        count++;
        percolateUp(count - 1);
        return value;
    }

    public void resizeHeap() {
        int newCapacity = capacity * 2;
        int[] newArray = new int[newCapacity];
        for (int i = 0; i < count; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        capacity = newCapacity;
    }

    public void destroyHeap() {
        array = null;
        count = 0;
        capacity = 0;
        isMinHeap = false;
        isMaxHeap = false;
    }

    public void buildMinHeap(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return; // Nothing to build
        }
        this.array = inputArray;
        this.count = inputArray.length;
        this.capacity = inputArray.length;

        for (int i = (count / 2) - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    public void heapSort(){
        if (count == 0) {
            return; // Heap is empty
        }

        int originalCount = count;
        for (int i = originalCount - 1; i > 0; i--) {
            swap(0, i);
            count--;
            percolateDown(0);
        }
        count = originalCount; // Restore count after sorting
    }

}
