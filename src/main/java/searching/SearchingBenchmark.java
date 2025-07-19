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

    private int targetSmall;
    private int targetMedium;
    private int targetLarge;
    private int targetExtraLarge;

    private int notFoundTarget;

    private UnorderedLinearSearch unorderedLinearSearch;

    @Setup(Level.Trial)
    public void setUp() {
        Random random = new Random(42); // Fixed seed for reproducible results

        // Generate arrays with values in range 0-9999
        smallArray = random.ints(SMALL_ARRAY_SIZE, 0, 10000).toArray();
        mediumArray = random.ints(MEDIUM_ARRAY_SIZE, 0, 10000).toArray();
        largeArray = random.ints(LARGE_ARRAY_SIZE, 0, 10000).toArray();
        extraLargeArray = random.ints(EXTRA_LARGE_ARRAY_SIZE, 0, 10000).toArray();

        // Select targets that exist in the arrays (middle position for average case)
        targetSmall = smallArray[SMALL_ARRAY_SIZE / 2];
        targetMedium = mediumArray[MEDIUM_ARRAY_SIZE / 2];
        targetLarge = largeArray[LARGE_ARRAY_SIZE / 2];
        targetExtraLarge = extraLargeArray[EXTRA_LARGE_ARRAY_SIZE / 2];

        // Target that doesn't exist in any array (worst case scenario)
        notFoundTarget = -1;

        unorderedLinearSearch = new UnorderedLinearSearch();
    }

    // Best Case Scenarios - target at the beginning

    @Benchmark
    public int unorderedLinearSearchSmallBestCase() {
        return unorderedLinearSearch.search(smallArray, smallArray[0]);
    }

    @Benchmark
    public int unorderedLinearSearchMediumBestCase() {
        return unorderedLinearSearch.search(mediumArray, mediumArray[0]);
    }

    @Benchmark
    public int unorderedLinearSearchLargeBestCase() {
        return unorderedLinearSearch.search(largeArray, largeArray[0]);
    }

    @Benchmark
    public int unorderedLinearSearchExtraLargeBestCase() {
        return unorderedLinearSearch.search(extraLargeArray, extraLargeArray[0]);
    }

    // Average Case Scenarios - target in the middle

    @Benchmark
    public int unorderedLinearSearchSmallAverageCase() {
        return unorderedLinearSearch.search(smallArray, targetSmall);
    }

    @Benchmark
    public int unorderedLinearSearchMediumAverageCase() {
        return unorderedLinearSearch.search(mediumArray, targetMedium);
    }

    @Benchmark
    public int unorderedLinearSearchLargeAverageCase() {
        return unorderedLinearSearch.search(largeArray, targetLarge);
    }

    @Benchmark
    public int unorderedLinearSearchExtraLargeAverageCase() {
        return unorderedLinearSearch.search(extraLargeArray, targetExtraLarge);
    }

    // Worst Case Scenarios - target at the end

    @Benchmark
    public int unorderedLinearSearchSmallWorstCase() {
        return unorderedLinearSearch.search(smallArray, smallArray[SMALL_ARRAY_SIZE - 1]);
    }

    @Benchmark
    public int unorderedLinearSearchMediumWorstCase() {
        return unorderedLinearSearch.search(mediumArray, mediumArray[MEDIUM_ARRAY_SIZE - 1]);
    }

    @Benchmark
    public int unorderedLinearSearchLargeWorstCase() {
        return unorderedLinearSearch.search(largeArray, largeArray[LARGE_ARRAY_SIZE - 1]);
    }

    @Benchmark
    public int unorderedLinearSearchExtraLargeWorstCase() {
        return unorderedLinearSearch.search(extraLargeArray, extraLargeArray[EXTRA_LARGE_ARRAY_SIZE - 1]);
    }

    // Not Found Scenarios - element doesn't exist (absolute worst case)

    @Benchmark
    public int unorderedLinearSearchSmallNotFound() {
        return unorderedLinearSearch.search(smallArray, notFoundTarget);
    }

    @Benchmark
    public int unorderedLinearSearchMediumNotFound() {
        return unorderedLinearSearch.search(mediumArray, notFoundTarget);
    }

    @Benchmark
    public int unorderedLinearSearchLargeNotFound() {
        return unorderedLinearSearch.search(largeArray, notFoundTarget);
    }

    @Benchmark
    public int unorderedLinearSearchExtraLargeNotFound() {
        return unorderedLinearSearch.search(extraLargeArray, notFoundTarget);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SearchingBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
