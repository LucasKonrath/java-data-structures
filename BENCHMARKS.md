# JMH Benchmarks for Sorting Algorithms

## Overview

This project now includes comprehensive JMH (Java Microbenchmark Harness) benchmarks for all sorting algorithms. The benchmarks are configured to test performance across different array sizes.

## Quick Start

### 1. Build the Benchmark JAR
```bash
mvn clean package -DskipTests
```

### 2. Run All Benchmarks
```bash
java -jar target/benchmarks.jar
```

### 3. Run Specific Benchmarks
```bash
# Run only small array benchmarks
java -jar target/benchmarks.jar ".*Small.*"

# Run only bubble sort benchmarks
java -jar target/benchmarks.jar ".*bubbleSort.*"

# Run benchmarks for a specific algorithm and size
java -jar target/benchmarks.jar ".*mergeSortMedium.*"
```

### 4. Use the Convenience Script
```bash
# Make executable and run
chmod +x run-benchmarks.sh
./run-benchmarks.sh

# Run specific benchmarks with the script
./run-benchmarks.sh ".*Large.*"
```

## Benchmark Configuration

The benchmarks test the following algorithms:
- **BubbleSort**: O(n²) comparison-based sorting
- **InsertionSort**: O(n²) efficient for small datasets
- **SelectionSort**: O(n²) with consistent performance
- **MergeSort**: O(n log n) divide-and-conquer algorithm
- **ShellSort**: O(n log n) to O(n²) gap-based insertion sort

Each algorithm is tested with three array sizes:
- **Small**: 100 elements
- **Medium**: 1,000 elements  
- **Large**: 10,000 elements

## JMH Output Options

### JSON Output (for automation)
```bash
java -jar target/benchmarks.jar -rf json -rff results.json
```

### CSV Output
```bash
java -jar target/benchmarks.jar -rf csv -rff results.csv
```

### Custom Configuration
```bash
java -jar target/benchmarks.jar \
    -wi 3 \        # 3 warmup iterations
    -i 5 \         # 5 measurement iterations
    -f 2 \         # 2 forks
    -tu us         # Time unit: microseconds
```

## GitHub Actions Integration

The project includes a GitHub Action (`.github/workflows/jmh-benchmarks.yml`) that:
- Runs benchmarks automatically on pushes and PRs
- Generates formatted performance reports
- Posts results as PR comments
- Creates weekly performance reports
- Uploads benchmark artifacts

## Expected Results

You should expect to see results similar to:
```
Benchmark                               Mode  Cnt    Score    Error  Units
SortingBenchmark.bubbleSortSmall        avgt    5   45.234 ± 2.123  us/op
SortingBenchmark.insertionSortSmall     avgt    5   12.456 ± 0.789  us/op
SortingBenchmark.mergeSortSmall         avgt    5    8.123 ± 0.456  us/op
```

## Troubleshooting

If you encounter issues:

1. **"Unable to find BenchmarkList"**: Run `mvn clean package -DskipTests` to regenerate JMH metadata
2. **No benchmarks found**: Ensure the benchmark classes have proper `@Benchmark` annotations
3. **OutOfMemoryError**: Increase heap size with `-Xmx4g` when running benchmarks

## Performance Tips

- Run benchmarks on a quiet system for consistent results
- Use multiple forks (`-f 3`) for better statistical accuracy
- Increase iterations (`-i 10`) for more precise measurements
- Consider system warm-up time before critical measurements
