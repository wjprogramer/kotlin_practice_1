package tour

import kotlin.system.exitProcess

fun boolean11() {
    val a = true
    println(a.not())
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> boolean11()
            else -> exitProcess(1)
        }

        println("======")
    }
}