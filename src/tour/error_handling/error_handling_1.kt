package tour.error_handling

import models.Person
import java.lang.Integer.parseInt
import kotlin.system.exitProcess

// 所有例外都繼承自 `Throwable` class or 他的 subclass

/**
     Bruce Eckel says about checked exceptions:

     ```
     Examination of small programs leads to the conclusion that requiring exception
     specifications could both enhance developer productivity and enhance code quality,

     but experience with large software projects suggests a different result
     – decreased productivity and little or no increase in code quality.
    ```

     Other citations of this sort:

     *  《Java's checked exceptions were a mistake》 (Rod Waldhoff)
        (http://radio-weblogs.com/0122027/stories/2003/04/01/JavasCheckedExceptionsWereAMistake.html)
     *  《The Trouble with Checked Exceptions》 (Anders Hejlsberg)
        (http://www.artima.com/intv/handcuffs.html)
 */

private fun basic() {
    try {
        throw Exception("Hi There!")
    }
    catch (e: Exception) {
        // 《Effective Java》第三版 第 77 條：不要忽略例外。
        println(e.message)
    }
    finally {

    }
}

// `try` is an expression, i.e. it may have a return value:
private fun tryIsExpression(input: String) {
    val a: Int? = try { parseInt(input) } catch (e: NumberFormatException) { null } finally {
        println("我不會被回傳 " +
                "/ 只有 try 或 catch 的區塊的值會被回傳")
    }
    println("parse result: $a")
}

// If you want to alert callers of possible exceptions when calling Kotlin code
// from Java, Swift, or Objective-C, you can use the `@Throws` annotation
@Throws
private fun callFromOther() {

}

private fun throwIsExpression() {
    val person = Person(1, "W", "J")

    // `throw` is an expression in Kotlin, so you can use it, for example, as part of an Elvis expression:
    val s = person.firstName ?: throw IllegalArgumentException("Name required")
}

// The type of the throw expression is the special type Nothing.
// The type has no values and is used to mark code locations that can never be reached.
// In your own code, you can use Nothing to mark a function that never returns:
private fun throwIsNothing(): Nothing {
    throw Exception("My type is `Nothing`")
}

private fun throwIsNothing2(): Nothing? {
    throw Exception("My type is `Nothing`")
}

private fun fail() {
    throwIsNothing2()
    println("-------")
    throwIsNothing()
    println("the compiler will know that the execution doesn't continue beyond the call")
}

fun main() {
    while(true) {
        println("Type number:")
        val option = readLine()

        when(option?.toInt()) {
            1 -> basic()
            2 -> tryIsExpression("123")
            3 -> callFromOther()
            4 -> throwIsExpression()
            5 -> fail()
            else -> exitProcess(1)
        }

        println("======")
    }
}