package tour.variables.types.collection

import kotlin.system.exitProcess

fun list1() {
    val l1 = listOf(listOf("London","New York"), listOf("New York","Lima"), listOf("Lima","Sao Paulo"))
    val l2 = l1.flatten()

    println(l2.joinToString())

    val l3 = ArrayList(l2)
    l3.contains("")
    l3.last()

    l3.getOrElse(3) { "Else" }
    l3.getOrNull(4)
    l3.getOrNull(4) ?: 123
    l3.contains("")
    l3.containsAll(listOf("1", "2"))
}

fun list2() {
    val l1 = arrayListOf<String>("a", "a", "a")
    l1.removeAt(0)
    l1.remove("a")

    val l2 = arrayListOf<String>("a", "c", "a", "b")
    l2.sort()
    println(l2.joinToString())

}

private fun mutableListDemo() {
    val list = mutableListOf("1", "2")
    list.remove("1")
    list.add("3")
    list.add(0, "s")
    list += "a"
    list.removeIf { it.contains("a") }
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
            3 -> mutableListDemo()
            else -> exitProcess(1)
        }

        println("======")
    }
}