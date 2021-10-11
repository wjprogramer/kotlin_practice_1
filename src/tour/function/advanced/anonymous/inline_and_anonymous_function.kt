package tour.function.advanced.anonymous

import kotlin.system.exitProcess

/**
 * 1. 因為使用 lambda 會有嚴重效能問題，（JVM 會為所有和 lambda 的變數分配記憶體），使用內連 inline 的最佳化機制解決
 * 2. 有了內聯，JVM 就不需要使用 lambda 物件的實例，避免變數記憶體的分配
 * 3. 內聯：將函數本體直接複製過去
 * 4. 遞迴無法使用內聯，因為要一直複製，形成無限迴圈，編譯器會阻止
 */

private fun inlineAndAnonymousFunction() {
    val greetingFunction: (String, Int) -> String = { playerName, numBuildings ->
        val currentYear = 2018
        println("Adding $numBuildings houses")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
    val playerName = "Player"
    runSimulation(playerName, greetingFunction)
}

private inline fun runSimulation(playerName: String, greetingFunction: (String, Int) -> String) {
    val numBuildings = (1..3).shuffled().last()
    println(greetingFunction(playerName, numBuildings))
}

fun main() {
    while(true) {
        println("======")
        print("Type number:")

        val option = readLine()

        when(option?.toInt()) {
            else -> exitProcess(1)
        }

        println("======")
    }
}