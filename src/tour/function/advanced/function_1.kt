package tour.function.advanced

import tour.enums.enum11
import kotlin.system.exitProcess

// region 1
interface MethodCallHandler {
    fun onMethodCall(a: Int, b: Int)
}

fun setMethodCallHandler(handler: MethodCallHandler) { }

private fun function1() {
    setMethodCallHandler(object : MethodCallHandler {
        override fun onMethodCall(a: Int, b: Int) {
            // do something
        }
    })
}

// endregion

fun main() {
    while(true) {
        println("======")
        print("Type number:")

        val option = readLine()

        when(option?.toInt()) {
            1 -> enum11()
            else -> exitProcess(1)
        }

        println("======")
    }
}