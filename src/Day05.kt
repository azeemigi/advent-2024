import kotlin.math.abs

fun main() {

    fun parseInput(input: List<String>): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val splitIndex = input.indexOf("")
        val orderingRules = input.subList(0, splitIndex)
            .map { it.split("|").let { (a, b) -> a.toInt() to b.toInt() } }
        val updates = input.subList(splitIndex + 1, input.size)
            .map { it.split(",").map { it.toInt() } }
        return orderingRules to updates
    }

    fun isCorrectOrder(update: List<Int>, rules: List<Pair<Int, Int>>): Boolean {
        val pageIndex = update.withIndex().associate { it.value to it.index }
        return rules.all { (a, b) ->
            if (a in update && b in update) {
                pageIndex[a]!! < pageIndex[b]!!
            } else {
                true
            }
        }
    }

    fun reorderUpdate(update: List<Int>, rules: List<Pair<Int, Int>>): List<Int> {
        val dependencies = mutableMapOf<Int, MutableList<Int>>()
        rules.forEach { (a, b) ->
            if (a in update && b in update) {
                dependencies.computeIfAbsent(b) { mutableListOf() }.add(a)
            }
        }
        return update.sortedWith { x, y ->
            when {
                dependencies[x]?.contains(y) == true -> 1
                dependencies[y]?.contains(x) == true -> -1
                else -> 0
            }
        }
    }

    fun part1(input: List<String>): Int {
        val (orderingRules, updates) = parseInput(input)
        val middleSum = updates.filter { isCorrectOrder(it, orderingRules) }
            .sumOf { it[it.size / 2] }
        println("Sum of middle page numbers: $middleSum")
        return middleSum
    }

    fun part2(input: List<String>): Int {
        val (orderingRules, updates) = parseInput(input)
        val middleSum = updates.filterNot { isCorrectOrder(it, orderingRules) }
            .map { reorderUpdate(it, orderingRules) }
            .sumOf { it[it.size / 2] }
        println("Sum of middle page numbers after correcting updates: $middleSum")
        return middleSum
    }



    // Or read a large test input from the `src/Day05_test.txt` file:
    val testInput = readInput("Day05_test")
    println("\nTEST\n----\n")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    // Read the input from the `src/Day05.txt` file.
    println("\nREAL\n----\n")
    val input = readInput("Day05")
    part1(input)
    part2(input)
}
