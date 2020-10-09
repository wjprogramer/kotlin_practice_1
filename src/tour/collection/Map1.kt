package tour.collection

import kotlin.system.exitProcess

fun map1() {
    val m1 = HashMap<String, Int>()

}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> list1()
            2 -> list2()
            else -> exitProcess(1)
        }

        println("======")
    }
}