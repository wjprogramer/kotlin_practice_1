package algo.examples

const val SIZE = 8

/**
 * @param size 棋盤大小
 */
class EightQueensPuzzle1(private val size: Int) {
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

    private fun location(row: Int): Int {
        // 搜尋特定row的col欄位
        val start = queues[row] + 1 // 也許是回溯,所以start不一定是0
        for (col in start until size) {
            if (isOk(row, col)) {
                return col // 暫時可以在(row,col)放置皇后
            }
        }
        return -1 // 沒有適合位置所以回傳 -1
    }

    fun solve(): Boolean {
        // 從特定row列開始找尋皇后的位置
        var row = 0
        while(row in 0..7) {
            val col = location(row)
            if (col < 0) {          // 如果回傳是 -1, 必須回溯前一列
                queues[row] = -1
                row -= 1            // 設定row少1, 可以回溯前一列
            } else {
                queues[row] = col   // 第row列皇后位置是col
                row += 1            // 往下一列
            }
        }

        return row != -1 // true: 找到解答
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
    val queensPuzzle = EightQueensPuzzle1(SIZE)
    println(queensPuzzle.solve())
    queensPuzzle.showBoard()
}