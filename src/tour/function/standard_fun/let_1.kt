package tour.function.standard_fun

import kotlin.system.exitProcess

/** region Data */
class TreasureBox {
    private val password = "password"
    private val treasure = "You've got a Windows install USB"

    fun open(key: String?): String {
        return key?.let {
            // `it` is the key String.
            // `this` is TreasureBox.

            var treasure = "error"
            if (it == password) {
                treasure = this.treasure
            }
            treasure     // <-- pass value back
        } ?: "error"
    }

    // 將 it 名稱改為 topSecretPassword
    fun open2(key: String?): String {
        return key?.let { topSecretPassword ->
            // `it` is the key String.
            // `this` is TreasureBox.

            var treasure = "error"
            if (topSecretPassword == password) {
                treasure = this.treasure
            }
            treasure     // <-- pass value back
        } ?: "error"
    }
}
/** endregion */

/** region Demo */
fun let11() {
    val treasureBox = TreasureBox()

    println("Open the box , and ${treasureBox.open(null)}")
    println("Open the box , and ${treasureBox.open2("admin")}")
    println("Open the box , and ${treasureBox.open("password")}")
}
/** endregion */

fun main() {
    while(true) {
        print("Type number: ")

        val option = readLine() ?: exitProcess(1)

        println("======")

        when(option.toInt()) {
            1 -> let11()
            else -> exitProcess(1)
        }

        println("======")
    }
}