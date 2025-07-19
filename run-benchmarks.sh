#!/bin/bash

# Script to run JMH benchmarks for data structure algorithms
set -e

echo "🚀 Data Structures Benchmark Runner"

# Build the benchmark JAR if it doesn't exist or is outdated
if [ ! -f target/benchmarks.jar ] || [ pom.xml -nt target/benchmarks.jar ]; then
    echo "📦 Building benchmark JAR..."
    mvn clean package -P jmh-benchmark -q
    if [ $? -ne 0 ]; then
        echo "❌ Failed to build benchmark JAR"
        exit 1
    fi
    echo "✅ Benchmark JAR built successfully"
else
    echo "📦 Using existing benchmark JAR"
fi

# Create results directory
mkdir -p benchmark-results
TIMESTAMP=$(date +"%Y%m%d_%H%M%S")

# Function to run benchmarks for a specific category
run_benchmark() {
    local category=$1
    local pattern=$2
    local description=$3

    echo ""
    echo "🏃 Running $description..."

    local result_file="benchmark-results/${category}_benchmark_${TIMESTAMP}.json"

    java -jar target/benchmarks.jar \
        -f 1 \
        -wi 3 \
        -i 5 \
        -tu ns \
        -rf json \
        -rff "$result_file" \
        "$pattern" 2>/dev/null

    if [ $? -eq 0 ] && [ -f "$result_file" ]; then
        echo "✅ $description completed"
        echo "📊 Results saved to: $result_file"

        # Display top results
        echo "📈 Top 5 fastest results:"
        if command -v jq >/dev/null 2>&1; then
            jq -r '.[] | "\(.benchmark | split(".") | .[-1]): \(.primaryMetric.score | tonumber | . / 1000 | round / 1000) μs"' "$result_file" | \
            sort -k2 -n | head -5 | \
            awk '{printf "   %s\n", $0}'
        else
            echo "   (Install 'jq' for formatted results)"
        fi
    else
        echo "❌ $description failed or no results generated"
    fi
}

# Main menu
echo ""
echo "Select benchmark to run:"
echo "1) Sorting algorithms"
echo "2) Searching algorithms"
echo "3) Heap operations"
echo "4) Linked List operations"
echo "5) Queue operations"
echo "6) Stack operations"
echo "7) Tree operations"
echo "8) Disjoint Set operations"
echo "9) All data structures"
echo "10) Exit"

read -p "Enter your choice (1-10): " choice

case $choice in
    1)
        run_benchmark "sorting" ".*SortingBenchmark.*" "Sorting Algorithm Benchmarks"
        ;;
    2)
        run_benchmark "searching" ".*SearchingBenchmark.*" "Searching Algorithm Benchmarks"
        ;;
    3)
        run_benchmark "heap" ".*HeapBenchmark.*" "Heap Operation Benchmarks"
        ;;
    4)
        run_benchmark "linkedlist" ".*LinkedListBenchmark.*" "Linked List Operation Benchmarks"
        ;;
    5)
        run_benchmark "queue" ".*QueueBenchmark.*" "Queue Operation Benchmarks"
        ;;
    6)
        run_benchmark "stack" ".*StackBenchmark.*" "Stack Operation Benchmarks"
        ;;
    7)
        run_benchmark "tree" ".*TreeBenchmark.*" "Tree Operation Benchmarks"
        ;;
    8)
        run_benchmark "disjointset" ".*DisjointSetBenchmark.*" "Disjoint Set Operation Benchmarks"
        ;;
    9)
        run_benchmark "sorting" ".*SortingBenchmark.*" "Sorting Algorithm Benchmarks"
        run_benchmark "searching" ".*SearchingBenchmark.*" "Searching Algorithm Benchmarks"
        run_benchmark "heap" ".*HeapBenchmark.*" "Heap Operation Benchmarks"
        run_benchmark "linkedlist" ".*LinkedListBenchmark.*" "Linked List Operation Benchmarks"
        run_benchmark "queue" ".*QueueBenchmark.*" "Queue Operation Benchmarks"
        run_benchmark "stack" ".*StackBenchmark.*" "Stack Operation Benchmarks"
        run_benchmark "tree" ".*TreeBenchmark.*" "Tree Operation Benchmarks"
        run_benchmark "disjointset" ".*DisjointSetBenchmark.*" "Disjoint Set Operation Benchmarks"
        ;;
    10)
        echo "👋 Goodbye!"
        exit 0
        ;;
    *)
        echo "❌ Invalid choice. Please run the script again."
        exit 1
        ;;
esac

echo ""
echo "🎉 Benchmark run completed!"
echo "📁 All results are saved in the benchmark-results/ directory"
