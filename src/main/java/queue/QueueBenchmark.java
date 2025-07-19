package queue;

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
public class QueueBenchmark {

    private static final int SMALL_SIZE = 100;
    private static final int MEDIUM_SIZE = 1000;
    private static final int LARGE_SIZE = 10000;

    private Queue arrayQueue;
    private QueueLinkedList linkedQueue;

    private Queue smallArrayQueue;
    private QueueLinkedList smallLinkedQueue;
    private Queue mediumArrayQueue;
    private QueueLinkedList mediumLinkedQueue;
    private Queue largeArrayQueue;
    private QueueLinkedList largeLinkedQueue;

    private int[] testData;

    @Setup(Level.Trial)
    public void setUp() {
        Random random = new Random(42);
        testData = random.ints(LARGE_SIZE, 0, 10000).toArray();
    }

    @Setup(Level.Invocation)
    public void setUpQueues() {
        arrayQueue = new Queue(LARGE_SIZE);
        linkedQueue = new QueueLinkedList();

        // Pre-populated queues for dequeue operations
        smallArrayQueue = new Queue(SMALL_SIZE);
        smallLinkedQueue = new QueueLinkedList();
        for (int i = 0; i < SMALL_SIZE; i++) {
            smallArrayQueue.enqueue(testData[i]);
            smallLinkedQueue.enqueue(testData[i]);
        }

        mediumArrayQueue = new Queue(MEDIUM_SIZE);
        mediumLinkedQueue = new QueueLinkedList();
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            mediumArrayQueue.enqueue(testData[i]);
            mediumLinkedQueue.enqueue(testData[i]);
        }

        largeArrayQueue = new Queue(LARGE_SIZE);
        largeLinkedQueue = new QueueLinkedList();
        for (int i = 0; i < LARGE_SIZE; i++) {
            largeArrayQueue.enqueue(testData[i]);
            largeLinkedQueue.enqueue(testData[i]);
        }
    }

    // Array Queue Enqueue Benchmarks
    @Benchmark
    public void arrayQueueEnqueueSmall() {
        Queue queue = new Queue(SMALL_SIZE);
        for (int i = 0; i < SMALL_SIZE; i++) {
            queue.enqueue(testData[i]);
        }
    }

    @Benchmark
    public void arrayQueueEnqueueMedium() {
        Queue queue = new Queue(MEDIUM_SIZE);
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            queue.enqueue(testData[i]);
        }
    }

    @Benchmark
    public void arrayQueueEnqueueLarge() {
        Queue queue = new Queue(LARGE_SIZE);
        for (int i = 0; i < LARGE_SIZE; i++) {
            queue.enqueue(testData[i]);
        }
    }

    // Linked Queue Enqueue Benchmarks
    @Benchmark
    public void linkedQueueEnqueueSmall() {
        QueueLinkedList queue = new QueueLinkedList();
        for (int i = 0; i < SMALL_SIZE; i++) {
            queue.enqueue(testData[i]);
        }
    }

    @Benchmark
    public void linkedQueueEnqueueMedium() {
        QueueLinkedList queue = new QueueLinkedList();
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            queue.enqueue(testData[i]);
        }
    }

    @Benchmark
    public void linkedQueueEnqueueLarge() {
        QueueLinkedList queue = new QueueLinkedList();
        for (int i = 0; i < LARGE_SIZE; i++) {
            queue.enqueue(testData[i]);
        }
    }

    // Array Queue Dequeue Benchmarks
    @Benchmark
    public int arrayQueueDequeueSmall() {
        return smallArrayQueue.dequeue();
    }

    @Benchmark
    public int arrayQueueDequeueMedium() {
        return mediumArrayQueue.dequeue();
    }

    @Benchmark
    public int arrayQueueDequeueLarge() {
        return largeArrayQueue.dequeue();
    }

    // Linked Queue Dequeue Benchmarks
    @Benchmark
    public int linkedQueueDequeueSmall() {
        return smallLinkedQueue.dequeue();
    }

    @Benchmark
    public int linkedQueueDequeueMedium() {
        return mediumLinkedQueue.dequeue();
    }

    @Benchmark
    public int linkedQueueDequeueLarge() {
        return largeLinkedQueue.dequeue();
    }

    // Peek Operations
    @Benchmark
    public int arrayQueuePeekSmall() {
        return smallArrayQueue.peek();
    }

    @Benchmark
    public int arrayQueuePeekMedium() {
        return mediumArrayQueue.peek();
    }

    @Benchmark
    public int arrayQueuePeekLarge() {
        return largeArrayQueue.peek();
    }

    @Benchmark
    public int linkedQueuePeekSmall() {
        return smallLinkedQueue.peek();
    }

    @Benchmark
    public int linkedQueuePeekMedium() {
        return mediumLinkedQueue.peek();
    }

    @Benchmark
    public int linkedQueuePeekLarge() {
        return largeLinkedQueue.peek();
    }

    // Size Operations
    @Benchmark
    public int arrayQueueSizeSmall() {
        return smallArrayQueue.size();
    }

    @Benchmark
    public int arrayQueueSizeMedium() {
        return mediumArrayQueue.size();
    }

    @Benchmark
    public int arrayQueueSizeLarge() {
        return largeArrayQueue.size();
    }

    @Benchmark
    public int linkedQueueSizeSmall() {
        return smallLinkedQueue.size();
    }

    @Benchmark
    public int linkedQueueSizeMedium() {
        return mediumLinkedQueue.size();
    }

    @Benchmark
    public int linkedQueueSizeLarge() {
        return largeLinkedQueue.size();
    }

    // Mixed Operations Benchmarks
    @Benchmark
    public void arrayQueueMixedOperationsSmall() {
        Queue queue = new Queue(SMALL_SIZE * 2);
        for (int i = 0; i < SMALL_SIZE; i++) {
            queue.enqueue(testData[i]);
            if (i % 3 == 0 && !queue.isEmpty()) {
                queue.dequeue();
            }
        }
    }

    @Benchmark
    public void linkedQueueMixedOperationsSmall() {
        QueueLinkedList queue = new QueueLinkedList();
        for (int i = 0; i < SMALL_SIZE; i++) {
            queue.enqueue(testData[i]);
            if (i % 3 == 0 && !queue.isEmpty()) {
                queue.dequeue();
            }
        }
    }

    @Benchmark
    public void arrayQueueMixedOperationsMedium() {
        Queue queue = new Queue(MEDIUM_SIZE * 2);
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            queue.enqueue(testData[i]);
            if (i % 3 == 0 && !queue.isEmpty()) {
                queue.dequeue();
            }
        }
    }

    @Benchmark
    public void linkedQueueMixedOperationsMedium() {
        QueueLinkedList queue = new QueueLinkedList();
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            queue.enqueue(testData[i]);
            if (i % 3 == 0 && !queue.isEmpty()) {
                queue.dequeue();
            }
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(QueueBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
