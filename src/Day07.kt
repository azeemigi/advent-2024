
fun main() {

    fun generateOperatorCombinations(count: Int): List<List<String>> {
        if (count == 0) return listOf(emptyList())

        val combinations = mutableListOf<List<String>>()
        val operators = listOf("+", "*", "||")

        val smallerCombinations = generateOperatorCombinations(count - 1)
        for (operator in operators) {
            for (combination in smallerCombinations) {
                combinations.add(combination + operator)
            }
        }

        return combinations
    }

    fun calculateTotalCalibrationResult(input: List<String>, evaluateExpression: (List<Long>, List<String>) -> Long): Long {
        var totalCalibrationResult = 0L

        for (line in input) {
            if (line.isBlank()) continue

            val parts = line.split(":").map { it.trim() }

            if (parts.size != 2 || parts[0].isEmpty() || parts[1].isEmpty()) continue

            val targetValue = parts[0].toLong()
            val numbers = parts[1].split(" ").map { it.toLong() }

            val operatorCombinations = generateOperatorCombinations(numbers.size - 1)

            for (operators in operatorCombinations) {
                if (evaluateExpression(numbers, operators) == targetValue) {
                    totalCalibrationResult += targetValue
                    break
                }
            }
        }

        return totalCalibrationResult
    }

    fun calculateCalibrationResultPart1(input: List<String>): Long {
        fun evaluateExpression(numbers: List<Long>, operators: List<String>): Long {
            var result = numbers[0]
            for (i in operators.indices) {
                result = when (operators[i]) {
                    "+" -> result + numbers[i + 1]
                    "*" -> result * numbers[i + 1]
                    else -> result
                }
            }
            return result
        }
        return calculateTotalCalibrationResult(input, ::evaluateExpression)
    }

    fun calculateCalibrationResultPart2(input: List<String>): Long {
        fun concatNumbers(left: Long, right: Long): Long {
            return (left.toString() + right.toString()).toLong()
        }
        fun evaluateExpression(numbers: List<Long>, operators: List<String>): Long {
            var result = numbers[0]
            for (i in operators.indices) {
                result = when (operators[i]) {
                    "+" -> result + numbers[i + 1]
                    "*" -> result * numbers[i + 1]
                    "||" -> concatNumbers(result, numbers[i + 1])
                    else -> result
                }
            }
            return result
        }

        return calculateTotalCalibrationResult(input, ::evaluateExpression)
    }

    fun part1(input: List<String>): Long {
        val calibrationResult = calculateCalibrationResultPart1(input)
        println("Calibration result: $calibrationResult")
        return calibrationResult
    }

    fun part2(input: List<String>): Long {
        val calibrationResult = calculateCalibrationResultPart2(input)
        println("Calibration result: $calibrationResult")
        return calibrationResult
    }

    // Or read a large test input from the `src/Day07_test.txt` file:
    val testInput = readInput("Day07_test")
    println("\nTEST\n----\n")
    check(part1(testInput) == 3749L)
    check(part2(testInput) == 11387L)

    // Read the input from the `src/Day07.txt` file.
    println("\nREAL\n----\n")
    val input = readInput("Day07")
    part1(input)
    part2(input)
}
