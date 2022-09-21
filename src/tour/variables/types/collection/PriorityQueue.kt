package tour.variables.types.collection

import java.util.*
import kotlin.system.exitProcess

private fun priorityQueue1() {
    val queue = PriorityQueue<Int>(10) { a, b -> a - b }

    add(queue, 1)
    add(queue, 2)
    add(queue, 3)
    add(queue, -1)
    add(queue, 0)

    println(queue.peek())
    println(queue.joinToString(","))

    poll(queue)
    poll(queue)
    poll(queue)
    poll(queue)
}

private fun priorityQueue2() {
    val queue = PriorityQueue<Int>()

}

private fun priorityQueue3() {
    val queue = PriorityQueue<Int>(Collections.reverseOrder())

}

// region utils

private fun <T> add(queue: PriorityQueue<T>, item: T) {
    queue.add(item)
    println("add $item")
}

private fun <T> poll(queue: PriorityQueue<T>): T {
    val res = queue.poll()
    println("poll $res")
    return res
}

// endregion


fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine() ?: exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> priorityQueue1()
            2 -> priorityQueue2()
            3 -> priorityQueue3()
            else -> exitProcess(1)
        }

        println("======")
    }
}