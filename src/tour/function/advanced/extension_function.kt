package tour.function.advanced

/**
 * 需指定接受翁能擴充的「接收者類型（receiver type）」
 */
private fun String.addEnthusiasm(amount: Int = 1) = this + "!".repeat(amount)

/**
 * 在超類別定義擴充函數
 */
private fun Any.easyPrint(): Any {
    println(this)
    return this
}

private fun <T> T.easyPrint2(): T {
    println(this)
    return this
}

fun main() {
//    "Hello"
//        .easyPrint()
//        .addEnthusiasm(5) // Error

    2.easyPrint()

    "Hello"
        .easyPrint2()
        .addEnthusiasm(5)
        .easyPrint2()

    22.easyPrint2()
}