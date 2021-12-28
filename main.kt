import kotlin.math.abs
import java.util.Scanner

fun initGrid(): MutableList<MutableList<Char>> {
    val cells = "         "
    val grid = mutableListOf(
        mutableListOf(cells[0], cells[1], cells[2]),
        mutableListOf(cells[3], cells[4], cells[5]),
        mutableListOf(cells[6], cells[7], cells[8])
    )
    return grid
}
fun showGrid(grid: MutableList<MutableList<Char>>) {
    println("---------")
    println("| ${grid[0][0]} ${grid[0][1]} ${grid[0][2]} |")
    println("| ${grid[1][0]} ${grid[1][1]} ${grid[1][2]} |")
    println("| ${grid[2][0]} ${grid[2][1]} ${grid[2][2]} |")
    println("---------")
}
fun countElements(grid: MutableList<MutableList<Char>>, s: Char): Int {
    return grid[0].count { it == s } + grid[1].count { it == s } + grid[2].count { it == s }
}
fun checkTheStage(grid: MutableList<MutableList<Char>>, countX: Int, countO: Int): String {
    var winX = false
    var winO = false

    if (countO + countX < 3) {
        return "Game not finished"
    } else if (abs(countO - countX) > 1) {
        return "Impossible"
    } else {
        if (grid[0][0] == grid[1][0] && grid[1][0] == grid[2][0] && grid[0][0] != '_') {
            if (grid[0][0] == 'X') {
                winX = true
            } else if (grid[0][0] == 'O') {
                winO = true
            }
        }
        if (grid[0][1] == grid[1][1] && grid[1][1] == grid[2][1] && grid[0][1] != '_') {
            if (grid[0][1] == 'X') {
                winX = true
            } else if (grid[0][1] == 'O') {
                winO = true
            }
        }
        if (grid[0][2] == grid[1][2] && grid[1][2] == grid[2][2] && grid[0][2] != '_') {
            if (grid[0][2] == 'X') {
                winX = true
            } else if (grid[0][2] == 'O') {
                winO = true
            }
        }
        if (grid[0][0] == grid[0][1] && grid[0][1] == grid[0][2] && grid[0][0] != '_') {
            if (grid[0][0] == 'X') {
                winX = true
            } else if (grid[0][0] == 'O') {
                winO = true
            }
        }
        if (grid[1][0] == grid[1][1] && grid[1][1] == grid[1][2] && grid[1][0] != '_') {
            if (grid[1][0] == 'X') {
                winX = true
            } else if (grid[1][0] == 'O') {
                winO = true
            }
        }
        if (grid[2][0] == grid[2][1] && grid[2][1] == grid[2][2] && grid[2][0] != '_') {
            if (grid[2][0] == 'X') {
                winX = true
            } else if (grid[2][0] == 'O') {
                winO = true
            }
        }
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[0][0] != '_') {
            if (grid[0][0] == 'X') {
                winX = true
            } else if (grid[0][0] == 'O') {
                winO = true
            }
        }
        if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[0][2] != '_') {
            if (grid[0][2] == 'X') {
                winX = true
            } else if (grid[0][2] == 'O') {
                winO = true
            }
        }

        if (winX && winO) {
            return "Impossible"
        } else if (winX && !winO){
            return "X wins"
        } else if (!winX && winO){
            return "O wins"
        } else if (countO + countX == 9) {
            return "Draw"
        } else if (countO + countX != 9) {
            return "Game not finished"
        }
    }
    return "-1"
}
fun makeMove(grid: MutableList<MutableList<Char>>, moveOrder: Int) {
    val sign = if (moveOrder % 2 == 0) 'X' else 'O'

    var x = Int.MIN_VALUE
    var y = Int.MIN_VALUE

    while (x == Int.MIN_VALUE || y == Int.MIN_VALUE) {
        val scanner = Scanner(System.`in`)
        print("Enter the coordinates: ")
        if (scanner.hasNextInt()) {
            var t = scanner.nextInt()

            if (t in 1..3) {
                x = t

                if (scanner.hasNextInt()) {
                    t = scanner.nextInt()
                    if (t in 1..3) {
                        y = t

                        x--
                        y--

                        if (grid[x][y] != ' ') {
                            println("This cell is occupied! Choose another one!")
                            x = Int.MIN_VALUE
                            y = Int.MIN_VALUE
                        } else {
                            grid[x][y] = sign
                        }
                    } else {
                        println("Coordinates should be from 1 to 3!")
                    }
                } else {
                    println("You should enter numbers!")
                }
            } else {
                println("Coordinates should be from 1 to 3!")
            }

        } else {
            println("You should enter numbers!")
        }
    }
}

fun main() {
    val grid = initGrid()
    val countX = countElements(grid, 'X')
    val countO = countElements(grid, 'O')
    var moveOrder = 0

    showGrid(grid)
    while (checkTheStage(grid, countElements(grid, 'X'), countElements(grid, 'O')) == "Game not finished") {
        makeMove(grid, moveOrder++)
        showGrid(grid)
        if (checkTheStage(grid, countElements(grid, 'X'), countElements(grid, 'O')) != "Game not finished") {
            println(checkTheStage(grid, countElements(grid, 'X'), countElements(grid, 'O')))
        }
    }
}