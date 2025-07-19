package stack;

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
public class StackBenchmark {

    private static final int SMALL_SIZE = 100;
    private static final int MEDIUM_SIZE = 1000;
    private static final int LARGE_SIZE = 10000;

    private DynamicArrayStack smallDynamicStack;
    private LinkedListStack smallLinkedStack;
    private DynamicArrayStack mediumDynamicStack;
    private LinkedListStack mediumLinkedStack;
    private DynamicArrayStack largeDynamicStack;
    private LinkedListStack largeLinkedStack;

    private int[] testData;

    @Setup(Level.Trial)
    public void setUp() {
        Random random = new Random(42);
        testData = random.ints(LARGE_SIZE, 0, 10000).toArray();
    }

    @Setup(Level.Invocation)
    public void setUpStacks() {
        // Pre-populated stacks for pop operations
        smallDynamicStack = new DynamicArrayStack();
        smallLinkedStack = new LinkedListStack();
        for (int i = 0; i < SMALL_SIZE; i++) {
            smallDynamicStack.push(testData[i]);
            smallLinkedStack.push(testData[i]);
        }

        mediumDynamicStack = new DynamicArrayStack();
        mediumLinkedStack = new LinkedListStack();
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            mediumDynamicStack.push(testData[i]);
            mediumLinkedStack.push(testData[i]);
        }

        largeDynamicStack = new DynamicArrayStack();
        largeLinkedStack = new LinkedListStack();
        for (int i = 0; i < LARGE_SIZE; i++) {
            largeDynamicStack.push(testData[i]);
            largeLinkedStack.push(testData[i]);
        }
    }

    // Dynamic Array Stack Push Benchmarks
    @Benchmark
    public void dynamicArrayStackPushSmall() {
        DynamicArrayStack stack = new DynamicArrayStack();
        for (int i = 0; i < SMALL_SIZE; i++) {
            stack.push(testData[i]);
        }
    }

    @Benchmark
    public void dynamicArrayStackPushMedium() {
        DynamicArrayStack stack = new DynamicArrayStack();
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            stack.push(testData[i]);
        }
    }

    @Benchmark
    public void dynamicArrayStackPushLarge() {
        DynamicArrayStack stack = new DynamicArrayStack();
        for (int i = 0; i < LARGE_SIZE; i++) {
            stack.push(testData[i]);
        }
    }

    // Linked List Stack Push Benchmarks
    @Benchmark
    public void linkedListStackPushSmall() {
        LinkedListStack stack = new LinkedListStack();
        for (int i = 0; i < SMALL_SIZE; i++) {
            stack.push(testData[i]);
        }
    }

    @Benchmark
    public void linkedListStackPushMedium() {
        LinkedListStack stack = new LinkedListStack();
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            stack.push(testData[i]);
        }
    }

    @Benchmark
    public void linkedListStackPushLarge() {
        LinkedListStack stack = new LinkedListStack();
        for (int i = 0; i < LARGE_SIZE; i++) {
            stack.push(testData[i]);
        }
    }

    // Dynamic Array Stack Pop Benchmarks
    @Benchmark
    public int dynamicArrayStackPopSmall() {
        return smallDynamicStack.pop();
    }

    @Benchmark
    public int dynamicArrayStackPopMedium() {
        return mediumDynamicStack.pop();
    }

    @Benchmark
    public int dynamicArrayStackPopLarge() {
        return largeDynamicStack.pop();
    }

    // Linked List Stack Pop Benchmarks
    @Benchmark
    public int linkedListStackPopSmall() {
        return smallLinkedStack.pop();
    }

    @Benchmark
    public int linkedListStackPopMedium() {
        return mediumLinkedStack.pop();
    }

    @Benchmark
    public int linkedListStackPopLarge() {
        return largeLinkedStack.pop();
    }

    // Peek Operations
    @Benchmark
    public int dynamicArrayStackPeekSmall() {
        return smallDynamicStack.peek();
    }

    @Benchmark
    public int dynamicArrayStackPeekMedium() {
        return mediumDynamicStack.peek();
    }

    @Benchmark
    public int dynamicArrayStackPeekLarge() {
        return largeDynamicStack.peek();
    }

    @Benchmark
    public int linkedListStackPeekSmall() {
        return smallLinkedStack.peek();
    }

    @Benchmark
    public int linkedListStackPeekMedium() {
        return mediumLinkedStack.peek();
    }

    @Benchmark
    public int linkedListStackPeekLarge() {
        return largeLinkedStack.peek();
    }

    // Size Operations
    @Benchmark
    public int dynamicArrayStackSizeSmall() {
        return smallDynamicStack.size();
    }

    @Benchmark
    public int dynamicArrayStackSizeMedium() {
        return mediumDynamicStack.size();
    }

    @Benchmark
    public int dynamicArrayStackSizeLarge() {
        return largeDynamicStack.size();
    }

    @Benchmark
    public int linkedListStackSizeSmall() {
        return smallLinkedStack.size();
    }

    @Benchmark
    public int linkedListStackSizeMedium() {
        return mediumLinkedStack.size();
    }

    @Benchmark
    public int linkedListStackSizeLarge() {
        return largeLinkedStack.size();
    }

    // Mixed Operations Benchmarks
    @Benchmark
    public void dynamicArrayStackMixedOperationsSmall() {
        DynamicArrayStack stack = new DynamicArrayStack();
        for (int i = 0; i < SMALL_SIZE; i++) {
            stack.push(testData[i]);
            if (i % 3 == 0 && !stack.isEmpty()) {
                stack.pop();
            }
        }
    }

    @Benchmark
    public void linkedListStackMixedOperationsSmall() {
        LinkedListStack stack = new LinkedListStack();
        for (int i = 0; i < SMALL_SIZE; i++) {
            stack.push(testData[i]);
            if (i % 3 == 0 && !stack.isEmpty()) {
                stack.pop();
            }
        }
    }

    @Benchmark
    public void dynamicArrayStackMixedOperationsMedium() {
        DynamicArrayStack stack = new DynamicArrayStack();
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            stack.push(testData[i]);
            if (i % 3 == 0 && !stack.isEmpty()) {
                stack.pop();
            }
        }
    }

    @Benchmark
    public void linkedListStackMixedOperationsMedium() {
        LinkedListStack stack = new LinkedListStack();
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            stack.push(testData[i]);
            if (i % 3 == 0 && !stack.isEmpty()) {
                stack.pop();
            }
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StackBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
