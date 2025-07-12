#!/bin/bash

# Test Coverage Report Generator for Java Data Structures Project
echo "=========================================="
echo "Test Coverage Report Generator"
echo "=========================================="

# Clean and compile the project
echo "Cleaning and compiling project..."
mvn clean compile

# Run tests with coverage
echo "Running tests with coverage analysis..."
mvn test

# Generate coverage reports
echo "Generating coverage reports..."
mvn jacoco:report

# Generate site reports
echo "Generating site documentation with coverage..."
mvn site

# Display coverage summary
echo ""
echo "=========================================="
echo "Coverage Summary"
echo "=========================================="

# Check if jacoco.csv exists and display summary
if [ -f "target/site/jacoco/jacoco.csv" ]; then
    echo "Overall Coverage Metrics:"
    echo "------------------------"

    # Parse the CSV file to extract coverage metrics
    tail -n 1 target/site/jacoco/jacoco.csv | awk -F',' '
    {
        missed_instructions = $4
        covered_instructions = $5
        total_instructions = missed_instructions + covered_instructions
        instruction_coverage = (covered_instructions / total_instructions) * 100

        missed_branches = $6
        covered_branches = $7
        total_branches = missed_branches + covered_branches
        branch_coverage = total_branches > 0 ? (covered_branches / total_branches) * 100 : 0

        missed_lines = $8
        covered_lines = $9
        total_lines = missed_lines + covered_lines
        line_coverage = (covered_lines / total_lines) * 100

        printf "Instruction Coverage: %.1f%% (%d/%d)\n", instruction_coverage, covered_instructions, total_instructions
        printf "Branch Coverage: %.1f%% (%d/%d)\n", branch_coverage, covered_branches, total_branches
        printf "Line Coverage: %.1f%% (%d/%d)\n", line_coverage, covered_lines, total_lines
    }'

    echo ""
    echo "Detailed Reports Available At:"
    echo "- HTML Report: target/site/jacoco/index.html"
    echo "- CSV Report: target/site/jacoco/jacoco.csv"
    echo "- XML Report: target/site/jacoco/jacoco.xml"
else
    echo "Coverage reports not found. Please run 'mvn test jacoco:report' first."
fi

echo ""
echo "Test Results:"
echo "-------------"
if [ -d "target/surefire-reports" ]; then
    test_files=$(ls target/surefire-reports/TEST-*.xml 2>/dev/null | wc -l)
    if [ $test_files -gt 0 ]; then
        total_tests=0
        total_failures=0
        total_errors=0

        for file in target/surefire-reports/TEST-*.xml; do
            if [ -f "$file" ]; then
                tests=$(grep -o 'tests="[0-9]*"' "$file" | cut -d'"' -f2)
                failures=$(grep -o 'failures="[0-9]*"' "$file" | cut -d'"' -f2)
                errors=$(grep -o 'errors="[0-9]*"' "$file" | cut -d'"' -f2)

                total_tests=$((total_tests + tests))
                total_failures=$((total_failures + failures))
                total_errors=$((total_errors + errors))
            fi
        done

        echo "Total Tests: $total_tests"
        echo "Failures: $total_failures"
        echo "Errors: $total_errors"
        echo "Success Rate: $(( (total_tests - total_failures - total_errors) * 100 / total_tests ))%"
    fi
else
    echo "No test results found."
fi

echo ""
echo "=========================================="
echo "To view detailed coverage report, open:"
echo "target/site/jacoco/index.html"
echo "=========================================="
