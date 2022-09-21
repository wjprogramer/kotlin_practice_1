package tour.variables.types.collection

import kotlin.system.exitProcess

private val myCustomComparator =  Comparator<CustomObject> { a, b ->
    when {
        (a == null && b == null) -> 0
        (a == null) -> -1
        else -> 1
    }
}

private fun demo1() {
    val customObjects = listOf(CustomObject(), CustomObject())
    customObjects.sortedWith(myCustomComparator)
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine() ?: exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> demo1()
            else -> exitProcess(1)
        }

        println("======")
    }
}

private class CustomObject {}