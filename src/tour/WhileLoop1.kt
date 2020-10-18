package tour

fun while1() {
    val items = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
}

fun while2() {
    var i = 0
    do {
        i++
        println(i)
    } while(i < 3)
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        println("======")

        when(option?.toInt()) {
            1 -> while1()
            2 -> while2()
        }

        println("======")
    }
}