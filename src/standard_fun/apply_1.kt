package standard_fun

import kotlin.system.exitProcess

fun apply1() {

}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> apply1()
            else -> exitProcess(1)
        }

        println("======")
    }
}