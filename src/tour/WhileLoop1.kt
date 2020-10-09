package tour

fun while1() {
    val items = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        println("======")

        when(option?.toInt()) {
            1 -> while1()
        }

        println("======")
    }
}