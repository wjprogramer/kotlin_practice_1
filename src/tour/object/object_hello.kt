package tour.`object`

/**
 * 使用 object 關鍵字有三種方式：
 *
 * 1. 物件宣告 object declaration
 * 2. 物件運算式 object expression
 * 3. 伴生物件 companion object
 *
 */

// 1. 物件宣告
object Game {
    init {
        println("Welcome, adventurer")
    }

    fun play() {
        while (true) {
            print("> Enter your command: ")
            println("Last command: ${readLine()}")

            // 2. 物件運算式
            // 用完即丟的類別、匿名類別
            // 遵守 object 關鍵字的一個規則: 一旦產生實體，該匿名類別只能存在唯一一個實例
            val abandonedTownSquare = object : TownSquare() {
                override fun load() = "You anticipate applause, but no one is here..."
            }
        }
    }
}

fun main() {
    Game.play()
}

private open class TownSquare {
    open fun load() = ""
}

