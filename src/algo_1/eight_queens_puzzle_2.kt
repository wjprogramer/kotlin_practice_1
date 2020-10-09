package algo_1

const val SIZE_2 = 8

/**
 * @param size 棋盤大小
 */
class EightQueensPuzzle2(private val size: Int) {
    private val queues = IntArray(size) { -1 }

    private fun isOk(row: Int, col: Int): Boolean {
        // 檢查是否可以放在此row, col位置
        for (i in 1..row) {
            if (queues[row - i] == col              // 檢查欄
                || queues[row - i] == col - i       // 檢查左上角斜線
                || queues[row - i] == col + i)  {   // 檢查右上角斜線
                return false                        // 傳回有衝突, 不可使用
            }
        }
        return true
    }

    fun solve(row: Int): Boolean {
        if (row == size) {
            return true
        }

        for (col in 0 until size) {
            queues[row] = col
            if (isOk(row, col) && solve(row + 1)) {
                return true
            }
        }

        return false
    }

    fun showBoard() {
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (queues[i] == j) {
                    print("Q  ")
                } else {
                    print("1  ")
                }
            }
            println()
        }
    }
}

fun main() {
    val queensPuzzle = EightQueensPuzzle2(SIZE_2)
    println(queensPuzzle.solve(0))
    queensPuzzle.showBoard()
}