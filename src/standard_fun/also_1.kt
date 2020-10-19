package standard_fun

import models.Telephone
import kotlin.system.exitProcess

/** region Data */
/** endregion */

/** region Demo */
fun also11() {
    val telephone = Telephone().also {
        it.fromWhere = "..."
    }.also {
        it.whoCallMe = "..."
    }

    telephone.callMe("Jay")
}
/** endregion */

fun main() {
    while(true) {
        print("Type number: ")

        val option = readLine() ?: exitProcess(1)

        println("======")

        when(option.toInt()) {
            1 -> also11()
            else -> exitProcess(1)
        }

        println("======")
    }
}