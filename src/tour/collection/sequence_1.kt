package tour.collection

import kotlin.system.measureNanoTime

private fun demo1() {
    // 接受一個種子值作為序列的起步值
    generateSequence(0) { it + 1 }
        .onEach { println("The Count says: $it, ah ah ah!") }
}

private fun getPrimesByTakeNumber() {
    val oneThousandPrimes = generateSequence(3) { value ->
        value + 1
    }.filter { it.isPrime() }.take(1000)
    println(oneThousandPrimes.toList())
}

private fun Int.isPrime(): Boolean {
    (2 until this).map {
        if (this % it == 0) {
            return false // Not a prime
        }
    }
    return true
}

private fun convertSequence() {
    val listOfNumbers = (0 until 10000000).toList()
    val sequence = listOfNumbers.asSequence()
}

private fun comparePerformance() {
    val listInNanos = measureNanoTime {
        (3..7920).toList().filter { it.isPrime() }.take(1000)
    }

    val sequenceInNanos = measureNanoTime {
        generateSequence(3) { value ->
            value + 1
        }.filter { it.isPrime() }.take(1000)
    }

    println("List completed in $listInNanos ns")
    println("Sequence completed in $sequenceInNanos ns")
}

fun main() {
    demo1()
    getPrimesByTakeNumber()
    convertSequence()
    comparePerformance()
}