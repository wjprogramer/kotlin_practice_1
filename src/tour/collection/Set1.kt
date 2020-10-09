package tour.collection

import kotlin.system.exitProcess

fun set1() {
    val s = mutableSetOf<Int>()

    s.add(1)
    println(s.size)
    println(s)

    s.add(1)
    println(s.size)
    println(s)
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> set1()
            else -> exitProcess(1)
        }

        println("======")
    }
}