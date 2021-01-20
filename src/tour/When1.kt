package tour

import kotlin.random.Random.Default.nextBoolean
import kotlin.system.exitProcess

// 只要有 if else 都建議用 when

fun when11(obj: Any): String =
    when (obj) {
        1          -> "One"
        "Hello"    -> "Greeting"
        is Long    -> "Long"
        !is String -> "Not a string"
        else       -> "Unknown"
    }

fun when12() {
    // #1
    when (val number = (1..7).random()) {
        1 -> println("Number is 1")
        2 -> println("Number is 2")
        3 -> {
            repeat(number) { index ->
                println("$index = print this line three times.")
            }
        }
        else -> println("Other")
    }

    // #2
    val pName = when (val number = (1..7).random()) {
        1 -> "Popsicle"
        2 -> "Pancake"
        3 -> "Pistachio"
        4 -> {
            // do some logic
            "I guess Pumpkin Pie"    // <-- final line return
        }
        else -> "Whatever pie"
    }

    // #3

    val n = when (val number = (1..7).random()) {
        in 1..3 -> "in 1~3"
        4 -> if (nextBoolean()) {
            "true"
        } else {
            "false"
        }
        else -> "out of 1~3"
    }

    println("Android P will call $pName")
}

private enum class UiState {
    PREPARING, READY, EMPTY, ERROR
}

private fun printState(state: UiState) {
    println("==== ==== printing ==== ====")
    when (state) {
        UiState.EMPTY, UiState.READY -> {
            if (state == UiState.EMPTY) {
                println("State = $state , loaded data is empty.")
            }
            println("State = $state , ui is ready.")
        }
        UiState.ERROR, UiState.PREPARING -> println("State = $state , UI is preparing or error")
    }
}

private fun whatItIs(any: Any) = when (any) {
    is String -> {
        val firstLetter = any.first()        // smart cast, any is String now
        println("It seems like words, it is $any and the first word is $firstLetter")
    }
    is Int -> {
        val doubleValue = any * 2        // smart cast, any is Int now
        println("It seems like a number, $any and multiply 2 is $doubleValue")
    }
    else -> println("Whatever")
}

fun checkNumberRange(number: Int) {
    when (number) {
        in 1..25 -> {
            // Number in range between 1 and 25
            println("$number is Not big enough!")
        }
        in 26..40 -> {
            // Number in range between 26 and 40
            println("$number is umm... not big or small")
        }
        !in 1..40 -> {
            // Number is NOT in ragne between 1 to 40.
            // So 41 to 99 enter here
            println("$number is quite LARGE!")
        }
    }
}

fun checkNumberIsEvenOrOdd(number: Int) {
    fun Int.isEven(): Boolean {
        return this % 2 == 0
    }

    fun Int.isOdd(): Boolean {
        return this % 2 != 0
    }

    when {
        number.isEven() -> println("$number , This is an even number.")
        number.isOdd() -> println("$number, This is an odd number. ")
        else -> println("$number, This is what??")
    }
}

fun main() {
    while(true) {
        print("Type number: ")

        val option = readLine() ?: exitProcess(1)

        println("======")

        when(option.toInt()) {
            1 -> when11(2)
            2 -> when12()
            3 -> printState(UiState.EMPTY)
            4 -> whatItIs("Hello")
            5 -> whatItIs(12)
            6 -> checkNumberRange(5)
            7 -> checkNumberIsEvenOrOdd(2)
            else -> exitProcess(1)
        }

        println("======")
    }
}

// ref:
// - https://medium.com/@louis383/kotlin-when-e383d642ac79