package models

class Person(var id: Int, var firstName: String, var lastName: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return  false

        other as Person

        if (id != other.id) return false

        return super.equals(other)
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    /**
     * usage:
     * ```
     * val p = Person()
     * p() // will call invoke
     * ```
     */
    operator fun invoke() {
        println("Invoke Person($id)")
    }

    override fun toString(): String {
        return "$id. $firstName $lastName"
    }
}