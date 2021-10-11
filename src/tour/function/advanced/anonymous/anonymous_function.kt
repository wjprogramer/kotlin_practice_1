package tour.function.advanced.anonymous

/**
 * 1. 匿名函數也嘔類型，稱作 `function type`（函數類型）
 *    （但函數類型有個叫 [Function] 的類型，是不同概念）
 * 2. predicate
 * 3. lambda
 */

import kotlin.system.exitProcess

private fun anonymousFunction1() {
    val numLetters = "Mississippi".count({ letter ->
        letter == 's'
    })
    println(numLetters)
}

private fun `custom anonymous function1`() {
    println({
        val currentYear = 2018
        "Welcome to SimVillage, Mayor! (copyright $currentYear)"
    }())
}

private fun `custom anonymous function2`() {
    val greetingFunction: () -> String = {
        val currentYear = 2018

        // 1. 匿名函數除了在極少數情況下，不需要使用到 return
        // 2. 之所以不能使用，是因為編譯器不知道返回資料究竟是來自呼叫匿名函數的函數，還是匿名函數本身
        "Welcome to SimVillage, Mayor! (copyright $currentYear)"
    }
    println(greetingFunction())
}

private fun `custom anonymous function3`() {
    val greetingFunction: (String, Int) -> String = { playerName, numBuildings ->
        val currentYear = 2018
        println("Adding $numBuildings houses")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
    `pass a lambda function1`(greetingFunction)

    `pass a lambda function1`() { playerName, numBuildings ->
        // 如果 lambda 排在參數最後一個，就直接將大括弧放在外面就好
        "$playerName, $numBuildings"
    }
}

private fun `pass a lambda function1`(greetingFunction: (String, Int) -> String) {
    val numBuildings = (1..3).shuffled().last()
    println(greetingFunction("Guyal", numBuildings))
}

private fun `parameters type inference`() {
    val greetingFunction = { playerName: String, numBuildings: Int ->
        val currentYear = 2018
        println("Adding $numBuildings houses")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
    `pass a lambda function1`(greetingFunction)
}

fun main() {
    while(true) {
        println("======")
        print("Type number:")

        val option = readLine()

        when(option?.toInt()) {
            1 -> anonymousFunction1()
            2 -> `custom anonymous function1`()
            else -> exitProcess(1)
        }

        println("======")
    }
}