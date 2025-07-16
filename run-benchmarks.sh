#!/bin/bash

# Script to run JMH benchmarks for sorting algorithms

echo "🚀 Running JMH Benchmarks for Sorting Algorithms"
echo "================================================"

# Build the benchmark JAR if it doesn't exist or is outdated
if [ ! -f target/benchmarks.jar ] || [ pom.xml -nt target/benchmarks.jar ]; then
    echo "📦 Building benchmark JAR..."
    mvn clean package -DskipTests -q
    if [ $? -ne 0 ]; then
        echo "❌ Failed to build benchmark JAR"
        exit 1
    fi
    echo "✅ Benchmark JAR built successfully"
fi

# Create results directory
mkdir -p results

# Run benchmarks with JSON output
echo "🏃 Running benchmarks..."
echo "This may take several minutes depending on the number of iterations..."

java -jar target/benchmarks.jar \
    -rf json \
    -rff results/jmh-results-$(date +%Y%m%d-%H%M%S).json \
    -wi 3 \
    -i 5 \
    -f 1 \
    "$@"

if [ $? -eq 0 ]; then
    echo "✅ Benchmarks completed successfully!"
    echo "📊 Results saved in the results/ directory"

    # Find the most recent results file
    LATEST_RESULT=$(ls -t results/jmh-results-*.json 2>/dev/null | head -n1)

    if [ -n "$LATEST_RESULT" ]; then
        echo "📈 Latest results: $LATEST_RESULT"

        # Display a quick summary if jq is available
        if command -v jq >/dev/null 2>&1; then
            echo ""
            echo "🔍 Quick Summary (Top 5 fastest):"
            echo "================================="
            jq -r '.[] | "\(.benchmark | split(".") | .[-1]): \(.primaryMetric.score | tonumber | . * 1000 | round / 1000) \(.primaryMetric.scoreUnit)"' "$LATEST_RESULT" | \
            sort -k2 -n | head -5 | nl
        fi
    fi
else
    echo "❌ Benchmarks failed"
    exit 1
fi
