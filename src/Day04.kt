fun main() {

    val allSideDirections = listOf(
        Pair(0, 1),
        Pair(1, 0),
        Pair(1, 1),
        Pair(1, -1),
        Pair(0, -1),
        Pair(-1, 0),
        Pair(-1, -1),
        Pair(-1, 1)
    )

    val diagonalDirections = listOf(
        Pair(1, 1),
        Pair(1, -1),
        Pair(-1, -1),
        Pair(-1, 1)
    )


    fun countOccurrences(matrix: List<String>, directions: List<Pair<Int, Int>>, word: String): Int {
        val rows = matrix.size
        val cols = matrix[0].length
        val wordLength = word.length
        var count = 0



        fun isWordAt(row: Int, col: Int, direction: Pair<Int, Int>): Boolean {
            for (i in 0 until wordLength) {
                val newRow = row + i * direction.first
                val newCol = col + i * direction.second

                if (newRow !in 0 until rows || newCol !in 0 until cols || matrix[newRow][newCol] != word[i]) {
                    return false
                }
            }
            return true
        }

        for (row in 0 until rows) {
            for (col in 0 until cols) {
                for (direction in directions) {
                    if (isWordAt(row, col, direction)) {
                        count++
                    }
                }
            }
        }

        return count
    }

    fun part1(reports: List<String>): Int {
        val wordOccurrence: Int = countOccurrences(reports, allSideDirections, "XMAS")
        println("Number of times does XMAS appear: $wordOccurrence")
        return wordOccurrence
    }

    fun countMASinXShape(matrix: List<String>): Int {
        val rows = matrix.size
        val cols = matrix[0].length

        var count = 0

        for (i in 1 until rows - 1) {
            for (j in 1 until cols - 1) {
                if (matrix[i][j] == 'A' &&
                    matrix[i - 1][j - 1] == 'M' &&
                    matrix[i - 1][j + 1] == 'S' &&
                    matrix[i + 1][j - 1] == 'M' &&
                    matrix[i + 1][j + 1] == 'S') {
                    count++
                } else if (matrix[i][j] == 'A' &&
                    matrix[i - 1][j - 1] == 'M' &&
                    matrix[i - 1][j + 1] == 'M' &&
                    matrix[i + 1][j - 1] == 'S' &&
                    matrix[i + 1][j + 1] == 'S') {
                    count++
                } else if (matrix[i][j] == 'A' &&
                    matrix[i - 1][j - 1] == 'S' &&
                    matrix[i - 1][j + 1] == 'M' &&
                    matrix[i + 1][j - 1] == 'S' &&
                    matrix[i + 1][j + 1] == 'M') {
                    count++
                } else if (matrix[i][j] == 'A' &&
                    matrix[i - 1][j - 1] == 'S' &&
                    matrix[i - 1][j + 1] == 'S' &&
                    matrix[i + 1][j - 1] == 'M' &&
                    matrix[i + 1][j + 1] == 'M') {
                    count++
                }
            }
        }

        return count
    }

    fun part2(reports: List<String>): Int {
        val wordOccurrence: Int = countMASinXShape(reports)
        println("Number of times does MAS appear (X shape only): $wordOccurrence")
        return wordOccurrence
    }

    // Or read a large test input from the `src/Day04_test.txt` file:
    val testInput = readInput("Day04_test")
    println("\nTEST\n----\n")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    // Read the input from the `src/Day04.txt` file.
    println("\nREAL\n----\n")
    val input = readInput("Day04")
    part1(input)
    part2(input)
}
