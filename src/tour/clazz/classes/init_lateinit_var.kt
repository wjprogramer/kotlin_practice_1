package tour.clazz.classes

private class B {

    lateinit var name: String

    fun assign() {
        name = ""
    }

    fun check() {
        // 少用 isInitialized，不然就不要用延遲初始化
        if (::name.isInitialized) {
            println(name)
        }
    }

}