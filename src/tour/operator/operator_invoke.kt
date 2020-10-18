package tour.operator

import models.Person
import kotlin.system.exitProcess

fun invoke1() {
    val i = Person(1, "", "")

    i()
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> invoke1()
            else -> exitProcess(1)
        }

        println("======")
    }
}