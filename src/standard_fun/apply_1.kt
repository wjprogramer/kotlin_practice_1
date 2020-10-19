package standard_fun

import models.Person
import models.Telephone
import kotlin.system.exitProcess

fun apply11() {
    val telephone = Telephone().apply {
        // `this` is `Telephone` in apply scope
        whoCallMe = Person(1, "F", "L").also {
            // `it` is `Person` in also scope
            // `this` is `Telephone`
            it.firstName = "Wu"
        }.firstName
    }

    telephone.callMe("My")
}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> apply11()
            else -> exitProcess(1)
        }

        println("======")
    }
}