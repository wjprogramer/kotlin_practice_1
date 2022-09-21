package tour.variables.types.collection

import java.util.*
import kotlin.system.exitProcess

fun arrays1() {
    val s = intArrayOf(1, 2, 3, 4)
    Arrays.fill(s, 0)
    println(s.joinToString())
}

fun main() {

    println("int[] 在 Java 中為「基礎資料類型」，Array 在 kotlin 中是參照類型，但會被轉換成 Java 的基礎類型")

    println("若沒有要與 Java 互動，在 kotlin 開發中一律使用 List")

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