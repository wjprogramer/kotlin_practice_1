package tour.collection

import kotlin.system.exitProcess

fun map1() {
    val m1 = HashMap<String, Int>()

}

private fun baseMapDemo() {

    val m1 = mapOf(
        "Eli" to 123,
        "Foo" to 123
    )

    val m2 = mutableMapOf(
        Pair("a", 1),
        Pair("b", 2)
    )
    m2 += "a" to 2
    m2["a"]
    m2.put("c", 3)
    m2.getOrPut("c") { 2 }

    m2.getValue("a")
    m2.getOrElse("a") { "" }
    m2.getOrDefault("a", "")

    m2.forEach { (myKey, myValue) ->
        println("$myKey. $myValue")
    }
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