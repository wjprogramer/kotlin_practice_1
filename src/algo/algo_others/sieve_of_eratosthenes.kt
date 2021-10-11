package algo.algo_others

fun countPrimes(n: Int): Int {
    var count = 0
    val signs = BooleanArray(n) { false }

    for (i in 2 until n) {
        if (!signs[i - 1]) {
            count++

            for (j in i until n step i) {
                signs[j - 1] = true
            }
        }
    }

    return count
}



fun main() {
    println("countPrimes(0): ${countPrimes(0)}")
    println("countPrimes(1): ${countPrimes(1)}")
    println("countPrimes(2): ${countPrimes(2)}")
    println("countPrimes(3): ${countPrimes(3)}")
    println("countPrimes(4): ${countPrimes(4)}")
    println("countPrimes(5): ${countPrimes(5)}")
    println("countPrimes(6): ${countPrimes(6)}")
    println("countPrimes(7): ${countPrimes(7)}")
    println("countPrimes(8): ${countPrimes(8)}")
    println("countPrimes(9): ${countPrimes(9)}")
}