import kotlin.math.absoluteValue

fun main() {

    fun stringToIntList(report: String): List<Int> {
        return report.trim().split("\\s+".toRegex()).map { it.toInt() }
    }

    
    fun isSafe(levels: List<Int>): Boolean {
        val allLevelsAreIncreasing = levels.zipWithNext().all { (a, b) -> b > a }
        val allLevelsAreDecreasing = levels.zipWithNext().all { (a, b) -> b < a }

        val isDiffValid = levels.zipWithNext().all { (a, b) -> (b - a).absoluteValue in 1..3 }

        return (allLevelsAreIncreasing || allLevelsAreDecreasing) && isDiffValid
    }

    fun canItBeSafeIfWeRemoveOneReport(levels: List<Int>): Boolean {
        if (isSafe(levels)) {
            return true
        }

        //Checking if the report can be safe if we remove a level iteratively
        for (i in levels.indices) {
            val modifiedList = levels.toMutableList().apply { removeAt(i) }
            if (isSafe(modifiedList)) {
                return true
            }
        }

        return false
    }


    fun part1(reports: List<String>): Int {
        var numberOfSafeReports: Int = 0;
        for (report in reports) {
            val levels = stringToIntList(report);
            if(isSafe(levels)) {
                numberOfSafeReports++
            }
        }
        println("Number of safe reports: $numberOfSafeReports")
        return numberOfSafeReports

    }

    fun part2(reports: List<String>): Int {
        var numberOfSafeReports: Int = 0;
        for (report in reports) {
            val levels = stringToIntList(report);
            if(canItBeSafeIfWeRemoveOneReport(levels)) {
                numberOfSafeReports++
            }
        }
        println("Number of safe reports: $numberOfSafeReports")
        return numberOfSafeReports
    }

    // Or read a large test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day02_test")
    println("\nTEST\n----\n")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day02.txt` file.
    println("\nREAL\n----\n")
    val input = readInput("Day02")
    part1(input)
    part2(input)
}
