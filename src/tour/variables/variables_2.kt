package tour.variables

import kotlin.reflect.KProperty

// https://stackoverflow.com/questions/53188226/how-to-get-the-name-of-a-variable-in-kotlin

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

val x = 1

fun main() {
    println(::x.get())
    println(::x.name)
}