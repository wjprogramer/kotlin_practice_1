package tour.function.advanced.params

private fun demo(vararg a: String) {
    println(a.joinToString(","))
}

private fun demo2(vararg a: String, number: Int) {

}

private fun demoX(vararg a: String, text: String) {

}

fun main() {
    demo()

    demo("Hello", "World")

    val array = arrayOf("Hi", "Siri")
    demo(*array)
}