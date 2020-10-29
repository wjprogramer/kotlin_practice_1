package tmp

// TODO: 寫一半
// https://leetcode.com/problems/valid-palindrome-ii/

fun validPalindrome(s: String): Boolean {
    if (s.isBlank() || s.length == 1) return true

    // delete none
    for (i in 0 until s.length / 2) {
        if (s[i] != s[s.length - i - 1]) {
            break
        } else if (i == s.length / 2 - 1) {
            return true
        }
    }

    // delete one
    deleteOne@
    for (i in s.indices) {
        val deleteIndex = if (i % 2 == 0) {
            i / 2
        } else {
            s.length - 1 - i / 2
        }

        var left = if (deleteIndex != 0) 0 else 1
        var right = if (deleteIndex != s.length - 1) s.length - 1 else s.length - 2

        checkPalindrome@
        while (left < right) {
            if (s[left] != s[right]) {
                if (i % 2 == 1 && i != s.length - 1) {
                    return false
                }
                continue@deleteOne
            }

            left++
            if (left == deleteIndex) left++

            right--
            if (right == deleteIndex) right--
        }

        return true
    }

    return false
}

fun main() {
    println(validPalindrome("aba"))
    println(validPalindrome("abca"))
}