package tour

import kotlin.system.exitProcess

fun char1() {
    println('a'.toInt()) // 97
    println('z' - 'a') // 25

    val ca = charArrayOf('a', 'b').joinToString("")
    println(ca) // ab

    val ca2 = charArrayOf('1', '2').joinToString("").toInt()
    println(ca2 * 2) // 24

    println('a'.toInt() - 96) // 1
    println('z'.toInt() - 96) // 26
    println(97.toChar()) // a
    println((10 + 96).toChar()) // j
    println(Integer.parseInt("1") * 2) // 2
    println('1'.toInt()) // 49
}


fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> char1()
            else -> exitProcess(1)
        }

        println("======")
    }
}