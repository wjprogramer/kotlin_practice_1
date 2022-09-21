package tour

import kotlin.system.exitProcess

// https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/635326/

fun map1() {
    // Map
    // - unmodifiable map
    // - does not permit subsequent addition or removals.
    val immutableMap: Map<String, String> = mapOf(
        "Red" to "#FF0000",
        "Blue" to "#0000FF"
    )

    println(immutableMap)
}

fun map2() {
    // MutableMap
    val mutableMap = mutableMapOf(
        "Red" to "#FF0000",
        "Blue" to "#0000FF"
    )
    println(mutableMap)

    mutableMap["Red"] = "赤色"
    println(mutableMap)

    mutableMap["Black"] = "#000000"
    println(mutableMap)
}

fun map3() {
    val hashMap: HashMap<String, String> = hashMapOf(
        "Red" to "#FF0000",
        "Blue" to "#0000FF"
    )
    println(hashMap)

    hashMap["Red"] = "赤色"
    println(hashMap)
}

fun map4() {
    // LinkedMap
    // LinkedHashMap能夠做到按照插入順序或者訪問順序進行迭代順序
    val linkedMap = linkedMapOf(
        "Red" to 1,
        "Blue" to 2
    )

    linkedMap.toSortedMap()
    println(linkedMap)
    linkedMap["Red"] = 2
    println(linkedMap["Red"])

    val linkedMap2 = linkedMapOf(
        4 to 5,
        3 to 7,
        1 to 6,
        2 to 8
    )

    println(linkedMap2)
}

fun map5() {
    val immutableMap: Map<String, String> = emptyMap()
}

fun map6() {
    // Java 9 made it very convenient to create compact, unmodifiable instance of map by providing static factory methods on the java.util.Map interface.
    val immutableMap: Map<String, String> = java.util.Map.of(
        "Red", "#FF0000",
        "Blue", "#0000FF"
    )

    println(immutableMap)
}

fun map7() {
    // Double Brace Initialization
    // Another alternative is to use Double Brace Initialization which creates an anonymous inner class with an instance initializer in it.
    val map: HashMap<String, String> = object : HashMap<String, String>() {
        init {
            put("Red", "#FF0000")
            put("Blue", "#0000FF")
        }
    }

    println(map)
}

fun map8() {
    val sortedMap = sortedMapOf(
        4 to 99,
        2 to 99,
        1 to 99,
        6 to 99
    )

    println(sortedMap)
    sortedMap[4] = sortedMap[4]?.plus(1)
    sortedMap[1] = sortedMap[1]?.minus(1)
    sortedMap[5] = sortedMap[5]?.plus(1)
    sortedMap[9] = if (sortedMap[9] == null) 0 else sortedMap[9]!!.plus(1)
    println(sortedMap)
    sortedMap.remove(9)
    println(sortedMap.size)
    println(sortedMap.keys.toList()[0])
    println(sortedMap)
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> map1()
            2 -> map2()
            3 -> map3()
            4 -> map4()
            8 -> map8()
            else -> exitProcess(1)
        }

        println("======")
    }
}