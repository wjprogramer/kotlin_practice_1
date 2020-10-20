// ref:
// https://en.wikipedia.org/wiki/Multiton_pattern
package design_pattern.creational_pattern.multiton_pattern

enum class MultitonType {
    Zero, One, Two
}

class Multiton private constructor(private var type: MultitonType) {

    companion object {
        private val instances = mutableMapOf<MultitonType, Multiton>()

        fun getInstance(type: MultitonType): Multiton? {
            // Lazy init (not thread safe as written)
            // Recommend using Double Check Locking if needing thread safety
            var instance: Multiton? = instances[type]
            if (instance == null) {
                instance = Multiton(type)
                instances[type] = instance
            }

            return instance
        }
    }

    override fun toString(): String {
        return "My type is $type"
    }

}

fun main() {
    val m0 = Multiton.getInstance(MultitonType.Zero)
    val m1 = Multiton.getInstance(MultitonType.Zero)
    val m2 = Multiton.getInstance(MultitonType.Two)
    val m3 = Multiton.getInstance(MultitonType.One)

    println(m0)
    println(m1)
    println(m2)
    println(m3)

    println(m0 == m1)
}