package tour.collection

import kotlin.system.exitProcess

fun list1() {
    val l1 = listOf(listOf("London","New York"), listOf("New York","Lima"), listOf("Lima","Sao Paulo"))
    val l2 = l1.flatten()

    println(l2.joinToString())

    val l3 = ArrayList(l2)
    l3.contains("")
    l3.last()
}

fun list2() {
    val l1 = arrayListOf<String>("a", "a", "a")
    l1.removeAt(0)
    l1.remove("a")

    val l2 = arrayListOf<String>("a", "c", "a", "b")
    l2.sort()
    println(l2.joinToString())

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