package tour.advanced_generic_type

import kotlin.random.Random


// region Loot Box
/**
 * `T` for type
 * `R` for return
 */
class LootBox<T>(item: T) {
    var open = false
    private var loot: T = item

    fun fetch(): T? {
        return loot.takeIf { open }
    }

    fun <R> fetch(lootModFunction: (T) -> R): R? {
        return lootModFunction(loot).takeIf { open }
    }
}

/**
 * ## 泛型約束
 * Q: 直接使用 `<Loot>`，而不使用 `<T: loot>`？
 * A: 不能讀取實例的成員
 *
 * ## vararg
 * 變數只要以 vararg 修飾，他就是變數返回值不可分割的一部份
 */
class OnlyLootBox<T: Loot>(vararg item: T) {
    var open = false
    // 加入 vararg 後，需要 out!
    private var loot: Array<out T> = item

    operator fun get(index: Int): T? = loot[index].takeIf { open }

    fun fetch(item: Int): T? {
        return loot[item].takeIf { open }
    }

    fun <R> fetch(item: Int, lootModFunction: (T) -> R): R? {
        return lootModFunction(loot[item]).takeIf { open }
    }
}
// endregion

// region Loot
open class Loot(val value: Int)

class Fedora(val name: String, value: Int): Loot(value) {
    fun hello() = println("Hello Fedora")
}

class Coin(value: Int): Loot(value) {
    fun headOrTail() {
        val side = if (Random.nextBoolean()) "head" else "tail"
        println(side)
    }
}
// endregion

fun main() {
    val lootBoxOne = LootBox(Fedora("a generic-looking fedora", 15))
    val lootBoxTwo = LootBox(Coin(15))

    lootBoxOne.open = true
    lootBoxOne.fetch()?.run {
        println("You retrieve $name from the box!")
    }

    val coin = lootBoxOne.fetch {
        Coin(it.value * 3)
    }
    coin?.let { println(it.value) }

    // ----------------

    val lootBox = OnlyLootBox(
        Fedora("a generic-looking fedora", 15),
        Fedora("a dazzling magenta fedora", 25)
    )

    lootBox.open = true
    lootBox.fetch(1)?.run {
        println("You retrieve $name from the box!")
    }
    lootBox[1]?.let {
        println("You retrieve ${it.name} from the box!")
    }

    val coin2 = lootBox.fetch(0) {
        Coin(it.value * 3)
    }
    coin2?.let { println(it.value) }

}

