package tour

import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.system.exitProcess

fun number1() {
    abs(1)
    (1 - 2).absoluteValue

}

fun number2() {
    var i = 0

    if (i++ == 0) {
        println("$i == 0")
    } else {
        println("$i")
    }

}

private fun showNumberTypes() {
    println("Byte: ${Byte.SIZE_BITS}, ${Byte.MIN_VALUE} ~ ${Byte.MAX_VALUE}")
    println("Short: ${Short.SIZE_BITS}, ${Short.MIN_VALUE} ~ ${Short.MAX_VALUE}")
    println("Int: ${Int.SIZE_BITS}, ${Int.MIN_VALUE} ~ ${Int.MAX_VALUE}")
    println("Long: ${Long.SIZE_BITS}, ${Long.MIN_VALUE} ~ ${Long.MAX_VALUE}")
    println("Float: 32, ${Float.MIN_VALUE} ~ ${Float.MAX_VALUE}")
    println("Double: 64, ${Double.MIN_VALUE} ~ ${Double.MAX_VALUE}")
}

private fun formatNumber() {
    println("%.2f".format(0.233336))  // 四捨五入
    println(5.4.roundToInt()) // 四捨五入
    println(5.5.roundToInt()) // 四捨五入
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> number1()
            2 -> number2()
            3 -> showNumberTypes()
            4 -> formatNumber()
            else -> exitProcess(1)
        }

        println("======")
    }
}