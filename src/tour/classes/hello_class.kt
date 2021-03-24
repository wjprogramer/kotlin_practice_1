package tour.classes

import java.io.File
import kotlin.system.exitProcess

private fun helloClass() {
    val player1 = Player("jay")
    player1.castFireball()
    printPlayerStatus(player1)
}

private class Player(_name: String,
                     var healthPoints: Int = 100,
                     val isBlessed: Boolean,
                     private val isImmortal: Boolean) {

    /**
     * filed 關鍵字自動指向 kotlin 管理的「支援欄位」（backing field）
     */
    var name: String = _name
        get() = field.capitalize()
        private set(value) {
            field = value.trim()
        }

    val hometown = selectHometown()

    /**
     * 設定變數值或檢查有效性
     */
    init {
        require(healthPoints > 0) { "healthPoints must be greater than zero." }
        require(name.isNotBlank()) { "Player must have a name." }
    }

    constructor(name: String): this(name,
        isBlessed = true,
        isImmortal = false) {
        if (name.toLowerCase() == "kar") {
            healthPoints = 40
        }
    }

    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        return if (auraVisible) "GREEN" else "NONE"
    }

    fun formatHealthStatus() =
        when (healthPoints) {
            100 -> "is in excellent condition!"
            in 90..99 -> "has a few scratches."
            in 75..89 -> if (isBlessed) {
                "has some minor wounds, but is healing quite quitely"
            } else {
                "has some minor wounds."
            }
            in 15..74 -> "looks pretty hurt."
            else -> "is in awful condition!"
        }

    fun castFireball(numFireballs: Int = 2) =
        println("A glass of Fireball springs into existence. (x$numFireballs)")

    private fun selectHometown() = File("data/towns.txt")
        .readText()
        .split("\n")
        .shuffled()
        .first()

}

private fun printPlayerStatus(player: Player) {
    println("(Aura: ${player.auraColor()}) " +
            "(Blessed: ${if (player.isBlessed) "Yes" else "No"})")
    println("${player.name} ${player.formatHealthStatus()}")
}

private class Dice() {
    /**
     * 「計算屬性」
     *    - 在定義屬性時，kotlin 都會產生一個 field 屬性封裝的值
     *      但「計算屬性」為特例，透過覆寫 get 和（或） set 運算元定義
     *      不需要 field，在這種情況不會產生 field
     *    - 不會有初始值或預設值
     */
    // 這邊只是範例，這種寫法並不好，
    // val 代表不可變，但顯然，現在每次讀取，值都會亂數產生，以「不可變」形容並不恰當
    val rolledValue
       get() = (1..6).shuffled().first()
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()

        when(option?.toIntOrNull()) {
            1 -> helloClass()
            else -> exitProcess(1)
        }

        println("======")
    }
}