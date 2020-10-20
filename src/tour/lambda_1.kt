package tour

import kotlin.system.exitProcess

// Kotlin 裡面，大括號表示的範圍都可以視作一個 Function。
// 例如一般 JVM 程式的進入點 main(argv Array<String>) 是一個 Function。
// 在某一個 Function 裡頭再執行另一個 Function，
// Lambda 表達式常用來表示這種 Function 內的其他內部 Function。

/** region Higher-Order Functions */

// 在 Kotlin 之中可以將 Function 作為變數類型傳遞。
// 換句話說，Function 的地位與其他的 String、Collections、SomeObject 相同，
// 可以用來記錄狀態，同時也能將狀態傳遞給其他人，也能作為回傳值。
private fun functionType() {
    // Function Type 是一種 Function 的變數類型。
    // ```
    // var action: (A, B) -> ReturnType
    // ```

    // 括弧內的傳入值可以不傳入任何變數，並且可以傳入最多 22 個變數(Kotlin 1.2 版與之前，
    // Kotlin 1.3 版後可以傳入最多 255 個變數 😅)

    // 不要回傳任何變數， 可以透過 `-> Unit` 即不會回傳。

    val plus: (Int, Int) -> Int = { firstNumber, secondNumber ->
        firstNumber + secondNumber
    }

    println("2 + 3 ,result is ${plus(2, 3)}")

    // 會自動辨別變數類型，因此也可以寫成：
    val plus2 = {firstNumber: Int, secondNumber: Int ->
        firstNumber + secondNumber
    }
}
/** endregion */

/** region 基本用法 */
private fun x(x1: (Int, Int) -> Int, x2: Int) {
    x1(1, 2)
    println(x2)
}

private fun lambdaBasic1() {
    x({n1, n2 ->
        println("$n1, $n2")
        n1 + n2
    }, 2)
}
/** endregion */

/** region 基本用法：如果 function type 的變數是在 `最後一個` */
// Kotlin 只要最後一個傳入變數 (最右邊) 是 Function Type，就可以搬到括弧外面，自成一個 Scope。

private fun findNumber(list: List<Int>, findByCondition: (Int, Int) -> Int): Int {
    if (list.isNotEmpty()) {
        var targetNumber = list.first()
        for (i in 1 until list.size) {
            targetNumber = findByCondition(targetNumber, list[i])
        }
        return targetNumber
    }

    return -1
}

private fun lambdaBasic2() {
    val randomNumbers = List(5) { (0..9).random() }

    val largestNumber = findNumber(randomNumbers) { largestNumber, currentNumber ->
        if (currentNumber > largestNumber) {
            // Lambda 回傳最後一行
            currentNumber
        } else {
            // Lambda 回傳最後一行
            largestNumber
        }
    }

    val smallestNumber = findNumber(randomNumbers, fun(targetNumber, currentNumber): Int {
        return if (currentNumber < targetNumber) {
            currentNumber
        } else {
            targetNumber
        }
    })
}
/** endregion */

private fun itOfLambda() {
    val randomNumbers = List(10) { (0..100).random() }

    // 只要 Lambda 表達式，傳入的變數只有一個，那麼就可以使用 it 來存取
    val result = randomNumbers.count { number -> number >= 50 }
    val result2 = randomNumbers.count { it >= 50 }

    println("$result numbers in result is larger than 50.")
    println("$result2 numbers in result is larger than 50.")
}

// 還能透過匿名函數 (Anonymous Function)，做出類似 Extension Function 的效果
private fun lambdaForExtensionFunction() {
    val surroundBy = fun String.(symbol: String) = symbol + this + symbol
    val sampleWords = "surrounded"
    println("This is a ${sampleWords.surroundBy("!!")} word.")
}

/** region example */

// 常用情景：有時需要保證一些程式碼跑在...版本以上
private fun runAboveVersion(action: () -> Unit) {
    val buildVersionSdk = 20
    val targetVersionCode = 20

    if (buildVersionSdk >= targetVersionCode) {
        action()
    }
}

private fun doSomethingAboveVersion() {
    runAboveVersion {
        // do something
    }
}

/** endregion */

// ## 與 Java
// 此外， Java 的 interface 像 View.OnClickListener ，
// 只有一個 Method —— onClick(View view) 。
// 在 Kotlin 中使用時，可以轉換成 Lambda 表達式來用
// (這行為叫 SAM conversions ，SAM: single abstract method )。

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine() ?: exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> functionType()
            2 -> lambdaBasic1()
            3 -> lambdaBasic2()
            4 -> itOfLambda()
            5 -> lambdaForExtensionFunction()
            6 -> doSomethingAboveVersion()
            else -> exitProcess(1)
        }

        println("======")
    }
}

// ref:
// - https://medium.com/@louis383/%E5%88%9D%E6%8E%A2-kotlin-lambda-%E8%A1%A8%E9%81%94%E5%BC%8F-cfe8796c9fac