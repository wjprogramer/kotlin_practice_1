package tour.interfaces

import kotlin.random.Random

/**
 * Creature.kt
 */
interface Fightable {
    var healthPoints: Int
    val diceCount: Int
    val diceSides: Int
    val damageRoll: Int
        // 「預設實作」：可在介面提供一些已經實作的
        get() = (0 until diceCount).map {
            Random.nextInt(diceSides) + 1
        }.sum()

    fun attack(opponent: Fightable): Int
}

fun main() {
    class Player(
        override var healthPoints: Int,
        override val diceCount: Int,
        override val diceSides: Int
    ) : Fightable {
        override fun attack(opponent: Fightable): Int {
            TODO("Not yet implemented")
        }
    }
}
