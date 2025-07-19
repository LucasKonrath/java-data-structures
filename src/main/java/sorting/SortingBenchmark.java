package sorting;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class SortingBenchmark {

    private static final int SMALL_ARRAY_SIZE = 100;
    private static final int MEDIUM_ARRAY_SIZE = 1000;
    private static final int LARGE_ARRAY_SIZE = 10000;

    private int[] smallArray;
    private int[] mediumArray;
    private int[] largeArray;

    private BubbleSort bubbleSort;
    private InsertionSort insertionSort;
    private SelectionSort selectionSort;
    private MergeSort mergeSort;
    private ShellSort shellSort;
    private TreeSort treeSort;
    private CountingSort countingSort;

    @Setup(Level.Trial)
    public void setUp() {
        Random random = new Random(42); // Fixed seed for reproducible results

        smallArray = random.ints(SMALL_ARRAY_SIZE, 0, 1000).toArray();
        mediumArray = random.ints(MEDIUM_ARRAY_SIZE, 0, 1000).toArray();
        largeArray = random.ints(LARGE_ARRAY_SIZE, 0, 1000).toArray();

        bubbleSort = new BubbleSort();
        insertionSort = new InsertionSort();
        selectionSort = new SelectionSort();
        mergeSort = new MergeSort();
        shellSort = new ShellSort();
        treeSort = new TreeSort();
        countingSort = new CountingSort();
    }

    // Small Array Benchmarks (100 elements)

    @Benchmark
    public void bubbleSortSmall() {
        int[] arr = smallArray.clone();
        bubbleSort.bubbleSort(arr);
    }

    @Benchmark
    public void insertionSortSmall() {
        int[] arr = smallArray.clone();
        insertionSort.insertionSort(arr);
    }

    @Benchmark
    public void selectionSortSmall() {
        int[] arr = smallArray.clone();
        selectionSort.selectionSort(arr);
    }

    @Benchmark
    public void mergeSortSmall() {
        int[] arr = smallArray.clone();
        mergeSort.sort(arr);
    }

    @Benchmark
    public void shellSortSmall() {
        int[] arr = smallArray.clone();
        shellSort.shellSort(arr);
    }

    @Benchmark
    public void quickSortSmall() {
        int[] arr = smallArray.clone();
        QuickSort.quickSort(arr, 0, arr.length - 1);
    }

    @Benchmark
    public void treeSortSmall() {
        int[] arr = smallArray.clone();
        treeSort.treeSort(arr);
    }

    @Benchmark
    public void countingSortSmall() {
        int[] arr = smallArray.clone();
        countingSort.countingSort(arr);
    }

    // Medium Array Benchmarks (1,000 elements)

    @Benchmark
    public void bubbleSortMedium() {
        int[] arr = mediumArray.clone();
        bubbleSort.bubbleSort(arr);
    }

    @Benchmark
    public void insertionSortMedium() {
        int[] arr = mediumArray.clone();
        insertionSort.insertionSort(arr);
    }

    @Benchmark
    public void selectionSortMedium() {
        int[] arr = mediumArray.clone();
        selectionSort.selectionSort(arr);
    }

    @Benchmark
    public void mergeSortMedium() {
        int[] arr = mediumArray.clone();
        mergeSort.sort(arr);
    }

    @Benchmark
    public void shellSortMedium() {
        int[] arr = mediumArray.clone();
        shellSort.shellSort(arr);
    }

    @Benchmark
    public void quickSortMedium() {
        int[] arr = mediumArray.clone();
        QuickSort.quickSort(arr, 0, arr.length - 1);
    }

    @Benchmark
    public void treeSortMedium() {
        int[] arr = mediumArray.clone();
        treeSort.treeSort(arr);
    }

    @Benchmark
    public void countingSortMedium() {
        int[] arr = mediumArray.clone();
        countingSort.countingSort(arr);
    }

    // Large Array Benchmarks (10,000 elements)

    @Benchmark
    public void bubbleSortLarge() {
        int[] arr = largeArray.clone();
        bubbleSort.bubbleSort(arr);
    }

    @Benchmark
    public void insertionSortLarge() {
        int[] arr = largeArray.clone();
        insertionSort.insertionSort(arr);
    }

    @Benchmark
    public void selectionSortLarge() {
        int[] arr = largeArray.clone();
        selectionSort.selectionSort(arr);
    }

    @Benchmark
    public void mergeSortLarge() {
        int[] arr = largeArray.clone();
        mergeSort.sort(arr);
    }

    @Benchmark
    public void shellSortLarge() {
        int[] arr = largeArray.clone();
        shellSort.shellSort(arr);
    }

    @Benchmark
    public void quickSortLarge() {
        int[] arr = largeArray.clone();
        QuickSort.quickSort(arr, 0, arr.length - 1);
    }

    @Benchmark
    public void treeSortLarge() {
        int[] arr = largeArray.clone();
        treeSort.treeSort(arr);
    }

    @Benchmark
    public void countingSortLarge() {
        int[] arr = largeArray.clone();
        countingSort.countingSort(arr);
    }
    // Main method to run benchmarks directly
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SortingBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
