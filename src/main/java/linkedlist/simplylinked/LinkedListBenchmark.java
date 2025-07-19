package linkedlist.simplylinked;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class LinkedListBenchmark {

    private static final int SMALL_SIZE = 100;
    private static final int MEDIUM_SIZE = 1000;
    private static final int LARGE_SIZE = 10000;

    private int[] testData;

    @Setup(Level.Trial)
    public void setUp() {
        Random random = new Random(42);
        testData = random.ints(LARGE_SIZE, 0, 10000).toArray();
    }

    // Insertion at Beginning Benchmarks
    @Benchmark
    public void insertAtBeginningSmall() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < SMALL_SIZE; i++) {
            list.insertAtBeginning(testData[i]);
        }
    }

    @Benchmark
    public void insertAtBeginningMedium() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            list.insertAtBeginning(testData[i]);
        }
    }

    @Benchmark
    public void insertAtBeginningLarge() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < LARGE_SIZE; i++) {
            list.insertAtBeginning(testData[i]);
        }
    }

    // Insertion at End Benchmarks
    @Benchmark
    public void insertAtEndSmall() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < SMALL_SIZE; i++) {
            list.insertAtEnd(testData[i]);
        }
    }

    @Benchmark
    public void insertAtEndMedium() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            list.insertAtEnd(testData[i]);
        }
    }

    @Benchmark
    public void insertAtEndLarge() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < LARGE_SIZE; i++) {
            list.insertAtEnd(testData[i]);
        }
    }

    // Insertion at Position Benchmarks
    @Benchmark
    public void insertAtPositionSmall() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < SMALL_SIZE; i++) {
            list.insertData(testData[i], i);
        }
    }

    @Benchmark
    public void insertAtPositionMedium() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            list.insertData(testData[i], i);
        }
    }

    @Benchmark
    public void insertAtPositionLarge() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < LARGE_SIZE; i++) {
            list.insertData(testData[i], i);
        }
    }

    // Traversal Benchmarks (since search/delete methods don't exist)
    @Benchmark
    public int traverseSmall() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < SMALL_SIZE; i++) {
            list.insertAtEnd(testData[i]);
        }

        int count = 0;
        LinkedListNode current = list.getHead();
        while (current != null) {
            count += current.getData(); // Process the data to prevent dead code elimination
            current = current.getNext();
        }
        return count;
    }

    @Benchmark
    public int traverseMedium() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            list.insertAtEnd(testData[i]);
        }

        int count = 0;
        LinkedListNode current = list.getHead();
        while (current != null) {
            count += current.getData();
            current = current.getNext();
        }
        return count;
    }

    @Benchmark
    public int traverseLarge() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < LARGE_SIZE; i++) {
            list.insertAtEnd(testData[i]);
        }

        int count = 0;
        LinkedListNode current = list.getHead();
        while (current != null) {
            count += current.getData();
            current = current.getNext();
        }
        return count;
    }

    // Linear Search Benchmarks (implemented manually since search() doesn't exist)
    @Benchmark
    public boolean searchSmall() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < SMALL_SIZE; i++) {
            list.insertAtEnd(testData[i]);
        }

        int target = testData[SMALL_SIZE / 2];
        LinkedListNode current = list.getHead();
        while (current != null) {
            if (current.getData() == target) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Benchmark
    public boolean searchMedium() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            list.insertAtEnd(testData[i]);
        }

        int target = testData[MEDIUM_SIZE / 2];
        LinkedListNode current = list.getHead();
        while (current != null) {
            if (current.getData() == target) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Benchmark
    public boolean searchLarge() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < LARGE_SIZE; i++) {
            list.insertAtEnd(testData[i]);
        }

        int target = testData[LARGE_SIZE / 2];
        LinkedListNode current = list.getHead();
        while (current != null) {
            if (current.getData() == target) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LinkedListBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
