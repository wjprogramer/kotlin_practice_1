package tour.idioms

import java.math.BigDecimal
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

// Creating DTOs (POJOs/POCOs)
data class Customer(val name: String, val email: String)

fun foo(a: Int = 0, b: String = "") {  }

// Lazy property
val p: String by lazy {
    "test"
}

// Extension Functions
fun String.spaceToCamelCase() {  }

// Creating a singleton
object Resource {
    val name = "Name"
}

fun useless(tmp: String) {

}

// Return on when statement
fun transform(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }
}

// 'try/catch' expression
fun test() {
    val result = try {
        // count()...
    } catch (e: ArithmeticException) {
        throw IllegalStateException(e)
    }
    // Working with result
}

// 'if' expression
fun foo(param: Int) {
    val result = if (param == 1) {
        "one"
    } else if (param == 2) {
        "two"
    } else {
        "three"
    }
}

// Builder-style usage of methods that return Unit
fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply { fill(-1) }
}

fun theAnswer() = 42

class Turtle {
    fun penDown() {}
    fun penUp() {}
    fun turn(degrees: Double) {}
    fun forward(pixels: Double) {}
}

// Calling multiple methods on an object instance (with)
fun turtleDemo() {
    val myTurtle = Turtle()
    with(myTurtle) { //draw a 100 pix square
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }
}

class Rectangle {
    var length: Int = 0
    var breadth: Int = 0
    var color = 0xFAFAFA
}

// Configuring properties of an object (apply)
val myRectangle = Rectangle().apply {
    length = 4
    breadth = 5
    color = 0xFAFAFA
}

fun main() {
    val list = arrayOf(1, 2, 3);
    val positives = list.filter { x -> x > 0 }
    val positives2 = list.filter { it > 0 }

    var xxx = list.firstOrNull() ?: 4
    println(1 in positives2)
    println(1 !in positives2)

    // Instance Checks
    val x = null
    when (x) {
        is String -> println("String")
        is Any -> println("Object")
    }

    val map: Map<String, Int> = mapOf<String, Int>()
    for ((k, v) in map) {
        println("$k -> $v")
    }

    for (i in 1..100) {  }  // closed range: includes 100
    for (i in 1 until 100) {  } // half-open range: does not include 100
    for (x in 2..10 step 2) {  }
    for (x in 10 downTo 1) {  }
    if (x in 1..10) {  }


    val list2 = listOf("a", "b", "c")
    val map2 = mapOf("a" to 1, "b" to 2, "c" to 3)


    val map3 = mutableMapOf("a" to "1")
    println(map3["a"])
    map3["a"] = "value"

    "Convert this to camelcase".spaceToCamelCase()

    // If not null shorthand
    Resource?.name

    // If not null and else shorthand
    Resource?.name ?: ""

    // Executing a statement if null
    Resource.name ?: throw IllegalStateException("Email is missing!")

    // Execute if not null
    Resource.name?.let { }

    // Map nullable value if not null
    // defaultValue is returned if the value or the transform result is null.
    val value = ""
    val mapped = value?.let { useless(it) } ?: ""

    // Java 7's try with resources
    val stream = Files.newInputStream(Paths.get("/some/file.txt"))
    stream.buffered().reader().use { reader ->
        println(reader.readText())
    }

    // Consuming a nullable Boolean
    val b: Boolean? = true
    if (b == true) {

    } else {
        // `b` is false or null
    }

    // Swapping two variables
    var a = 1
    var bb = 2
    a = bb.also { bb = a }

}

// Convenient form for a generic function that requires the generic type information
class JsonElement
inline fun <reified T: Any>  Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)

// TODO(): Marking code as incomplete
fun calcTaxes(): BigDecimal = TODO("Waiting for feedback from accounting")











