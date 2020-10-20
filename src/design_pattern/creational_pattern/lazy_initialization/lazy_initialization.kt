// ref:
// https://zh.wikipedia.org/wiki/%E6%83%B0%E6%80%A7%E5%88%9D%E5%A7%8B%E6%A8%A1%E5%BC%8F
package design_pattern.creational_pattern.lazy_initialization

import java.util.*

// using a private constructor to force use of the factory method.
class Fruit private constructor(private val type: String) {

    companion object {
        private val types: MutableMap<String, Fruit?> = HashMap()

        /**
         * Lazy Factory method, gets the Fruit instance associated with a
         * certain type. Instantiates new ones as needed.
         * @param type Any string that describes a fruit type, e.g. "apple"
         * @return The Fruit instance associated with that type.
         */
        @Synchronized
        fun getFruit(type: String): Fruit? {
            if (!types.containsKey(type)) types[type] = Fruit(type) // Lazy initialization
            return types[type]
        }
    }
}