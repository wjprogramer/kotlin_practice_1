package tour.function.standard_fun

// 利用泛型擴充函數，隱式存取接收者
inline fun <T> T.myApply(block: T.() -> Unit): T {
    block()
    return this
}

fun main() {
    "Hi".myApply {
        println(length)
    }
}