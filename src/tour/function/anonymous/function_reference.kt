package tour.function.anonymous

import kotlin.system.exitProcess

/**
 * 函數參照
 *
 * 1. 除了 lambda 可以做為參數傳遞給其他函數，函數參照也可以達到此效果
 * 2. 函數參照可以將具名函數（以 `fun` 定義的函數）轉換為一個引數
 * 3. 凡事出現 lambda 運算式的地方，都允許使用函數參照
 */

//private fun functionReference1() {
//    runSimulation("Guyal", ::printConstructionCost) { playerName, numBuildings ->
//        val currentYear = 2018
//
//        "Welcome to SimVillage, $playerName! (copyright ${}"
//    }
//}

private fun printConstructionCost(numBuildings: Int) {
    val cost = 500
    println("construction cost: ${cost * numBuildings}")
}

private inline fun runSimulation(playerName: String, costPrinter: (Int) -> Unit, greetingFunction: (String, Int) -> String) {
    val numBuilding = (1..3).shuffled().last()
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