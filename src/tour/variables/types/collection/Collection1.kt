package tour.variables.types.collection

import kotlin.system.exitProcess

fun collection1() {
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }

    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }
}

fun collection2() {
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }
}

fun collection3() {
    val res = arrayListOf<Int>()

    val index = 0
    val value = 1

    res.add(index, value)
    res.toIntArray()
}

fun collection4() {
    val ia = IntArray(5) { 0 }

    ia.forEach {
        print(it)
    }
}

fun collection5() {
    // 1.
    val deepArray: Array<Array<Int>> = arrayOf(
        arrayOf(1),
        arrayOf(5, 6),
        arrayOf(2, 3, 4)
    )

    var list0 = deepArray.flatten()
    println(list0)

    list0 = list0.sortedBy { it }
    println(list0)

    // 1-1.
    val deepArray2: Array<IntArray> = arrayOf(
        intArrayOf(1),
        intArrayOf(5, 6),
        intArrayOf(2, 3, 4)
    )

    var list01 = deepArray2.toList().flatMap { e -> e.toList() }
    println(list01)

    list01 = list01.sortedBy { it }
    println(list01)

    // 2.
    val list = listOf(listOf(10,20),listOf(30,40),listOf(50,60))
    val mapList = list.map{element -> element.toString()}
    val flatMapList = list.flatMap{element -> element.toList() }

    println(mapList)
    println(flatMapList)

    // 3.
    val list2 = listOf("123", "45")
    println(list2.flatMap { it.toList() }) // [1, 2, 3, 4, 5]
}

private fun destructingList() {
    val numbers = listOf("A1", "A2", "A3")
    val (a, b, c) = numbers
    println("$a, $b, $c")
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toIntOrNull()) {
            1 -> collection1()
            2 -> collection2()
            3 -> collection3()
            4 -> collection4()
            5 -> collection5()
            6 -> destructingList()
            else -> exitProcess(1)
        }

        println("======")
    }
}