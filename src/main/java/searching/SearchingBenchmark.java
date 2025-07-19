package searching;

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
public class SearchingBenchmark {

    private static final int SMALL_ARRAY_SIZE = 100;
    private static final int MEDIUM_ARRAY_SIZE = 1000;
    private static final int LARGE_ARRAY_SIZE = 10000;
    private static final int EXTRA_LARGE_ARRAY_SIZE = 100000;

    private int[] smallArray;
    private int[] mediumArray;
    private int[] largeArray;
    private int[] extraLargeArray;

    // Sorted arrays for OrderedLinearSearch
    private int[] smallSortedArray;
    private int[] mediumSortedArray;
    private int[] largeSortedArray;
    private int[] extraLargeSortedArray;

    private int targetSmall;
    private int targetMedium;
    private int targetLarge;
    private int targetExtraLarge;

    private int notFoundTarget;

    private UnorderedLinearSearch unorderedLinearSearch;
    private OrderedLinearSearch orderedLinearSearch;
    private BinarySearch binarySearch;

    @Setup(Level.Trial)
    public void setUp() {
        Random random = new Random(42); // Fixed seed for reproducible results

        // Generate arrays with values in range 0-9999
        smallArray = random.ints(SMALL_ARRAY_SIZE, 0, 10000).toArray();
        mediumArray = random.ints(MEDIUM_ARRAY_SIZE, 0, 10000).toArray();
        largeArray = random.ints(LARGE_ARRAY_SIZE, 0, 10000).toArray();
        extraLargeArray = random.ints(EXTRA_LARGE_ARRAY_SIZE, 0, 10000).toArray();

        // Create sorted versions for OrderedLinearSearch and BinarySearch
        smallSortedArray = smallArray.clone();
        mediumSortedArray = mediumArray.clone();
        largeSortedArray = largeArray.clone();
        extraLargeSortedArray = extraLargeArray.clone();

        java.util.Arrays.sort(smallSortedArray);
        java.util.Arrays.sort(mediumSortedArray);
        java.util.Arrays.sort(largeSortedArray);
        java.util.Arrays.sort(extraLargeSortedArray);

        // Select targets that exist in the arrays (middle position for average case)
        targetSmall = smallArray[SMALL_ARRAY_SIZE / 2];
        targetMedium = mediumArray[MEDIUM_ARRAY_SIZE / 2];
        targetLarge = largeArray[LARGE_ARRAY_SIZE / 2];
        targetExtraLarge = extraLargeArray[EXTRA_LARGE_ARRAY_SIZE / 2];

        // Target that doesn't exist in any array (worst case scenario)
        notFoundTarget = -1;

        unorderedLinearSearch = new UnorderedLinearSearch();
        orderedLinearSearch = new OrderedLinearSearch();
        binarySearch = new BinarySearch();
    }

    // Best Case Scenarios - target at the beginning

    @Benchmark
    public int unorderedLinearSearchSmallBestCase() {
        return unorderedLinearSearch.search(smallArray, smallArray[0]);
    }

    @Benchmark
    public int orderedLinearSearchSmallBestCase() {
        return orderedLinearSearch.search(smallSortedArray, smallSortedArray[0]);
    }

    @Benchmark
    public int binarySearchSmallBestCase() {
        // For binary search, best case is finding the element in the middle
        return binarySearch.search(smallSortedArray, smallSortedArray[SMALL_ARRAY_SIZE / 2]);
    }

    @Benchmark
    public int unorderedLinearSearchMediumBestCase() {
        return unorderedLinearSearch.search(mediumArray, mediumArray[0]);
    }

    @Benchmark
    public int orderedLinearSearchMediumBestCase() {
        return orderedLinearSearch.search(mediumSortedArray, mediumSortedArray[0]);
    }

    @Benchmark
    public int binarySearchMediumBestCase() {
        return binarySearch.search(mediumSortedArray, mediumSortedArray[MEDIUM_ARRAY_SIZE / 2]);
    }

    @Benchmark
    public int unorderedLinearSearchLargeBestCase() {
        return unorderedLinearSearch.search(largeArray, largeArray[0]);
    }

    @Benchmark
    public int orderedLinearSearchLargeBestCase() {
        return orderedLinearSearch.search(largeSortedArray, largeSortedArray[0]);
    }

    @Benchmark
    public int binarySearchLargeBestCase() {
        return binarySearch.search(largeSortedArray, largeSortedArray[LARGE_ARRAY_SIZE / 2]);
    }

    @Benchmark
    public int unorderedLinearSearchExtraLargeBestCase() {
        return unorderedLinearSearch.search(extraLargeArray, extraLargeArray[0]);
    }

    @Benchmark
    public int orderedLinearSearchExtraLargeBestCase() {
        return orderedLinearSearch.search(extraLargeSortedArray, extraLargeSortedArray[0]);
    }

    @Benchmark
    public int binarySearchExtraLargeBestCase() {
        return binarySearch.search(extraLargeSortedArray, extraLargeSortedArray[EXTRA_LARGE_ARRAY_SIZE / 2]);
    }

    // Average Case Scenarios - target in the middle

    @Benchmark
    public int unorderedLinearSearchSmallAverageCase() {
        return unorderedLinearSearch.search(smallArray, targetSmall);
    }

    @Benchmark
    public int orderedLinearSearchSmallAverageCase() {
        return orderedLinearSearch.search(smallSortedArray, smallSortedArray[SMALL_ARRAY_SIZE / 2]);
    }

    @Benchmark
    public int binarySearchSmallAverageCase() {
        // For binary search, we'll search for an element at 1/4 or 3/4 position for average case
        return binarySearch.search(smallSortedArray, smallSortedArray[SMALL_ARRAY_SIZE / 4]);
    }

    @Benchmark
    public int unorderedLinearSearchMediumAverageCase() {
        return unorderedLinearSearch.search(mediumArray, targetMedium);
    }

    @Benchmark
    public int orderedLinearSearchMediumAverageCase() {
        return orderedLinearSearch.search(mediumSortedArray, mediumSortedArray[MEDIUM_ARRAY_SIZE / 2]);
    }

    @Benchmark
    public int binarySearchMediumAverageCase() {
        return binarySearch.search(mediumSortedArray, mediumSortedArray[MEDIUM_ARRAY_SIZE / 4]);
    }

    @Benchmark
    public int unorderedLinearSearchLargeAverageCase() {
        return unorderedLinearSearch.search(largeArray, targetLarge);
    }

    @Benchmark
    public int orderedLinearSearchLargeAverageCase() {
        return orderedLinearSearch.search(largeSortedArray, largeSortedArray[LARGE_ARRAY_SIZE / 2]);
    }

    @Benchmark
    public int binarySearchLargeAverageCase() {
        return binarySearch.search(largeSortedArray, largeSortedArray[LARGE_ARRAY_SIZE / 4]);
    }

    @Benchmark
    public int unorderedLinearSearchExtraLargeAverageCase() {
        return unorderedLinearSearch.search(extraLargeArray, targetExtraLarge);
    }

    @Benchmark
    public int orderedLinearSearchExtraLargeAverageCase() {
        return orderedLinearSearch.search(extraLargeSortedArray, extraLargeSortedArray[EXTRA_LARGE_ARRAY_SIZE / 2]);
    }

    @Benchmark
    public int binarySearchExtraLargeAverageCase() {
        return binarySearch.search(extraLargeSortedArray, extraLargeSortedArray[EXTRA_LARGE_ARRAY_SIZE / 4]);
    }

    // Worst Case Scenarios

    @Benchmark
    public int unorderedLinearSearchSmallWorstCase() {
        return unorderedLinearSearch.search(smallArray, smallArray[SMALL_ARRAY_SIZE - 1]);
    }

    @Benchmark
    public int orderedLinearSearchSmallWorstCase() {
        return orderedLinearSearch.search(smallSortedArray, smallSortedArray[SMALL_ARRAY_SIZE - 1]);
    }

    @Benchmark
    public int binarySearchSmallWorstCase() {
        // For binary search, worst case is searching for first or last element
        return binarySearch.search(smallSortedArray, smallSortedArray[0]);
    }

    @Benchmark
    public int unorderedLinearSearchMediumWorstCase() {
        return unorderedLinearSearch.search(mediumArray, mediumArray[MEDIUM_ARRAY_SIZE - 1]);
    }

    @Benchmark
    public int orderedLinearSearchMediumWorstCase() {
        return orderedLinearSearch.search(mediumSortedArray, mediumSortedArray[MEDIUM_ARRAY_SIZE - 1]);
    }

    @Benchmark
    public int binarySearchMediumWorstCase() {
        return binarySearch.search(mediumSortedArray, mediumSortedArray[0]);
    }

    @Benchmark
    public int unorderedLinearSearchLargeWorstCase() {
        return unorderedLinearSearch.search(largeArray, largeArray[LARGE_ARRAY_SIZE - 1]);
    }

    @Benchmark
    public int orderedLinearSearchLargeWorstCase() {
        return orderedLinearSearch.search(largeSortedArray, largeSortedArray[LARGE_ARRAY_SIZE - 1]);
    }

    @Benchmark
    public int binarySearchLargeWorstCase() {
        return binarySearch.search(largeSortedArray, largeSortedArray[0]);
    }

    @Benchmark
    public int unorderedLinearSearchExtraLargeWorstCase() {
        return unorderedLinearSearch.search(extraLargeArray, extraLargeArray[EXTRA_LARGE_ARRAY_SIZE - 1]);
    }

    @Benchmark
    public int orderedLinearSearchExtraLargeWorstCase() {
        return orderedLinearSearch.search(extraLargeSortedArray, extraLargeSortedArray[EXTRA_LARGE_ARRAY_SIZE - 1]);
    }

    @Benchmark
    public int binarySearchExtraLargeWorstCase() {
        return binarySearch.search(extraLargeSortedArray, extraLargeSortedArray[0]);
    }

    // Not Found Scenarios - element doesn't exist

    @Benchmark
    public int unorderedLinearSearchSmallNotFound() {
        return unorderedLinearSearch.search(smallArray, notFoundTarget);
    }

    @Benchmark
    public int orderedLinearSearchSmallNotFound() {
        return orderedLinearSearch.search(smallSortedArray, notFoundTarget);
    }

    @Benchmark
    public int binarySearchSmallNotFound() {
        return binarySearch.search(smallSortedArray, notFoundTarget);
    }

    @Benchmark
    public int unorderedLinearSearchMediumNotFound() {
        return unorderedLinearSearch.search(mediumArray, notFoundTarget);
    }

    @Benchmark
    public int orderedLinearSearchMediumNotFound() {
        return orderedLinearSearch.search(mediumSortedArray, notFoundTarget);
    }

    @Benchmark
    public int binarySearchMediumNotFound() {
        return binarySearch.search(mediumSortedArray, notFoundTarget);
    }

    @Benchmark
    public int unorderedLinearSearchLargeNotFound() {
        return unorderedLinearSearch.search(largeArray, notFoundTarget);
    }

    @Benchmark
    public int orderedLinearSearchLargeNotFound() {
        return orderedLinearSearch.search(largeSortedArray, notFoundTarget);
    }

    @Benchmark
    public int binarySearchLargeNotFound() {
        return binarySearch.search(largeSortedArray, notFoundTarget);
    }

    @Benchmark
    public int unorderedLinearSearchExtraLargeNotFound() {
        return unorderedLinearSearch.search(extraLargeArray, notFoundTarget);
    }

    @Benchmark
    public int orderedLinearSearchExtraLargeNotFound() {
        return orderedLinearSearch.search(extraLargeSortedArray, notFoundTarget);
    }

    @Benchmark
    public int binarySearchExtraLargeNotFound() {
        return binarySearch.search(extraLargeSortedArray, notFoundTarget);
    }

    // Early Exit Optimization Benchmarks - OrderedLinearSearch and BinarySearch advantages

    @Benchmark
    public int orderedLinearSearchSmallEarlyExit() {
        // Target between first and second element - should exit very early
        int target = (smallSortedArray[0] + smallSortedArray[1]) / 2;
        return orderedLinearSearch.search(smallSortedArray, target);
    }

    @Benchmark
    public int binarySearchSmallEarlyExit() {
        // Target between first and second element
        int target = (smallSortedArray[0] + smallSortedArray[1]) / 2;
        return binarySearch.search(smallSortedArray, target);
    }

    @Benchmark
    public int orderedLinearSearchMediumEarlyExit() {
        int target = (mediumSortedArray[0] + mediumSortedArray[1]) / 2;
        return orderedLinearSearch.search(mediumSortedArray, target);
    }

    @Benchmark
    public int binarySearchMediumEarlyExit() {
        int target = (mediumSortedArray[0] + mediumSortedArray[1]) / 2;
        return binarySearch.search(mediumSortedArray, target);
    }

    @Benchmark
    public int orderedLinearSearchLargeEarlyExit() {
        int target = (largeSortedArray[0] + largeSortedArray[1]) / 2;
        return orderedLinearSearch.search(largeSortedArray, target);
    }

    @Benchmark
    public int binarySearchLargeEarlyExit() {
        int target = (largeSortedArray[0] + largeSortedArray[1]) / 2;
        return binarySearch.search(largeSortedArray, target);
    }

    @Benchmark
    public int orderedLinearSearchExtraLargeEarlyExit() {
        int target = (extraLargeSortedArray[0] + extraLargeSortedArray[1]) / 2;
        return orderedLinearSearch.search(extraLargeSortedArray, target);
    }

    @Benchmark
    public int binarySearchExtraLargeEarlyExit() {
        int target = (extraLargeSortedArray[0] + extraLargeSortedArray[1]) / 2;
        return binarySearch.search(extraLargeSortedArray, target);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SearchingBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
