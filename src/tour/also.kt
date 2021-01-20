package tour

import kotlin.system.exitProcess

// 參考: swap，從不會到略懂 a = b.also{b = a}
// https://medium.com/@spff/swap-%E5%BE%9E%E4%B8%8D%E6%9C%83%E5%88%B0%E7%95%A5%E6%87%82-a-b-also-b-a-5bf6f7001cac

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