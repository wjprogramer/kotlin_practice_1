package models

class Person(val id: Int, val firstName: String, val lastName: String) {
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
}