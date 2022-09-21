package tour.function.operator

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

private fun overrideEquals() {

    open class Weapon(val name: String, val type: String) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Weapon

            if (name != other.name) return false
            if (type != other.type) return false

            return true
        }

        override fun hashCode(): Int {
            var result = name.hashCode()
            result = 31 * result + type.hashCode()
            return result
        }
    }

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