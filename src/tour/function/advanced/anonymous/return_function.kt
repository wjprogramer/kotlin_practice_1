package tour.function.advanced.anonymous

import kotlin.system.exitProcess

private fun `return function`() {
    runSimulation()
}

private fun runSimulation() {
    val greetingFunction = configureGreetingFunction()
    println(greetingFunction("Guyal")) // 6
    println(greetingFunction("Guyal")) // 7
}

private fun configureGreetingFunction(): (String) -> String {
    val structureType = "hospitals"
    var numBuildings = 5
    return { playerName ->
        val currentYear = 2018
        numBuildings += 1
        println("Adding $numBuildings $structureType")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
}

fun main() {
    while(true) {
        println("======")
        print("Type number: ")

        val option = readLine()

        when(option?.toInt()) {
            1 -> `return function`()
            else -> exitProcess(1)
        }

        println("======")
    }
}