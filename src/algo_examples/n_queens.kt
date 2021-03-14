package tmp

var res = mutableListOf<MutableList<String>>()

/**
 * @param n 棋盤邊長
 */
fun solveNQueens(n: Int): List<List<String>> {
    // '.' 表示空，'Q' 表示皇后，初始化空棋盤。
    val board = MutableList(n) {
        List(n) { '.' }.joinToString("")
    }

    backtrack(board, 0)
    return res
}

// 路徑：board 中小於 row 的那些行都已經成功放置了皇后
// 選擇列表：第 row 行的所有列都是放置皇后的選擇
// 結束條件：row 超過 board 的最後一行
fun backtrack(board: MutableList<String>, row: Int) {
    // 觸發結束條件
    if (row == board.size) {
        res.add(board.toMutableList())
        return
    }

    for (col in board[row].indices) {
        // 排除不合法選擇
        if (!isValid(board, row, col))
            continue
        // 做選擇
        board[row] = board[row].substring(0 until col) + 'Q' + board[row].substring(col + 1)
        // 進入下一行決策
        backtrack(board, row + 1);
        // 撤銷選擇
        board[row] = board[row].substring(0 until col) + '.' + board[row].substring(col + 1)
    }
}

/* 是否可以在 board[row][col] 放置皇后？ */
fun isValid(board: List<String>, row: Int, col: Int): Boolean {
    var ri = 0
    var ci = 0

    // 檢查列是否有皇后互相衝突
    for (i in board.indices) {
        if (board[i][col] == 'Q')
            return false
    }

    // 檢查右上方是否有皇后互相衝突
    ri = row - 1
    ci = col + 1
    while(ri >= 0 && ci < board[0].length) {
        if (board[ri][ci] == 'Q') {
            return false
        }
        ri--; ci++
    }

    // 檢查左上方是否有皇后互相衝突
    ri = row - 1
    ci = col - 1
    while(ri >= 0 && ci in board[0].indices) {
        if (board[ri][ci] == 'Q') {
            return false
        }
        ri--; ci--
    }

    return true
}

fun main() {
    val number = 8
    solveNQueens(number)

    res.forEachIndexed { index, result ->
        println("Result $index:")
        result.forEach { row ->
            println(row.split("").joinToString(" "))
        }
        println()
    }
}