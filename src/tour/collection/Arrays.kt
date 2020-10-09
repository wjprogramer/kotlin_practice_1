package tour.collection

import java.util.*
import kotlin.system.exitProcess

fun arrays1() {
    val s = intArrayOf(1, 2, 3, 4)
    Arrays.fill(s, 0)
    println(s.joinToString())
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> arrays1()
            else -> exitProcess(1)
        }

        println("======")
    }
}