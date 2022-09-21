package tmp

// (339, 38.9)
class Solution {
    private val elements = mutableListOf<Any>()

    fun diffWaysToCompute(expression: String): List<Int> {
        val sb = StringBuilder()

        // Generate Elements
        fun buildNum() {
            if (sb.isNotEmpty()) {
                elements.add(sb.toString().toInt())
                sb.clear()
            }
        }

        for (c in expression) {
            when (c) {
                '+', '-', '*' -> {
                    buildNum()
                    elements.add(c)
                }
                '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' -> {
                    sb.append(c)
                }
            }
        }
        buildNum()

        // Calculate
        return splitElements(elements)
    }

    private fun splitElements(elements: List<Any>): List<Int> {
        val res = mutableListOf<Int>()
        if (elements.size == 1) {
            val e = elements[0]
            if (e is Int) {
                return listOf(e)
            }
        }

        if (elements.size >= 3) {
            for (i in 1 until elements.size step 2) {
                val operator = elements[i]
                if (operator is Char) {
                    val es1 = tour(elements.subList(0, i).toList())
                    val es2 = tour(elements.subList(i + 1, elements.size).toList())
                    res.addAll(calculateLists(es1, operator, es2))
                }
            }
        }
        return res
    }

    private fun tour(elements: List<Any>): List<Int> {
        if (elements.isEmpty()) {
            return listOf()
        }

        if (elements.size == 1) {
            val n = elements.first()
            return listOf(n as Int)
        }

        if (elements.size == 3) {
            val e1 = elements[0] as Int
            val e2 = elements[1] as Char
            val e3 = elements[2] as Int
            return listOf(binaryCalculate(e1, e2, e3))
        }

        // size > 3
        return splitElements(elements)
    }

    private fun calculateLists(ns1: List<Int>, operator: Char, ns2: List<Int>): List<Int> {
        val res = mutableListOf<Int>()
        for (n1 in ns1) {
            for (n2 in ns2) {
                res.add(binaryCalculate(n1, operator, n2))
            }
        }
        return res
    }

    private fun binaryCalculate(n1: Int, operator: Char, n2: Int): Int {
        when (operator) {
            '+' -> return n1 + n2
            '-' -> return n1 - n2
            '*' -> return n1 * n2
        }
        return 0
    }
}

fun main() {
    print(Solution().diffWaysToCompute("2*3-4*5"))
}

