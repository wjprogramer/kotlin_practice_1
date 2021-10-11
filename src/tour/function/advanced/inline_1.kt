package tour.function.advanced

import tour.enums.*
import kotlin.system.exitProcess

fun main() {
    while(true) {
        println("======")
        print("Type number:")

        val option = readLine()

        when(option?.toInt()) {
            1 -> enum11()
            else -> exitProcess(1)
        }

        println("======")
    }
}