package tour.`class`

private class B {

    lateinit var name: String

    fun assign() {
        name = ""
    }

    fun check() {
        if (::name.isInitialized) {
            println(name)
        }
    }

}