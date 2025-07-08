# GitHub Actions CI/CD Pipeline

This repository includes a comprehensive GitHub Actions pipeline that automatically runs tests and code quality checks on every push and pull request.

## Pipeline Overview

The CI pipeline consists of two main jobs:

### 1. Test Job
- **Multi-version testing**: Runs tests on Java 11, 17, and 21
- **Automatic triggers**: Runs on pushes and PRs to `main` and `develop` branches
- **Test reporting**: Generates detailed test reports with JUnit XML format
- **Artifact preservation**: Saves test results and reports as artifacts

### 2. Build Job
- **Code coverage**: Generates coverage reports with JaCoCo
- **Build artifacts**: Creates and uploads JAR files

## Local Development

### Running Tests Locally
```bash
# Run all tests
mvn clean test

# Run tests with coverage report
mvn clean test jacoco:report

# Run specific test class
mvn test -Dtest=SimplyLinkedListTest

# Generate test report
mvn surefire-report:report
```

### Code Quality Checks
```bash
# Generate code coverage report
mvn clean test jacoco:report

# View coverage report
open target/site/jacoco/index.html
```

## Pipeline Features

### Test Matrix
The pipeline tests against multiple Java versions to ensure compatibility:
- Java 11 (LTS)
- Java 17 (LTS)
- Java 21 (Latest LTS)

### Caching
Maven dependencies are cached to speed up builds and reduce GitHub Actions usage.

### Artifact Management
- Test results are uploaded as artifacts for failed builds
- JAR files are created and stored for successful builds
- Reports are preserved for 30 days

### Status Badges
Add these badges to your main README.md:

```markdown
[![Java CI](https://github.com/YOUR_USERNAME/java-data-structures/actions/workflows/ci.yml/badge.svg)](https://github.com/YOUR_USERNAME/java-data-structures/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/YOUR_USERNAME/java-data-structures/branch/main/graph/badge.svg)](https://codecov.io/gh/YOUR_USERNAME/java-data-structures)
```

## Test Coverage

Current test coverage includes:
- **68 total tests** across all classes
- **100% method coverage** for core functionality
- **Comprehensive edge case testing**

### Test Breakdown
- `TowerOfHanoiTest`: 7 tests
- `IsArraySortedTest`: 20 tests  
- `BinaryStringsTest`: 13 tests
- `SimplyLinkedListTest`: 28 tests

## Contributing

When contributing to this project:
1. All tests must pass in the CI pipeline
2. Code coverage should not decrease
3. Follow the existing code style and patterns

The pipeline will automatically run on your pull requests and provide feedback on test results and code quality.
