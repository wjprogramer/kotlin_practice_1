package tour.operator

import models.Person
import kotlin.system.exitProcess

fun equality1() {
    val i = Person(1, "", "")
    val j = i
    val k = Person(1, "", "")

    // reference equality
    println(i === j)

    // structural equality
    println(i == k)

    // non equality
    println(i !== j)

    // non-equality
    println(i != k)
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> equality1()
            else -> exitProcess(1)
        }

        println("======")
    }
}