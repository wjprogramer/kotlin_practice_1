package algo_2_sort

// https://chercher.tech/kotlin/insertion-sort-kotlin
fun insertionSort(items: MutableList<Int>): List<Int> {
    if (items.isEmpty() || items.size < 2) {
        return items
    }

    for (count in 1 until items.count()){
        // println(items)
        val item = items[count]
        var i = count
        while (i > 0 && item < items[i - 1]){
            items[i] = items[i - 1]
            i -= 1
        }
        items[i] = item
    }

    return items
}

fun main(args: Array<String>) {
    val names = mutableListOf(8, 3, 2, 7, 4)
    println(names)

    val ordered = insertionSort(names)
    println(ordered)
}