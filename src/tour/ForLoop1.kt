package tour

import kotlin.system.exitProcess

fun for1() {
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
}

fun for2() {
    val items = listOf("apple", "banana", "kiwifruit")
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}

fun for3() {
    for (i in 5 until 0) {
        print(i) // empty
    }
    println()

    for (i in 0 until 5) {
        print(i)
    }
    println()

}

fun main() {
    while(true) {
        println("Type number:")
        val option = readLine()

        when(option?.toInt()) {
            1 -> for1()
            2 -> for2()
            3 -> for3()
            else -> exitProcess(1)
        }

        println("======")
    }
}