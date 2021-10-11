package tour.clazz.classes

import kotlin.system.exitProcess

private fun demo1() {
    A("Jay", false)
}

/**
 * Kotlin 習慣：
 * 底線開頭代表「臨時變數」（包括僅參照一次的參數）
 */
private class A(_name: String,
                var isImmortal: Boolean) {

    var name = _name

}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()

        when(option?.toIntOrNull()) {
            1 -> demo1()
            else -> exitProcess(1)
        }

        println("======")
    }
}