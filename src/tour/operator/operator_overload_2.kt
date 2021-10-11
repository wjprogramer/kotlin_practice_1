package tour.operator

fun main() {

    data class Coordinate(val x: Int, val y: Int) { // toString 時，會顯示 x,y，而 isInBounds 不會
        val isInBounds = x >= 0 && y >= 0

        operator fun plus(other: Coordinate) = Coordinate(x + other.x, y + other.y)

        /**
         * 其他「重載運算子」：
         *
         * 運算子        函數名稱
         * --------     ----------
         * +=           plusAssign
         * ==           equals
         * >            compareTo
         * []           get
         * ..           rangeTo (建立一個 Range 物件)
         * in           contains (如果物件位於集合裡，返回 true)
         */
    }

    val c1 = Coordinate(1, 5)
    val c2 = Coordinate(12, 3)

    println(c1 + c2)

}