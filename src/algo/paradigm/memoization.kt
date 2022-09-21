package algo.paradigm

// ref: https://web.ntnu.edu.tw/~algo/AlgorithmDesign.html

// memoization
private fun countLetterFrequency(s: String) {
    val counts = IntArray(26) { 0 }

    for (c in s) {
        val index = c.toLowerCase() - 'a'
        if (index in 0..26) {
            counts[index]++
        }
    }

    println(counts.joinToString { it.toString() })
}

private fun main() {
    println("# Memoization")

    println("## 範例：統計字母數量")
    countLetterFrequency("Hello World")
    countLetterFrequency("Hi")
}

