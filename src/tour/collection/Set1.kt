package tour.collection

import java.io.File
import kotlin.system.exitProcess

/**
 * Set: 無重複元素集合
 */

fun set1() {
    val s = mutableSetOf<Int>()

    s.add(1)
    println(s.size)
    println(s)

    s.add(1)
    println(s.size)
    println(s)

    s.contains(1)
    s.containsAll(listOf(1, 2))

    // 不要用這個！！會不斷迭代尋找元素
    s.elementAt(0)
}

/**
 * set  : 無序、不能重複
 * list : 有序、可重複、利用 index 高效
 *
 * => 結合使用：有序、不可重複、要利用 index
 */
private fun setAndListComposition() {
    val patronList = mutableListOf("Eli", "Mar", "Sophie")
    val lastName = listOf("IronFoot", "FernsWorth", "Bag")
    val uniquePatrons = mutableSetOf<String>()

    val list = File("data/my-data.txt")
        .readText()
        .split("\n")

    (0..9).forEach { _ ->
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }
}

private fun listToSet() {
    val list = listOf(1, 2, 3, 3)
    println(list)
    println("利用 toSet 消除陣列中重複元素")
    println(list.toSet())
    println("消除重複元素後，若想用 index 存取，再 toList")
    println(list.toSet().toList())
}

private fun distinctDemo() {
    println("toSet().toList() 太常見，所以有了 distinct")
    val list = listOf(1, 2, 3, 3)
    println(list.distinct())
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> set1()
            2 -> setAndListComposition()
            3 -> listToSet()
            4 -> distinctDemo()
            else -> exitProcess(1)
        }

        println("======")
    }
}