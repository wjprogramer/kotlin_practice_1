package tour.number

import javax.print.attribute.IntegerSyntax
import kotlin.system.exitProcess

fun number11() {
    // ## Byte
    // Basic unit in the computer is bit, 8 bit makes up a byte.
    // -128 to 127
    val byte: Byte = 14

    // ## Short
    // 16 bits and it can contain values between -32768 to 32767
    val short: Short = 200

    // ## Int
    // contains 32 bits, it can have a value from -2power31 to 2power31 – 1
    val int: Int

    // ## Long
    // Contains 64 bits, it can have a value from -2power62 to 2power62 – 1.
    val long: Long = 1090L

    // ## Double
    // contains 64 bit and it can contain 64bit precision
    val double: Double = 33665.4988

    // ## Float
    // contains 32 bit with 32-bit precession
    val float1: Float = 1236.78f
    val float2: Float = 1236.78F
}

fun number12() {
    val a = -20

    println(+a) // +(-20) => -20
    println(a.unaryPlus()) // +(-20) => -20
    println(a.unaryMinus()) // -(-20) => 20
}

fun number13() {
    var a = 0

    a = a.dec()
    println(a)

    a = a.inc()
    println(a)
}

private fun byteCompute() {
    println(Integer.toBinaryString(42))

    println(42.shl(2)) // 按位左移
    println(42.shr(2))
    println(42.inv()) // 按位取反
    println(42.xor(33)) // 按位互斥
    println(42.and(10)) // 按位與
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> number11()
            2 -> number12()
            3 -> number13()
            4 -> byteCompute()
            else -> exitProcess(1)
        }

        println("======")
    }
}