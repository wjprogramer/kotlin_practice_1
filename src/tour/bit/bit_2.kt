package tour.bit

import kotlin.math.pow
import kotlin.system.exitProcess

private fun bit1() {
    val a = 0b11111111_11111111_11111111_11111111

    println(a.toString(10))
    println(a.inv().toString(10))
    println((-(a.inv())).toString(10))
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine() ?: exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> bit1()
            else -> exitProcess(1)
        }

        println("======")

//        println(0b11010010_01101001_10010100_10010010)
    }
}