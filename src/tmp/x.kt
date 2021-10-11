package tmp


class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

// kotlin Solution with dp
// https://leetcode.com/problems/unique-binary-search-trees/discuss/471165/kotlin-Solution-with-dp
class Solution {
    fun numTrees(n: Int): Int {
        val dp = MutableList(n + 1) { 1 }

        for (i in 2..n) {
            val excludeRoot = i - 1
            var result = 0
            for (j in 0..excludeRoot) {
                result += dp[j] * dp[excludeRoot - j]
            }
            dp[i] = result
        }

        return dp[n]
    }
}
