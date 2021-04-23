package tour.collection.queue

import java.util.Queue
import java.util.LinkedList

private fun `hello queue`() {
    val numbers: Queue<Int> = LinkedList<Int>(listOf(1, 2, 3))
    println(numbers)

    // add
    numbers.add(4)
    println("numbers: $numbers")

    // remove (如果佇列是空的，remove會出錯)
    val removedNumber = numbers.remove()
    println("numbers: $numbers, removed: $removedNumber")

    // poll (與 remove 相近，但佇列空的時候，poll 會回傳 null)
    val n1 = numbers.poll()
    val n2 = numbers.poll()
    val n3 = numbers.poll()
    val n4 = numbers.poll()
    println("numbers: $numbers, removed: $n1, $n2, $n3, $n4")
}

private fun `use peek`() {
    val names: Queue<String> = LinkedList<String>()

    names.add("Jack")
    names.add("Adam")
    names.add("Katherin")

    println("Queue: $names")
    println("is Queue empty? : " + names.isEmpty())
    println("Size of Queue : " + names.size)

    val name = "Adam"
    if (names.contains(name)) {
        println("Queue contains $name");
        println("-at index " + names.indexOf(name));
    } else {
        println("Queue doesn't contain $name");
    }

    var first = names.element()
    println("First item in Queue: $first");

    first = names.peek()
    println("First item in Queue: $first");
}

private fun `iterate queue`() {
    val names: Queue<String> = LinkedList()

    names.add("Jack")
    names.add("Adam")
    names.add("Katherin")

    println("== forEach() ==")
    names.forEach { name -> print(">$name ") }

    println("\n==  simple for loop ==")
    for (name in names) {
        print(">$name ")
    }

    println("\n==  iterator ==")
    var queueIterator = names.iterator()
    while (queueIterator.hasNext()) {
        val name = queueIterator.next()
        print(">$name ")
    }

    println("\n== iterator with forEachRemaining ==")
    queueIterator = names.iterator()
    queueIterator.forEachRemaining { name -> print(">$name ") }
}

fun main() {

    println("# Hello Queue\n")
    `hello queue`()
    println()

    println("# Peek\n")
    `use peek`()
    println()

    println("# Iterate\n")
    `iterate queue`()
    println()

}


