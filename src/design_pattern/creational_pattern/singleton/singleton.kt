// ref:
// https://zh.wikipedia.org/wiki/%E5%8D%95%E4%BE%8B%E6%A8%A1%E5%BC%8F
package design_pattern.creational_pattern.singleton

// Private constructor suppresses
// default public constructor
class Singleton private constructor() {

    companion object {
        private val INSTANCE = Singleton()

        fun getInstance() = INSTANCE
    }

}

fun main() {
    val singleton = Singleton.getInstance()
    val singleton2 = Singleton.getInstance()

    println("${singleton == singleton2}")
}
