package sets.disjoint;

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
public class DisjointSetBenchmark {

    private static final int SMALL_SIZE = 100;
    private static final int MEDIUM_SIZE = 1000;
    private static final int LARGE_SIZE = 10000;
    private static final int EXTRA_LARGE_SIZE = 100000;

    private DisjointSet smallSet;
    private DisjointSet mediumSet;
    private DisjointSet largeSet;
    private DisjointSet extraLargeSet;

    private int[][] unionOperations;
    private int[] findOperations;

    @Setup(Level.Trial)
    public void setUp() {
        Random random = new Random(42);

        // Generate union operations (pairs of elements to union)
        unionOperations = new int[EXTRA_LARGE_SIZE][2];
        for (int i = 0; i < EXTRA_LARGE_SIZE; i++) {
            unionOperations[i][0] = random.nextInt(EXTRA_LARGE_SIZE);
            unionOperations[i][1] = random.nextInt(EXTRA_LARGE_SIZE);
        }

        // Generate find operations
        findOperations = random.ints(EXTRA_LARGE_SIZE, 0, EXTRA_LARGE_SIZE).toArray();
    }

    @Setup(Level.Invocation)
    public void setUpSets() {
        smallSet = new DisjointSet();
        smallSet.makeSet(SMALL_SIZE);

        mediumSet = new DisjointSet();
        mediumSet.makeSet(MEDIUM_SIZE);

        largeSet = new DisjointSet();
        largeSet.makeSet(LARGE_SIZE);

        extraLargeSet = new DisjointSet();
        extraLargeSet.makeSet(EXTRA_LARGE_SIZE);

        // Pre-populate sets with some unions for find benchmarks
        populateSet(smallSet, SMALL_SIZE / 2);
        populateSet(mediumSet, MEDIUM_SIZE / 2);
        populateSet(largeSet, LARGE_SIZE / 2);
        populateSet(extraLargeSet, EXTRA_LARGE_SIZE / 2);
    }

    private void populateSet(DisjointSet set, int numOperations) {
        for (int i = 0; i < numOperations; i++) {
            int x = unionOperations[i][0] % set.size;
            int y = unionOperations[i][1] % set.size;
            set.union(x, y);
        }
    }

    // MakeSet Benchmarks
    @Benchmark
    public void makeSetSmall() {
        DisjointSet set = new DisjointSet();
        set.makeSet(SMALL_SIZE);
    }

    @Benchmark
    public void makeSetMedium() {
        DisjointSet set = new DisjointSet();
        set.makeSet(MEDIUM_SIZE);
    }

    @Benchmark
    public void makeSetLarge() {
        DisjointSet set = new DisjointSet();
        set.makeSet(LARGE_SIZE);
    }

    @Benchmark
    public void makeSetExtraLarge() {
        DisjointSet set = new DisjointSet();
        set.makeSet(EXTRA_LARGE_SIZE);
    }

    // Union Benchmarks
    @Benchmark
    public void unionOperationsSmall() {
        DisjointSet set = new DisjointSet();
        set.makeSet(SMALL_SIZE);
        for (int i = 0; i < SMALL_SIZE / 2; i++) {
            int x = unionOperations[i][0] % SMALL_SIZE;
            int y = unionOperations[i][1] % SMALL_SIZE;
            set.union(x, y);
        }
    }

    @Benchmark
    public void unionOperationsMedium() {
        DisjointSet set = new DisjointSet();
        set.makeSet(MEDIUM_SIZE);
        for (int i = 0; i < MEDIUM_SIZE / 2; i++) {
            int x = unionOperations[i][0] % MEDIUM_SIZE;
            int y = unionOperations[i][1] % MEDIUM_SIZE;
            set.union(x, y);
        }
    }

    @Benchmark
    public void unionOperationsLarge() {
        DisjointSet set = new DisjointSet();
        set.makeSet(LARGE_SIZE);
        for (int i = 0; i < LARGE_SIZE / 2; i++) {
            int x = unionOperations[i][0] % LARGE_SIZE;
            int y = unionOperations[i][1] % LARGE_SIZE;
            set.union(x, y);
        }
    }

    @Benchmark
    public void unionOperationsExtraLarge() {
        DisjointSet set = new DisjointSet();
        set.makeSet(EXTRA_LARGE_SIZE);
        for (int i = 0; i < EXTRA_LARGE_SIZE / 2; i++) {
            int x = unionOperations[i][0] % EXTRA_LARGE_SIZE;
            int y = unionOperations[i][1] % EXTRA_LARGE_SIZE;
            set.union(x, y);
        }
    }

    // Find Benchmarks (with path compression)
    @Benchmark
    public int findOperationSmall() {
        return smallSet.find(findOperations[0] % SMALL_SIZE);
    }

    @Benchmark
    public int findOperationMedium() {
        return mediumSet.find(findOperations[0] % MEDIUM_SIZE);
    }

    @Benchmark
    public int findOperationLarge() {
        return largeSet.find(findOperations[0] % LARGE_SIZE);
    }

    @Benchmark
    public int findOperationExtraLarge() {
        return extraLargeSet.find(findOperations[0] % EXTRA_LARGE_SIZE);
    }

    // Sequential Union Benchmarks (worst case for union-find without union by rank)
    @Benchmark
    public void sequentialUnionSmall() {
        DisjointSet set = new DisjointSet();
        set.makeSet(SMALL_SIZE);
        for (int i = 0; i < SMALL_SIZE - 1; i++) {
            set.union(i, i + 1);
        }
    }

    @Benchmark
    public void sequentialUnionMedium() {
        DisjointSet set = new DisjointSet();
        set.makeSet(MEDIUM_SIZE);
        for (int i = 0; i < MEDIUM_SIZE - 1; i++) {
            set.union(i, i + 1);
        }
    }

    @Benchmark
    public void sequentialUnionLarge() {
        DisjointSet set = new DisjointSet();
        set.makeSet(LARGE_SIZE);
        for (int i = 0; i < LARGE_SIZE - 1; i++) {
            set.union(i, i + 1);
        }
    }

    // Mixed Operations Benchmarks
    @Benchmark
    public void mixedOperationsSmall() {
        DisjointSet set = new DisjointSet();
        set.makeSet(SMALL_SIZE);

        for (int i = 0; i < SMALL_SIZE / 2; i++) {
            // Perform union
            int x = unionOperations[i][0] % SMALL_SIZE;
            int y = unionOperations[i][1] % SMALL_SIZE;
            set.union(x, y);

            // Perform find
            set.find(findOperations[i] % SMALL_SIZE);
        }
    }

    @Benchmark
    public void mixedOperationsMedium() {
        DisjointSet set = new DisjointSet();
        set.makeSet(MEDIUM_SIZE);

        for (int i = 0; i < MEDIUM_SIZE / 2; i++) {
            // Perform union
            int x = unionOperations[i][0] % MEDIUM_SIZE;
            int y = unionOperations[i][1] % MEDIUM_SIZE;
            set.union(x, y);

            // Perform find
            set.find(findOperations[i] % MEDIUM_SIZE);
        }
    }

    @Benchmark
    public void mixedOperationsLarge() {
        DisjointSet set = new DisjointSet();
        set.makeSet(LARGE_SIZE);

        for (int i = 0; i < LARGE_SIZE / 2; i++) {
            // Perform union
            int x = unionOperations[i][0] % LARGE_SIZE;
            int y = unionOperations[i][1] % LARGE_SIZE;
            set.union(x, y);

            // Perform find
            set.find(findOperations[i] % LARGE_SIZE);
        }
    }

    // Connected Components Counting Benchmark
    @Benchmark
    public int countComponentsSmall() {
        int components = 0;
        for (int i = 0; i < SMALL_SIZE; i++) {
            if (smallSet.find(i) == i) {
                components++;
            }
        }
        return components;
    }

    @Benchmark
    public int countComponentsMedium() {
        int components = 0;
        for (int i = 0; i < MEDIUM_SIZE; i++) {
            if (mediumSet.find(i) == i) {
                components++;
            }
        }
        return components;
    }

    @Benchmark
    public int countComponentsLarge() {
        int components = 0;
        for (int i = 0; i < LARGE_SIZE; i++) {
            if (largeSet.find(i) == i) {
                components++;
            }
        }
        return components;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(DisjointSetBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
