package tmp

fun maxSubArray(nums: IntArray): Int {
    if (nums.isEmpty()) {
        return 0
    }

    if (nums.size == 1) {
        return nums.first()
    }




    return 0
}

fun main() {
    println(maxSubArray(intArrayOf(-2,1,-3,4,-1,2,1,-5,4)))
    println(maxSubArray(intArrayOf(1)))
    println(maxSubArray(intArrayOf(0)))
    println(maxSubArray(intArrayOf(-1)))
    println(maxSubArray(intArrayOf(-2147483647)))
}