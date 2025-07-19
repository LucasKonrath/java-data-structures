package heap;

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
public class HeapBenchmark {

    private static final int SMALL_SIZE = 100;
    private static final int MEDIUM_SIZE = 1000;
    private static final int LARGE_SIZE = 10000;
    private static final int EXTRA_LARGE_SIZE = 100000;

    private Heap smallMinHeap;
    private Heap mediumMinHeap;
    private Heap largeMinHeap;
    private Heap extraLargeMinHeap;

    private Heap smallMaxHeap;
    private Heap mediumMaxHeap;
    private Heap largeMaxHeap;
    private Heap extraLargeMaxHeap;

    private int[] smallData;
    private int[] mediumData;
    private int[] largeData;
    private int[] extraLargeData;

    @Setup(Level.Trial)
    public void setUp() {
        Random random = new Random(42);

        // Generate test data
        smallData = random.ints(SMALL_SIZE, 0, 10000).toArray();
        mediumData = random.ints(MEDIUM_SIZE, 0, 10000).toArray();
        largeData = random.ints(LARGE_SIZE, 0, 10000).toArray();
        extraLargeData = random.ints(EXTRA_LARGE_SIZE, 0, 10000).toArray();

        // Initialize heaps
        smallMinHeap = new Heap(SMALL_SIZE, true);
        mediumMinHeap = new Heap(MEDIUM_SIZE, true);
        largeMinHeap = new Heap(LARGE_SIZE, true);
        extraLargeMinHeap = new Heap(EXTRA_LARGE_SIZE, true);

        smallMaxHeap = new Heap(SMALL_SIZE, false);
        mediumMaxHeap = new Heap(MEDIUM_SIZE, false);
        largeMaxHeap = new Heap(LARGE_SIZE, false);
        extraLargeMaxHeap = new Heap(EXTRA_LARGE_SIZE, false);
    }

    // Min Heap Insertion Benchmarks
    @Benchmark
    public void minHeapInsertionSmall() {
        Heap heap = new Heap(SMALL_SIZE, true);
        for (int value : smallData) {
            heap.insert(value);
        }
    }

    @Benchmark
    public void minHeapInsertionMedium() {
        Heap heap = new Heap(MEDIUM_SIZE, true);
        for (int value : mediumData) {
            heap.insert(value);
        }
    }

    @Benchmark
    public void minHeapInsertionLarge() {
        Heap heap = new Heap(LARGE_SIZE, true);
        for (int value : largeData) {
            heap.insert(value);
        }
    }

    @Benchmark
    public void minHeapInsertionExtraLarge() {
        Heap heap = new Heap(EXTRA_LARGE_SIZE, true);
        for (int value : extraLargeData) {
            heap.insert(value);
        }
    }

    // Max Heap Insertion Benchmarks
    @Benchmark
    public void maxHeapInsertionSmall() {
        Heap heap = new Heap(SMALL_SIZE, false);
        for (int value : smallData) {
            heap.insert(value);
        }
    }

    @Benchmark
    public void maxHeapInsertionMedium() {
        Heap heap = new Heap(MEDIUM_SIZE, false);
        for (int value : mediumData) {
            heap.insert(value);
        }
    }

    @Benchmark
    public void maxHeapInsertionLarge() {
        Heap heap = new Heap(LARGE_SIZE, false);
        for (int value : largeData) {
            heap.insert(value);
        }
    }

    @Benchmark
    public void maxHeapInsertionExtraLarge() {
        Heap heap = new Heap(EXTRA_LARGE_SIZE, false);
        for (int value : extraLargeData) {
            heap.insert(value);
        }
    }

    // Heap Extraction Benchmarks (requires pre-populated heaps)
    @Setup(Level.Invocation)
    public void setUpForExtraction() {
        // Pre-populate heaps for extraction benchmarks
        smallMinHeap = new Heap(SMALL_SIZE, true);
        for (int value : smallData) {
            smallMinHeap.insert(value);
        }

        mediumMinHeap = new Heap(MEDIUM_SIZE, true);
        for (int value : mediumData) {
            mediumMinHeap.insert(value);
        }

        largeMinHeap = new Heap(LARGE_SIZE, true);
        for (int value : largeData) {
            largeMinHeap.insert(value);
        }

        smallMaxHeap = new Heap(SMALL_SIZE, false);
        for (int value : smallData) {
            smallMaxHeap.insert(value);
        }

        mediumMaxHeap = new Heap(MEDIUM_SIZE, false);
        for (int value : mediumData) {
            mediumMaxHeap.insert(value);
        }

        largeMaxHeap = new Heap(LARGE_SIZE, false);
        for (int value : largeData) {
            largeMaxHeap.insert(value);
        }
    }

    @Benchmark
    public int minHeapExtractionSmall() {
        return smallMinHeap.getMin();
    }

    @Benchmark
    public int minHeapExtractionMedium() {
        return mediumMinHeap.getMin();
    }

    @Benchmark
    public int minHeapExtractionLarge() {
        return largeMinHeap.getMin();
    }

    @Benchmark
    public int maxHeapExtractionSmall() {
        return smallMaxHeap.getMax();
    }

    @Benchmark
    public int maxHeapExtractionMedium() {
        return mediumMaxHeap.getMax();
    }

    @Benchmark
    public int maxHeapExtractionLarge() {
        return largeMaxHeap.getMax();
    }

    // Heap Peek Operations
    @Benchmark
    public int minHeapPeekSmall() {
        return smallMinHeap.getMin();
    }

    @Benchmark
    public int minHeapPeekMedium() {
        return mediumMinHeap.getMin();
    }

    @Benchmark
    public int minHeapPeekLarge() {
        return largeMinHeap.getMin();
    }

    @Benchmark
    public int maxHeapPeekSmall() {
        return smallMaxHeap.getMax();
    }

    @Benchmark
    public int maxHeapPeekMedium() {
        return mediumMaxHeap.getMax();
    }

    @Benchmark
    public int maxHeapPeekLarge() {
        return largeMaxHeap.getMax();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(HeapBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
