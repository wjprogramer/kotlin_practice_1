package algo_paradigm


// https://zh.wikipedia.org/wiki/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92

// 動態規劃（英語：Dynamic programming，簡稱DP）是一種在數學、管理科學、電腦科學、經濟學和生物資訊學中使用的，通過把原問題分解為相對簡單的子問題的方式求解複雜問題的方法。

// http://web.ntnu.edu.tw/~algo/DynamicProgramming.html

// 1 × 2 × 3 × ⋯ × N 。整數 1 到 N 的連乘積。 N 階乘。 N! 。
// N! 源自 (N-1)! ，如此就遞迴分割問題了。

// 陣列的每一格對應每一個問題。設定第一格的答案，再以迴圈依序計算其餘答案。

// 1. Time Complexity: 總共 N 個問題，每個問題花費 O(1) 時間，總共花費 O(N) 時間。
// 2. Space Complexity:
//      - 求 1! 到 N! ：總共 N 個問題，用一條 N 格陣列儲存全部問題的答案，空間複雜度為 O(N) 。
//      - 只求 N! ：用一個變數累計乘積，空間複雜度為 O(1) 。

private fun factorial(n: Int): IntArray {
    if (n == 0) {
        return intArrayOf(1)
    }

    if (n == 1) {
        return intArrayOf(1, 1)
    }

    val res = IntArray(n) { 1 }

    for (i in 2 until n) {
        res[i] = res[i - 1] * i
    }

    return res
}

private fun factorial2(n: Int): Int {
    var res = 1
    for (i in 2..n) {
        res *= i
    }
    return res
}

fun main() {
    println(factorial(3).joinToString(","))
    println(factorial(5).joinToString(","))

    println("======")

    println(factorial2(3))
    println(factorial2(5))
}