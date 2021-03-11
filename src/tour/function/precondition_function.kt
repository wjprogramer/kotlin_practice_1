package tour.function

import java.lang.Exception
import kotlin.system.exitProcess

/**
 * 先決條件
 */

private fun preconditionFunction1() {
    try {
        var text = readLine()
        text = null
        checkNotNull(text) {
            "I am error log if text is null"
        }
    } catch (e: Exception) {
        println("Error: $e")
    }
}

fun main() {
    while(true) {
        println("Type number:")
        val option = readLine()

        when(option?.toInt()) {
            1 -> preconditionFunction1()
            else -> exitProcess(1)
        }

        println("======")
    }
}