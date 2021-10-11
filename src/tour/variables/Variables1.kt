package tour

val PI = 3.14

/**
 * 區域變數初始化時可不用給值（檔案層級變數不行）
 * 只要使用該變數前要有值就好
 */
private fun variable1() {
    val name: String

    val firstName: String
    firstName = "Hello"

    var number: Int
    number = 1
    number += 1
}

/*
    Variables Demo
 */
fun main() {
    val a: Int = 1  // immediate assignment
    val b = 2   // `Int` type is inferred
    val c: Int  // Type required when no initializer is provided
    c = 3       // deferred assignment

    var x = 5 // `Int` type is inferred
    x += 1
}