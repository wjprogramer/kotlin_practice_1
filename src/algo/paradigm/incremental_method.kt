package algo.paradigm

import algo.sort.selectionSort

private fun main(arg: Array<String>) {
    println("# Incremental Method")

    println("## Selection Sort")
    println("Before Sort")
    val array = intArrayOf(1, 7, 3, 9, 4)
    for (i in array) print(i)

    selectionSort(array)

    println("Sorted array is : ")
    for (i in array) print(i)
}