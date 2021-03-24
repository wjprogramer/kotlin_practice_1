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

// 3. 伴生物件
// - 一個 class 只能有一個伴生物件
// - 如果想將某個物件的初始化關聯至一個類別實例，可考慮「伴生物件」
class WorldMap {
    companion object {
        private const val MAPS_FILEPATH = "nyethack.maps"

        fun load() = MAPS_FILEPATH
    }
}

private open class TownSquare {
    open fun load() = ""
}

fun main() {
    Game.play()
    WorldMap.load()
}

