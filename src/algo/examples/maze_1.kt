package algo.examples

class Maze1 {
    class Point(var x: Int, var y: Int)

    // 0: 走道
    // 1: 牆壁
    // 2: 經過的路徑
    // 3: 經過會造成無路可走的路徑

    val maze = arrayOf(
        intArrayOf(1, 1, 1, 1, 1, 1),
        intArrayOf(1, 0, 1, 0, 1, 1),
        intArrayOf(1, 0, 1, 0, 0, 1),
        intArrayOf(1, 0, 0, 0, 1, 1),
        intArrayOf(1, 0, 1, 0, 0, 1),
        intArrayOf(1, 1, 1, 1, 1, 1)
    )

    private val directions = arrayOf<(x: Int, y: Int) -> Point>(
        { x, y -> Point(x - 1, y) },    // 上
        { x, y -> Point(x + 1, y) },    // 下
        { x, y -> Point(x, y - 1) },    // 左
        { x, y -> Point(x, y + 1) }     // 右
    )

    /**
     * @param x 迷宮入口
     * @param y 迷宮入口
     * @param goalX 迷宮出口
     * @param goalY 迷宮出口
     */
    fun mazeSolve(x: Int, y: Int, goalX: Int, goalY: Int): Boolean {
        maze[x][y] = 2
        val stack = arrayListOf<Point>() // 建立路徑堆疊
        stack.add(Point(x, y))

        while (stack.size > 0) {
            val cur = stack.last() // 目前位置

            if (cur.x == goalX && cur.y == goalY) {
                return true
            }

            var foundPath = false
            for (dir in directions) {
                val next = dir(cur.x, cur.y)
                if (maze[next.x][next.y] == 0) {
                    foundPath = true
                    stack.add(next)
                    maze[next.x][next.y] = 2
                    break
                }
            }

            if (!foundPath) {
                maze[cur.x][cur.y] = 3          // 標記死路
                stack.removeAt(stack.lastIndex) // 回溯
            }
        }

        return false
    }
}

fun main() {
    val maze1 = Maze1()
    maze1.mazeSolve(1, 1, 4, 4)

    for (m in maze1.maze) {
        println(m.joinToString(", "))
    }
}