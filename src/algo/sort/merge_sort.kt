package algo.sort

fun mergesort(a: IntArray, L: Int, R: Int) {
    if (L < R) {
        val M: Int = (L + R) / 2
        println("L = $L, M = $M, R = $R")

        mergesort(a, L, M)
        mergesort(a, M + 1, R)
        merge(a, L, M, R)
        println("=> " + a.joinToString(","))
    }
}

private fun merge(a: IntArray, L: Int, M: Int, R: Int) {
    var left: Int = L
    var right: Int = M + 1
    var i = L
    val tmp = IntArray(a.size)

    while ((left <= M) && (right <= R)) {
        if (a[left] < a[right]){
            tmp[i] = a[left];
            i++; left++;
        } else {
            tmp[i] = a[right];
            i++; right++;
        }
    }

    while (left <= M) {
        tmp[i] = a[left]
        i++; left++
    }

    while (right <= R) {
        tmp[i] = a[left]
        i++; right++
    }

    for (j in L until (R + 1)) {
        a[j] = tmp[j]
    }
}

fun mergeSortDemo() {
    val a = intArrayOf(55, 78, 89, 45, 65, 99, 23, 35)

    println("排序前")
    println(a.joinToString(", "))

    mergesort(a, 0, 7)

    println("排序後")
    println(a.joinToString(", "))

}

fun main() {
    mergeSortDemo()
}