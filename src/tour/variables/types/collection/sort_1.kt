package tour.variables.types.collection

import kotlin.system.exitProcess

// Basic
private fun sort1() {
    val nums = mutableListOf(3, 1, 7, 2, 8, 6)

    nums.sort()
    // nums: [1, 2, 3, 6, 7, 8]

    nums.sortDescending()
    // nums: [8, 7, 6, 3, 2, 1]

}

// 不改變原本的
private fun sorted() {
    val nums = mutableListOf(3, 1, 7, 2, 8, 6)

    val sortedNums = nums.sorted()
    // nums: [3, 1, 7, 2, 8, 6]
    // sortedNums: [1, 2, 3, 6, 7, 8]

    val sortedNumsDescending = nums.sortedDescending()
    // nums: [3, 1, 7, 2, 8, 6]
    // sortedNumsDescending: [8, 7, 6, 3, 2, 1]

}

// 指定 field
private fun sortBy() {
    val myDates = mutableListOf(
        MyDate(4, 3),
        MyDate(5, 16),
        MyDate(1, 29)
    )

    myDates.sortBy { it.month }
    myDates.forEach { println(it) }
    /*
        MyDate(month=1, day=29)
        MyDate(month=4, day=3)
        MyDate(month=5, day=16)
    */

    myDates.sortByDescending { it.month }
    myDates.forEach { println(it) }
    /*
        MyDate(month=5, day=16)
        MyDate(month=4, day=3)
        MyDate(month=1, day=29)
    */
}

// 指定 filed / 產生新的
private fun sortedBy() {
    val myDates = mutableListOf(
        MyDate(4, 3),
        MyDate(5, 16),
        MyDate(1, 29)
    )

    val sortedDates = myDates.sortedBy { it.month }
    myDates.forEach { println(it) }
    /*
        MyDate(month=4, day=3)
        MyDate(month=5, day=16)
        MyDate(month=1, day=29)
    */

    sortedDates.forEach { println(it) }
    /*
        MyDate(month=1, day=29)
        MyDate(month=4, day=3)
        MyDate(month=5, day=16)
    */

    val sortedDatesDescending = myDates.sortedByDescending { it.month }
    myDates.forEach { println(it) }
    /*
        MyDate(month=4, day=3)
        MyDate(month=5, day=16)
        MyDate(month=1, day=29)
    */
    sortedDatesDescending.forEach { println(it) }
    /*
        MyDate(month=5, day=16)
        MyDate(month=4, day=3)
        MyDate(month=1, day=29)
    */

}

// 比較多個 fields
private fun sortWith() {
    val myDates = mutableListOf(
        MyDate(8, 19),
        MyDate(5, 16),
        MyDate(1, 29),
        MyDate(5, 10),
        MyDate(8, 3)
    )

    // 舊寫法: compareBy { it.month }.thenBy { it.day }
    // 只有一個: compareBy { it.month }
    myDates.sortWith(compareBy({ it.month }, { it.day }))
    myDates.forEach { println(it) }
    /*
        MyDate(month=1, day=29)
        MyDate(month=5, day=10)
        MyDate(month=5, day=16)
        MyDate(month=8, day=3)
        MyDate(month=8, day=19)
    */

    myDates.reverse()
    myDates.forEach { println(it) }
    /*
        MyDate(month=8, day=19)
        MyDate(month=8, day=3)
        MyDate(month=5, day=16)
        MyDate(month=5, day=10)
        MyDate(month=1, day=29)
    */
}

// 比較多個 fields / 產生新的
private fun sortedWith() {
    val myDates = mutableListOf(
        MyDate(8, 19),
        MyDate(5, 16),
        MyDate(1, 29),
        MyDate(5, 10),
        MyDate(8, 3)
    )

    val sortedDates = myDates.sortedWith(compareBy({ it.month }, { it.day }))
    myDates.forEach { println(it) }
    /*
    MyDate(month=8, day=19)
    MyDate(month=5, day=16)
    MyDate(month=1, day=29)
    MyDate(month=5, day=10)
    MyDate(month=8, day=3)
    */
    sortedDates.forEach { println(it) }
    /*
    MyDate(month=1, day=29)
    MyDate(month=5, day=10)
    MyDate(month=5, day=16)
    MyDate(month=8, day=3)
    MyDate(month=8, day=19)
    */

    val sortedDatesDescending = myDates.sortedWith(compareBy({ it.month }, { it.day })).reversed()
    myDates.forEach { println(it) }
    /*
    MyDate(month=8, day=19)
    MyDate(month=5, day=16)
    MyDate(month=1, day=29)
    MyDate(month=5, day=10)
    MyDate(month=8, day=3)
    */
    sortedDatesDescending.forEach { println(it) }
    /*
    MyDate(month=8, day=19)
    MyDate(month=8, day=3)
    MyDate(month=5, day=16)
    MyDate(month=5, day=10)
    MyDate(month=1, day=29)
    */

}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine() ?: exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> sort1()
            else -> exitProcess(1)
        }

        println("======")
    }
}

/*

ref

    - https://bezkoder.com/kotlin-sort-list/

 */

private data class MyDate(val month: Int, val day: Int)