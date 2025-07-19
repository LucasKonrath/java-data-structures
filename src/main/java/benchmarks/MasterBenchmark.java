package benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms4G", "-Xmx4G"})
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class MasterBenchmark {

    /**
     * Master benchmark runner for all data structures in the project.
     *
     * This class serves as a centralized entry point to run benchmarks for:
     * - Sorting Algorithms (Bubble, Insertion, Selection, Merge, Quick, Shell, Tree, Counting, Bucket, Radix)
     * - Searching Algorithms (Unordered Linear, Ordered Linear, Binary Search)
     * - Heap Operations (Min/Max Heap insertion, extraction, peek)
     * - Linked List Operations (insertion, deletion, search, traversal)
     * - Queue Operations (Array-based and LinkedList-based enqueue/dequeue)
     * - Stack Operations (Dynamic Array and LinkedList push/pop)
     * - Tree Operations (Binary Tree traversals, BST/AVL insertion/search)
     * - Disjoint Set Operations (makeSet, union, find with path compression)
     */

    public static void main(String[] args) throws RunnerException {

        System.out.println("🚀 Master Data Structures Benchmark Suite");
        System.out.println("==========================================");
        System.out.println("This will run comprehensive benchmarks for ALL data structures:");
        System.out.println("• Sorting Algorithms (10 algorithms × 3 sizes = 30 benchmarks)");
        System.out.println("• Searching Algorithms (3 algorithms × 4 sizes × 5 scenarios = 60 benchmarks)");
        System.out.println("• Heap Operations (Min/Max heaps × 4 sizes × 3 operations = 24 benchmarks)");
        System.out.println("• Linked List Operations (6 operations × 3 sizes = 18 benchmarks)");
        System.out.println("• Queue Operations (2 implementations × 6 operations × 3 sizes = 36 benchmarks)");
        System.out.println("• Stack Operations (2 implementations × 6 operations × 3 sizes = 36 benchmarks)");
        System.out.println("• Tree Operations (3 tree types × multiple operations × 3 sizes = 45+ benchmarks)");
        System.out.println("• Disjoint Set Operations (5 operations × 4 sizes = 20 benchmarks)");
        System.out.println("==========================================");
        System.out.println("Total: 250+ individual benchmark scenarios");
        System.out.println("Estimated runtime: 15-30 minutes depending on system performance");
        System.out.println("==========================================");

        String pattern = ".*";
        if (args.length > 0) {
            pattern = args[0];
            System.out.println("Running benchmarks matching pattern: " + pattern);
        } else {
            System.out.println("Running ALL benchmarks (use pattern argument to filter)");
            System.out.println("Examples:");
            System.out.println("  java -jar benchmarks.jar \".*Sorting.*\"     # Only sorting");
            System.out.println("  java -jar benchmarks.jar \".*Small.*\"       # Only small datasets");
            System.out.println("  java -jar benchmarks.jar \".*heap.*\"        # Only heap operations");
        }

        Options opt = new OptionsBuilder()
                .include(pattern)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-server")
                .build();

        System.out.println("\n🏃 Starting benchmark execution...\n");
        new Runner(opt).run();

        System.out.println("\n🎉 Benchmark execution completed!");
        System.out.println("Results are available in JSON format for further analysis.");
    }
}
