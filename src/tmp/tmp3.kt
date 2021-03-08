package tmp

var res = mutableListOf<MutableList<String>>()

/* 输入棋盘边长 n，返回所有合法的放置 */
fun solveNQueens(n: Int): List<List<String>> {
    // '.' 表示空，'Q' 表示皇后，初始化空棋盘。
    val board = MutableList(n) {
        "."
    }

    backtrack(board, 0);
    return res;
}

// 路径：board 中小于 row 的那些行都已经成功放置了皇后
// 选择列表：第 row 行的所有列都是放置皇后的选择
// 结束条件：row 超过 board 的最后一行
fun backtrack(board: MutableList<String>, row: Int) {
    // 触发结束条件
    if (row == board.size) {
        res.add(board);
        return
    }

    for (col in board[row].indices) {
        // 排除不合法选择
        if (!isValid(board, row, col))
            continue
        // 做选择
        board[row] = board[row].substring(0 until col) + 'Q' + board[row].substring(col + 2)
        // 进入下一行决策
        backtrack(board, row + 1);
        // 撤销选择
        board[row] = board[row].substring(0 until col) + '.' + board[row].substring(col + 2)
    }
}

/* 是否可以在 board[row][col] 放置皇后？ */
fun isValid(board: List<String>, row: Int, col: Int): Boolean {
    // 检查列是否有皇后互相冲突
    for (i in board.indices) {
        if (board[i][col] == 'Q')
            return false
    }
    // 检查右上方是否有皇后互相冲突
    for (i in (row-1) downTo 0) {
        for (j in (col+1)..(board.lastIndex)) {
            if (board[i][j] == 'Q') {
                return false
            }
        }
    }
    // 检查左上方是否有皇后互相冲突
    for (i in (row-1) downTo 0) {
        for (j in (col-1) downTo 0) {
            if (board[i][j] == 'Q') {
                return false
            }
        }
    }
    return true
}