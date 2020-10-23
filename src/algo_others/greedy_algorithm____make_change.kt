package algo_others

// 找零錢 Change-Making Problem
/**
 * @param originalAmount 要兌換的金額
 */
private fun makingChange(originalAmount: Int) {
    var amount = originalAmount
    var coin = 0

    coin = amount / 10
    amount %= 10
    println("10元硬幣可兌換 $coin 個")

    coin = amount / 5
    amount %= 5
    println("5元硬幣可兌換 $coin 個")

    coin = amount
    println("1元硬幣可兌換 $coin 個")
}

fun main() {
    makingChange(100)
    println()

    makingChange(20)
    println()
}