package tour

import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.system.exitProcess

fun number1() {
    abs(1)
    (1 - 2).absoluteValue

}

fun number2() {
    var i = 0

    if (i++ == 0) {
        println("$i == 0")
    } else {
        println("$i")
    }

}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> number1()
            2 -> number2()
            else -> exitProcess(1)
        }

        println("======")
    }
}