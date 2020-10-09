package tour

import java.lang.StringBuilder
import kotlin.system.exitProcess

fun string1() {
    val s = "Hello World"
    val sb = StringBuilder(s)

    sb[1] = 'x'

    println(sb.toString())

    sb.clear()

    sb.append('K')
    sb.append('o')
    sb.append('t')

    println(sb.toString())

    sb.reverse()
}

fun string2() {
    val s = "Hello"

    for (c in s) {
        print(c)
    }

    println()
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> string1()
            2 -> string2()
            else -> exitProcess(1)
        }

        println("======")
    }
}