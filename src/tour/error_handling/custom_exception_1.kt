package tour.error_handling

import java.lang.Exception
import java.lang.IllegalStateException
import kotlin.system.exitProcess

private fun handleException() {
    var swordJuggling: Int? = null
    val isJugglingProficient = (1..3).shuffled().last() == 3
    if (isJugglingProficient) {
        swordJuggling = 2
    }

    try {
        proficiencyCheck(swordJuggling)
        swordJuggling = swordJuggling!!.plus(1)
    } catch (e: Exception) {
        println(e)
    }

    println("You juggle $swordJuggling swords!")
}

private fun proficiencyCheck(swordsJuggling: Int?) {
    swordsJuggling ?: throw UnskilledSwordJugglerException()
}

private class UnskilledSwordJugglerException() :
    IllegalStateException("Player cannot juggle swords")

fun main() {
    while(true) {
        println("Type number:")
        val option = readLine()

        when(option?.toInt()) {
            else -> exitProcess(1)
        }

        println("======")
    }
}