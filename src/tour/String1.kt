package tour

import java.lang.StringBuilder
import kotlin.system.exitProcess

fun string1() {
    val s = "Hello World"
    val sb = StringBuilder(s)

    sb[1] = 'x'

    println(sb.toString())

    sb.clear()

    sb.append('K')
    sb.append('o')
    sb.append('t')

    println(sb.toString())

    sb.reverse()
}

fun string2() {
    val s = "Hello"

    for (c in s) {
        print(c)
    }

    println()
}


private const val TAVERN_NAME = "Taernyl's Folly"
private fun placeOrder() {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.subSequence(0, indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order")
}

private fun showEscapeCharacters() {
    println("___: 1")
    println("Tab: \t1")
    println("Back: \b")                 // 退回鍵
    println("NewLine: \n")              // 新行
    println("\" _ \' _ \\ _ \$")
    println("Unicode: \u2605")
    println("Enter: \r")                // Enter 鍵
    println("")
}

private fun replaceDemo() {
    placeOrder("Shady,Dragon's Breath,5.91")
    placeOrder("Shady,Dragon's Breath,5.92")
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiou]")) {
        when(it.value) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }

private fun placeOrder(menuData: String) {
    val (type, name, price) = menuData.split(',')
    val phrase = "Ah, delicious $name!"
    println("Madrigal exclaims: ${toDragonSpeak(phrase)}")
}

/**
 * 不需要像 java 一樣，使用 .equals
 */
private fun stringCompareDemo() {
    var a = "123"
    var b = "456"
    println(a == b) // 結構相等
    println(a === b) // 參照相等（較少用）

    a = "456"
    b = "456"
    println(a == b)
    println(a === b)
}

private fun tourCharacters() {
    "Dragon's Breath".forEach {
        print("$it ")
    }
    println()
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toIntOrNull()) {
            1 -> string1()
            2 -> string2()
            3 -> placeOrder()
            4 -> showEscapeCharacters()
            5 -> replaceDemo()
            6 -> stringCompareDemo()
            7 -> tourCharacters()
            else -> exitProcess(1)
        }

        println("======")
    }
}