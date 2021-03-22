package tour.class_extends

/**
 * 加上 `open` 允許其他 class 繼承 `Room`
 */
open class Room(val name: String) {
    /**
     * `protected`: 只有目前類別和子類別可見
     */
    protected open val dangerLevel = 5

    /**
     * 不加修飾子：public, final
     */
    fun description() = "Room: $name"

    /**
     * 允許所有子類別覆寫（除非子類別有在函式前加上 final）
     */
    open fun load() = "Nothing must to see here..."
}

class TownSquare: Room("Town Square") {
    override val dangerLevel = super.dangerLevel - 3
    private var bellSound = "GWONG" // 狀聲詞，沒拼錯

    /**
     * `final`: 不允許 `TownSquare` 的子類別覆寫 load
     */
    final override fun load() = "The villagers rally and cheer as you enter!\n${ringBell()}"

    private fun ringBell() = "Th bell tower announces your arrival. $bellSound"
}

fun main() {

    val townSquare: Room = TownSquare()
    println(getRoomType(townSquare))

    val room = Room("Hello")
    println(getRoomType(room))

}

private fun getRoomType(room: Room): String {
    return when(room) {
        is TownSquare -> room::class.simpleName.toString()
        is Room -> room::class.simpleName.toString()
        else -> "Not Room"
    }
}

private fun printIsSourceOfBlessings(any: Any) {
    val isSourceOfBlessing = if (any is Player) {
        any.isBlessed
    } else {
        (any as Room).name == "Fount of Blessings"
    }

    println("$any is source of blessings: $isSourceOfBlessing")
}

private class  Player {

    val isBlessed = false

}

