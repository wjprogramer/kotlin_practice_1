package tour

import kotlin.system.exitProcess

private fun nullability21() {
    var beverage1 = readLine()?.capitalize()
    var beverage2 = readLine()!!.capitalize()
    var beverage3 = readLine()?.let {
        if (it.isNotBlank()) {
            it.capitalize()
        } else { "Buttered Ale" }
    }
    var beverage4 = readLine()
    if (beverage4 != null) {
        beverage4 = beverage4.capitalize()
        println("- $beverage4")
    } else {
        beverage4 = "Buttered Ale"
    }
    var beverage5 = readLine()?.capitalize() ?: "Buttered Ale"
}

private fun nullability22() {
    var beverage = readLine()?.capitalize()
    beverage?.let {
        beverage = it.capitalize()
    } ?: println("I can't do that without crashing - beverage was null")
}


fun main() {
    while(true) {
        println("Type number:")
        val option = readLine()

        when(option?.toInt()) {
            1 -> nullability11()
            2 -> nullability12()
            else -> exitProcess(1)
        }

        println("======")
    }
}