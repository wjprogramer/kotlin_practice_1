package tour

import kotlin.system.exitProcess

fun pair1() {
    val pairs = mutableListOf<Pair<Int, Int>>()

    val pair = Pair<Int, Int>(1, 2)
}

fun pair2() {
    var pair = Pair(1, 2)

    pair = pair.copy(second = pair.second + 1)

    println("(${pair.first}, ${pair.second})")
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> pair1()
            2 -> pair2()
            else -> exitProcess(1)
        }

        println("======")
    }
}