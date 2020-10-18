package tour

import kotlin.system.exitProcess

fun function1(vararg numbers: Int) {

}

// Extension Function
//      - Extension functions are statically dispatched :
//        All the extension function calls are matched with respective functions on compile time itself,
//        so the dispatching occurs at compile time and it is called as static dispatching
//      - Rules for Extension functions :
//        You cannot override a member function
fun String.add(a:Int, b:Int) {
    val result = a +b
    println("$this, the total is : $result")
}

// TODO: infix functions

fun main() {
    while(true) {
        println(message = "Type number:")
        val option = readLine()

        when(option?.toInt()) {
            // Spread Operator (*) with vararg:
            // scatter the array into individual element
            1 -> function1(numbers = *intArrayOf(1, 2, 3, 4, 5))
            2 -> "today".add(1, 2)
            else -> exitProcess(1)
        }

        println("======")
    }
}