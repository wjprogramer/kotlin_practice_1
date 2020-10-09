package algo_strategy

// ex 1 階乘
fun factorial(n: Int): Int {
    return if (n == 1) {
        1
    } else {
        n * factorial(n - 1)
    }
}

// ex 2
// 輸出 a 的 b 次方除以 1234 的餘數。。
fun power(a: Int, b: Int): Int {
    return when {
        b == 0 -> {
            1
        }
        b == 1 -> {
            a % 1234
        }
        b % 2 == 0 -> {
            val tmp = power(a, b / 2) % 1234
            tmp * tmp % 1234
        }
        else -> {
            a * power(a, b - 1) % 1234
        }
    }
}

// ex 3 mergeSort (請參閱 merge_sort.kt)



fun main() {

}