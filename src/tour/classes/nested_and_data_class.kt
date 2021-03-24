package tour.classes

object Game {
    /**
     * Nested Class
     */
    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1) { "" }

        fun processCommand() = when (command.toLowerCase()) {
            else -> commandNotFound()
        }

        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }

    fun play() {
        while (true) {
            println(GameInput(readLine()).processCommand())
        }
    }
}

/**
 * Data Class
 */
data class Coordinate(val x: Int, val y: Int) { // toString 時，會顯示 x,y，而 isInBounds 不會
    val isInBounds = x >= 0 && y >= 0

    override fun equals(other: Any?): Boolean {
        if (other is Coordinate) {
            return x == other.x && y == other.y
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

class Player1 {
    var currentPosition = Coordinate(0, 0)
}

fun main() {

    println("data class 提供 copy")
    val c = Coordinate(0, 0)
    val c2 = c.copy()

    println("data class 的解構宣告")
    // kotlin 解構宣告後台實作：宣告 component1, component2 等若干個元件函數，
    // 讓每個函數負責管理想返回的一個屬性資料
    //
    // 如果定義一個資料類別（data class），
    // 他會自動為所有位於主建構函數的屬性增加對應的元件函數
}
