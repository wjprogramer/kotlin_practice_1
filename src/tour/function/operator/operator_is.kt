package tour.function.operator

import kotlin.system.exitProcess

fun is1() {
    val flag: Boolean? = false

    // check whether type is boolean
    println(flag is Boolean)
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> is1()
            else -> exitProcess(1)
        }

        println("======")
    }
}