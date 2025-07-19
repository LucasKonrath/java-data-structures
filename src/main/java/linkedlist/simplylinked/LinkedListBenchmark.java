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

    private SimplyLinkedList smallList;
    private SimplyLinkedList mediumList;
    private SimplyLinkedList largeList;

    private int[] testData;
    private Random random;

    @Setup(Level.Trial)
    public void setUp() {
        random = new Random(42);
        testData = random.ints(LARGE_SIZE, 0, 10000).toArray();
    }

    @Setup(Level.Invocation)
    public void setUpLists() {
        smallList = new SimplyLinkedList();
        mediumList = new SimplyLinkedList();
        largeList = new SimplyLinkedList();

        // Pre-populate lists for certain benchmarks
        for (int i = 0; i < SMALL_SIZE; i++) {
            smallList.insertAtBeginning(testData[i]);
        }
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            mediumList.insertAtBeginning(testData[i]);
        }
        for (int i = 0; i < LARGE_SIZE; i++) {
            largeList.insertAtBeginning(testData[i]);
        }
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

    // Search Benchmarks
    @Benchmark
    public boolean searchSmall() {
        return smallList.search(testData[SMALL_SIZE / 2]);
    }

    @Benchmark
    public boolean searchMedium() {
        return mediumList.search(testData[MEDIUM_SIZE / 2]);
    }

    @Benchmark
    public boolean searchLarge() {
        return largeList.search(testData[LARGE_SIZE / 2]);
    }

    // Deletion Benchmarks
    @Benchmark
    public void deleteFromBeginningSmall() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < SMALL_SIZE; i++) {
            list.insertAtBeginning(testData[i]);
        }
        for (int i = 0; i < SMALL_SIZE / 2; i++) {
            list.deleteFromBeginning();
        }
    }

    @Benchmark
    public void deleteFromBeginningMedium() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            list.insertAtBeginning(testData[i]);
        }
        for (int i = 0; i < MEDIUM_SIZE / 2; i++) {
            list.deleteFromBeginning();
        }
    }

    @Benchmark
    public void deleteFromBeginningLarge() {
        SimplyLinkedList list = new SimplyLinkedList();
        for (int i = 0; i < LARGE_SIZE; i++) {
            list.insertAtBeginning(testData[i]);
        }
        for (int i = 0; i < LARGE_SIZE / 2; i++) {
            list.deleteFromBeginning();
        }
    }

    // Get Length Benchmarks
    @Benchmark
    public int getLengthSmall() {
        return smallList.getLength();
    }

    @Benchmark
    public int getLengthMedium() {
        return mediumList.getLength();
    }

    @Benchmark
    public int getLengthLarge() {
        return largeList.getLength();
    }

    // Traversal Benchmarks
    @Benchmark
    public void traverseSmall() {
        LinkedListNode current = smallList.getHead();
        while (current != null) {
            current.getData(); // Access the data
            current = current.getNext();
        }
    }

    @Benchmark
    public void traverseMedium() {
        LinkedListNode current = mediumList.getHead();
        while (current != null) {
            current.getData(); // Access the data
            current = current.getNext();
        }
    }

    @Benchmark
    public void traverseLarge() {
        LinkedListNode current = largeList.getHead();
        while (current != null) {
            current.getData(); // Access the data
            current = current.getNext();
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LinkedListBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
