package tmp

import java.util.*

// 1331
// https://leetcode.com/problems/rank-transform-of-an-array/
fun arrayRankTransform(arr: IntArray): IntArray {
    val numberCountSortMapping = TreeMap<Int, Int>()

    arr.forEach {
        val count = numberCountSortMapping.getOrDefault(it, 0) + 1
        numberCountSortMapping[it] = count
    }

    val result = mutableListOf<Int>()
    var progressiveCount = 0
    numberCountSortMapping.forEach {
        numberCountSortMapping[it.key] = numberCountSortMapping[it.key]!! + progressiveCount
        progressiveCount = numberCountSortMapping[it.key]!!
    }

    return result.toIntArray()
}

fun main() {

    println(arrayRankTransform(intArrayOf(40, 10, 20, 30)))

    // test
//    val treeMap: SortedMap<Int, Int> = TreeMap<Int, Int>()
//    treeMap[0] = 1
//    treeMap[0] = 2
}