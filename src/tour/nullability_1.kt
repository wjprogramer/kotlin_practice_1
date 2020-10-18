package tour

import kotlin.system.exitProcess

// Elvis operator `?:`
fun nullability11() {
    val a: Int? = 3
    val b: Int? = 2
    val c: Int? = a ?: b // 3 ?: 2

    println("c = $c")

    val d = null ?: b // null ?: 2
    println("d = $d")
}

// Safe cast `as?`
fun nullability12() {
    val a: Any? = null

    (a as? Float).toString()
}

fun main() {
    while(true) {
        println("Type number:")
        val option = readLine()

        when(option?.toInt()) {
            1 -> nullability11()
            2 -> nullability12()
            else -> exitProcess(1)
        }

        println("======")
    }
}