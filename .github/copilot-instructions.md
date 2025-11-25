# Copilot Instructions for advent-2024

## Repository Overview

This is an **Advent of Code 2024** solutions repository written in **Kotlin**. It contains puzzle solutions for the annual Advent of Code programming challenges. The project uses the JetBrains [Advent of Code Kotlin Template](https://github.com/kotlin-hands-on/advent-of-code-kotlin-template).

**Project Type:** Kotlin JVM application  
**Language:** Kotlin 2.1.0  
**Build Tool:** Gradle 8.11.1 (Kotlin DSL)  
**Runtime:** JDK 17+

---

## Build and Validation Commands

### Build the project
```bash
./gradlew build
```
This compiles all Kotlin source files. Build time is approximately 2-5 seconds for incremental builds, 30-60 seconds for the first build (downloads dependencies).

### Clean and rebuild
```bash
./gradlew clean build
```

### Compile Kotlin only (faster validation)
```bash
./gradlew compileKotlin
```
Use this for quick syntax validation without full build.

### Run a specific day's solution
```bash
kotlin -classpath build/classes/kotlin/main Day01Kt
```
Replace `Day01Kt` with the appropriate day (e.g., `Day02Kt`, `Day03Kt`).

**Important:** Solutions require input files in `src/` directory (e.g., `src/Day01.txt` and `src/Day01_test.txt`). These files are gitignored and must be created manually with puzzle inputs from adventofcode.com.

---

## Project Structure

```
advent-2024/
├── build.gradle.kts          # Gradle build configuration (Kotlin 2.1.0, Gradle 8.11.1)
├── settings.gradle.kts       # Project settings and repository configuration
├── gradlew, gradlew.bat      # Gradle wrapper scripts
├── gradle/wrapper/           # Gradle wrapper JAR and properties
├── src/                      # Source code directory
│   ├── Utils.kt              # Shared utilities (readInput, md5, println extension)
│   ├── Day01.kt              # Day 1 solution
│   ├── Day02.kt              # Day 2 solution
│   ├── ...                   # Additional day solutions (Day03-Day07)
│   └── *.txt                 # Input files (gitignored)
├── .idea/                    # IntelliJ IDEA configuration
│   └── fileTemplates/        # Template for new day solutions
├── .gitignore                # Ignores build/, .gradle/, .kotlin/, src/**/*.txt
└── README.md                 # Project documentation
```

---

## Key Files

### src/Utils.kt
Contains shared utility functions used by all solutions:
- `readInput(name: String)` - Reads input from `src/{name}.txt`
- `String.md5()` - MD5 hash extension function
- `Any?.println()` - Shorthand for printing output

### Solution File Pattern (Day##.kt)
Each solution file follows this structure (uses zero-padded day numbers like Day01, Day02):
```kotlin
fun main() {
    fun part1(input: List<String>): Int { /* solution */ }
    fun part2(input: List<String>): Int { /* solution */ }

    // Test with sample input
    val testInput = readInput("Day01_test")
    check(part1(testInput) == expectedValue)
    check(part2(testInput) == expectedValue)

    // Run with real input
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
```

### .idea/fileTemplates/Advent of Code.kt
IntelliJ template for creating new day solutions. Use this pattern when adding new days.

---

## Important Notes

1. **No CI/CD workflows**: This repository has no GitHub Actions or automated pipelines.

2. **No test framework**: Solutions use inline `check()` assertions instead of a test framework. Tests run when executing the main function.

3. **Input files are gitignored**: Files matching `src/**/*.txt` are not committed. Each solution expects:
   - `src/Day##.txt` - Real puzzle input (e.g., Day01.txt, Day02.txt)
   - `src/Day##_test.txt` - Sample/test input from puzzle description

4. **Flat source structure**: All source files are in `src/` directly (no package hierarchy).

5. **No linting tools configured**: No ktlint, detekt, or EditorConfig present.

---

## When Making Changes

### Adding a new day solution:
1. Create `src/Day##.kt` following the existing pattern (e.g., Day08.kt, Day09.kt)
2. Create input files `src/Day##.txt` and `src/Day##_test.txt`
3. Run `./gradlew compileKotlin` to verify compilation
4. Run `kotlin -classpath build/classes/kotlin/main Day##Kt` to test (e.g., Day08Kt)

### Modifying Utils.kt:
- Changes affect all solutions
- Run `./gradlew compileKotlin` to ensure all files still compile

### Build verification:
Always run `./gradlew build` before committing to verify:
- Kotlin compilation succeeds
- No syntax errors exist

---

## Troubleshooting

| Issue | Solution |
|-------|----------|
| `NoSuchFileException: src/Day##.txt` | Create required input files in `src/` |
| Gradle download slow | First build downloads ~30MB of dependencies |
| `Task 'run' not found` | Use `kotlin -classpath build/classes/kotlin/main Day##Kt` instead |

---

## Trust These Instructions

The information above has been validated by running the actual commands. If something doesn't work as documented, verify:
1. You're in the repository root directory
2. Gradle wrapper is present (`./gradlew` exists)
3. JDK 17+ is available

Only perform additional searches if these instructions are incomplete or found to be incorrect.
