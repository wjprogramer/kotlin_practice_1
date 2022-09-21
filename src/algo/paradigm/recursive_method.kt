package algo.paradigm

// 繁中「遞迴法」、簡中「递归法」。重複運用相同手法，縮減問題範圍，直到釐清細節。
// 注意到，遞推法、遞歸法，不等於程式語言中的迴圈、遞迴。遞推法、遞歸法是分析問題的方法，用來得到計算過程、用來得到演算法。至於編寫程式時，我們可以自由地採用迴圈或者遞迴。

// intro: https://web.ntnu.edu.tw/~algo/AlgorithmDesign.html#4
fun gcd(a: Int, b: Int): Int {
    var a1 = a
    var b1 = b

    // 令 a 比 b 大，比較容易思考。
    while (b1 != 0) {
        val t = a1 % b1
        a1 = b1
        b1 = t
    }

    return a1
}

private fun main() {
    println("# Recursive Method")
    println(gcd(5, 3))
    println(gcd(75, 30))
}