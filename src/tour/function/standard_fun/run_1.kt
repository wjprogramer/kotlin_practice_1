package tour.function.standard_fun

import models.Telephone
import kotlin.system.exitProcess

// 可以將 run 想像成一個獨立出來的 Scope，
// run 會把最後一行的東西回傳或是帶到下一個 chain
fun run1() {
    val whatsMyName = "Francis"
    run {
        // NAME_SHADOWED
        val whatsMyName = "Ajax"
        println("Call my name! $whatsMyName")
    }
    println("What's my name? $whatsMyName")
}

fun run2() {
    // ## 1.
    run {
        val telephone = Telephone()
        telephone.whoCallMe = "English"
        telephone    // <--  telephone 被帶到下一個 Chain
    }.callMe("Softest part of heart")    // <-- 這裡可以執行 `Telephone` Class 的方法

    // ## 2.
    val wowCall = run {
        val telephone = Telephone()
        telephone.fromWhere = "Sagittarius"
        telephone.whoCallMe = "Still Unknown"
        telephone  // <-- telephone 回傳，wowCall 型態成為 Telephone
    }
    println("WOW, This signal is from ${wowCall.fromWhere}")
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine() ?: exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> run1()
            else -> exitProcess(1)
        }

        println("======")
    }
}