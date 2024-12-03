fun main() {

    fun extractAndSumMul(input: String): Int {
        val regex = Regex("mul\\((\\d+),(\\d+)\\)")
        var sum = 0

        regex.findAll(input).forEach { matchResult ->
            val x = matchResult.groupValues[1].toInt()
            val y = matchResult.groupValues[2].toInt()
            sum += x * y
        }

        return sum
    }

    fun part1(reports: List<String>): Int {
        var sumResultsOfMultiplication = 0;
        for (report in reports) {
            sumResultsOfMultiplication += extractAndSumMul(report);
        }
        println("Sum of the results of the multiplications: $sumResultsOfMultiplication")
        return sumResultsOfMultiplication;
    }

    fun removeBetweenDontAndDo(input: String): String {
        var result = input
        var startIndex = result.indexOf("don't()")

        while (startIndex != -1) {
            val endIndex = result.indexOf("do()", startIndex)
            if (endIndex != -1) {
                result = result.removeRange(startIndex, endIndex + "do()".length)
            }
            startIndex = result.indexOf("don't()", startIndex + 1)
        }
        return result
    }

    fun part2(reports: List<String>): Int {
        var sumResultsOfMultiplication = 0;
        for (report in reports) {
            sumResultsOfMultiplication += extractAndSumMul(removeBetweenDontAndDo(report))
        }
        println("Sum of the results of the multiplications (with additional checks): $sumResultsOfMultiplication")
        return sumResultsOfMultiplication;
    }

    // Or read a large test input from the `src/Day03_test.txt` file:
    val testInput = readInput("Day03_test")
    println("\nTEST\n----\n")
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    // Read the input from the `src/Day03.txt` file.
    println("\nREAL\n----\n")
    val input = readInput("Day03")
    part1(input)
    part2(input)
}
