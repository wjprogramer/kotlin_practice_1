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

    for (i in 0 until 5 step 2) {
        print(i)
    }
    println()

    for (i in 0.rangeTo(3)) {
        print(i)
    }
    println()

    /// -----------------
    10.downTo(0)

    for (i in 9 downTo 2) {
        print("")
    }

    f1@for (i in 0..8) {
        f2@for (j in 0..1) {
           continue@f1
        }
    }

    fun localFunction() {
        fun localFunction2() {

        }
        localFunction2()
    }
    localFunction()
}

fun main() {
    while(true) {
        println(message = "Type number:")
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