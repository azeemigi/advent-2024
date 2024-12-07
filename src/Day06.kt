import kotlin.math.abs

fun main() {

    val directions = listOf(
        Pair(-1, 0), // ^ Up
        Pair(0, 1),  // > Right
        Pair(1, 0),  // v Down
        Pair(0, -1)  // < Left
    )

    fun guardPatrol(map: List<String>): Int {
        val grid = map.map { it.toCharArray() }.toMutableList()
        val rows = grid.size
        val cols = grid[0].size

        var guardRow = -1
        var guardCol = -1
        var direction = 0

        outerLoop@ for (r in 0 until rows) {
            for (c in 0 until cols) {
                when (grid[r][c]) {
                    '^' -> {
                        guardRow = r
                        guardCol = c
                        direction = 0 // ^ Up
                        break@outerLoop
                    }
                    '>' -> {
                        guardRow = r
                        guardCol = c
                        direction = 1 // > Right
                        break@outerLoop
                    }
                    'v' -> {
                        guardRow = r
                        guardCol = c
                        direction = 2 // v Down
                        break@outerLoop
                    }
                    '<' -> {
                        guardRow = r
                        guardCol = c
                        direction = 3 // < Left
                        break@outerLoop
                    }
                }
            }
        }

        println("initial positions: [$direction] $guardRow and $guardCol")

        // Marking the initial position as visited
        grid[guardRow][guardCol] = 'X'

        while (true) {
            val nextRow = guardRow + directions[direction].first
            val nextCol = guardCol + directions[direction].second

            // This condition is to check if the the next position of hte guard is out of the grid. If so, break...
            if (nextRow !in 0 until rows || nextCol !in 0 until cols) break

            if (grid[nextRow][nextCol] == '#') {
                // Obstacle so let's turn the guard
                direction = (direction + 1) % 4
            } else {
                guardRow = nextRow
                guardCol = nextCol
                if (grid[guardRow][guardCol] != 'X') {
                    grid[guardRow][guardCol] = 'X'
                }
            }
        }

        return grid.sumOf { row -> row.count { it == 'X' } }
    }

    fun findObstructionPositions(map: List<String>): Int {
        fun simulateWithObstruction(grid: List<CharArray>, obstructionRow: Int, obstructionCol: Int): Boolean {
            val rows = grid.size
            val cols = grid[0].size

            val gridCopy = grid.map { it.copyOf() }
            gridCopy[obstructionRow][obstructionCol] = '#'

            var guardRow = -1
            var guardCol = -1
            var direction = 0

            outerLoop@ for (r in 0 until rows) {
                for (c in 0 until cols) {
                    when (gridCopy[r][c]) {
                        '^' -> {
                            guardRow = r
                            guardCol = c
                            direction = 0
                            break@outerLoop
                        }
                        '>' -> {
                            guardRow = r
                            guardCol = c
                            direction = 1
                            break@outerLoop
                        }
                        'v' -> {
                            guardRow = r
                            guardCol = c
                            direction = 2
                            break@outerLoop
                        }
                        '<' -> {
                            guardRow = r
                            guardCol = c
                            direction = 3
                            break@outerLoop
                        }
                    }
                }
            }

            val visitedStates = mutableSetOf<Triple<Int, Int, Int>>()

            while (true) {
                val state = Triple(guardRow, guardCol, direction)
                if (!visitedStates.add(state)) {
                    return true
                }

                val nextRow = guardRow + directions[direction].first
                val nextCol = guardCol + directions[direction].second

                if (nextRow !in 0 until rows || nextCol !in 0 until cols) break // Guard leaves the map

                if (gridCopy[nextRow][nextCol] == '#') {
                    direction = (direction + 1) % 4
                } else {
                    guardRow = nextRow
                    guardCol = nextCol
                }
            }
            return false
        }

        val grid = map.map { it.toCharArray() }
        val rows = grid.size
        val cols = grid[0].size
        var count = 0

        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (grid[r][c] == '.') {
                    if (simulateWithObstruction(grid, r, c)) {
                        count++
                    }
                }
            }
        }
        return count
    }


    fun part1(input: List<String>): Int {
        val result = guardPatrol(input)
        println("Distinct positions visited: $result")
        return result
    }

    fun part2(input: List<String>): Int {
        val obstructionCount = findObstructionPositions(input)
        println("Number of obstruction positions causing a loop: $obstructionCount")
        return obstructionCount
    }


    // Or read a large test input from the `src/Day06_test.txt` file:
    val testInput = readInput("Day06_test")
    println("\nTEST\n----\n")
    check(part1(testInput) == 41)
    check(part2(testInput) == 6)

    // Read the input from the `src/Day06.txt` file.
    println("\nREAL\n----\n")
    val input = readInput("Day06")
    part1(input)
    part2(input)
}
