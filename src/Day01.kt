import kotlin.math.abs

fun main() {
    fun splitToTwoLists(input: List<String>): Pair<ArrayList<Int>, ArrayList<Int>> {
        val leftList = arrayListOf<Int>()
        val rightList = arrayListOf<Int>()
        input.forEach { line ->
            val numbers = line.trim().split("\\s+".toRegex())
            if (numbers.size == 2) {
                leftList.add(numbers[0].toInt())
                rightList.add(numbers[1].toInt())
            }
        }
        return Pair(leftList, rightList)
    }

    fun part1(input: List<String>): Int {

        val (leftList, rightList) = splitToTwoLists(input)
        leftList.sort()
        rightList.sort()
        println("Sorted Left List: $leftList")
        println("Sorted Right List: $rightList")

        var totalDistance: Int = 0;
        for (i in 0..leftList.indices.last) {
            totalDistance += abs(leftList[i] - rightList[i]);
        }

        println("Total distance between left and right lists: $totalDistance")
        return totalDistance
    }

    fun part2(input: List<String>): Int {
        val (leftList, rightList) = splitToTwoLists(input)

        var similarityScore: Int = 0;
        for (i in 0..leftList.indices.last) {
            val count = rightList.count { it == leftList[i] }
            similarityScore += (leftList[i] * count);
        }

        println("Similarity score: $similarityScore")
        return similarityScore
    }
    
    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    println("\nTEST\n----\n")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    println("\nREAL\n----\n")
    val input = readInput("Day01")
    part1(input)
    part2(input)
}
