package algo.paradigm

// 字串變整數
private fun strToInt() {
    val s = "26962869"
    var n = 0
    for (i in s.indices) {
        val number = s[i] - '0' // 或是這樣也可以 Character.getNumericValue('2')
        n += number * Math.pow(10.0, s.length.toDouble() - 1 - i).toInt()
    }
    println(n)
}

private fun main() {
    println("# Iterative Method")
    strToInt()
}