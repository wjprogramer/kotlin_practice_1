package tour

import kotlin.system.exitProcess

// # Operator Overload 運算子多載
//
// ref:
// - 中：https://www.kotlincn.net/docs/reference/operator-overloading.html
// - 英：https://kotlinlang.org/docs/reference/operator-overloading.html
// - https://www.codingame.com/playgrounds/6847/kotlin-operator-overloading

// ## 一元操作 Unary operations
//
// 運萬子有很多種 `+`, `*` 等，以及固定的優先順序
//
// ### 一元前綴運算子 Unary prefix operators
//
// | Expression       | Translated to   |
// | ---------------- | --------------- |
// | +a               | a.unaryPlus()   |
// | -a               | a.unaryMinus()  |
// | !a               | a.not()         |

// 這個表是說，當編譯器處理例如表達式+a時，它執行以下步驟：
//
// - 確定 `a` 的類型，令其為 `T`；
// - 為接收者 `T` 查找一個帶有 `operator` 修飾符的無參函數 `unaryPlus（）`，即成員函數或擴展函數；
// - 如果函數不存在或不明確，則導致編譯錯誤；
// - 如果函數存在且其返回類型為 `R`，那就表達式 `+a` 具有類型 `R`；

// 注意這些操作以及所有其他操作都針對基本類型做了優化，不會為它們引入函數調用的開銷。

private data class Point(var x: Int, var y: Int)

private fun unaryPrefixOperators() {

    operator fun Point.unaryMinus() = Point(-x, -y)

    val point = Point(10, 20)

    println(-point)
}

// ### 遞增、遞減

// | Expression   | Translated to       |
// | ------------ | ------------------- |
// | a++ 	      | a.inc() + see below |
// | a-- 	      | a.dec() + see below |

// `inc()` 和 `dec()` 函數必須返回一個值，它用於賦值給使用 `++` 或 `--` 操作的變量。
// 它們不應該改變在其上調用 `inc()` 或 `dec()` 的對象。

// 編譯器執行以下步驟來解析後綴形式的運算子，例如 `a++`：

// - 確定 `a` 的類型，令其為 `T`；
// - 查找一個適用於類型為 `T` 的接收者的、帶有 `operator` 修飾符的無參數函數 `inc()`；
// - 檢測函數的返回類型是 `T` 的子類型。

// 計算表達式的步驟是：

// - 把 `a` 的初始值存儲到臨時存儲 `a0` 中；
// - 把 `a0.inc()` 結果賦值給 `a`；
// - 把 `a0` 作為表達式的結果返回。

// 對於 `a--`，步驟是完全類似的。

// 對於前綴形式 `++a` 和 `--a` 以相同方式解析，其步驟是：

// - 把 `a.inc()` 結果賦值給 `a`；
// - 把 `a` 的新值作為表達式結果返回。

private fun increments() {

    operator fun Point.inc() = Point(x + 2, y + 2)

    var point = Point(10, 20)

    point++

    println(point)

}

private data class Point2(val x: Double, val y: Double) {
    operator fun plus(p: Point2) = Point2(x + p.x, y + p.y)
    operator fun minus(p: Point2) = Point2(x - p.x, y - p.y)
    operator fun times(p: Point2) = Point2(x * p.x, y * p.y)
    operator fun div(p: Point2) = Point2(x / p.x, y / p.y)
    operator fun inc() = Point2(x + 1, y + 1)
    operator fun dec() = Point2(x - 1, y - 1)
}

private fun conclusion() {
    val running: (String, String, String) -> String = {first, op, second -> "Running $first $op $second = "}

    var one = Point2(2.0, 3.0)
    var two = Point2(3.0, 2.0)

    println(running(one.toString(), "+", two.toString()) + (one + two))
    println(running(one.toString(), "-", two.toString()) + (one - two))
    println(running(one.toString(), "*", two.toString()) + (one * two))
    println(running(one.toString(), "/", two.toString()) + (one * two))
    println(running(one.toString(), "++", "") + (++one))
    println(running(two.toString(), "--", "") + (--two))
}

// ## 二元操作 Binary operations

// ### 算術運算符 Arithmetic operators

// | Expression | Translated to                       |
// | ---------- | ----------------------------------- |
// | a + b      | a.plus(b)                           |
// | a - b      | a.minus(b)                          |
// | a * b      | a.times(b)                          |
// | a / b      | a.div(b)                            |
// | a % b      | a.rem(b), a.mod(b) (deprecated)     |
// | a..b       | a.rangeTo(b)                        |

// 對於此表中的操作，編譯器只是解析成翻譯為列中的表達式。

// 請注意，自Kotlin 1.1起支持rem運算符。Kotlin 1.0使用mod運算符，它在Kotlin 1.1中被棄用。

private data class Counter(val dayIndex: Int) {
    operator fun plus(increment: Int): Counter {
        return Counter(dayIndex + increment)
    }
}

// ### 'In' operator

// | Expression 	| Translated to       |
// | -------------- | ------------------- |
// | a in b 	    | b.contains(a)       |
// | a !in b 	    | !b.contains(a)      |

// 對於 `in` 和 `!in`，過程是相同的，但是參數的順序是相反的。

// ### 索引訪問運算子 Indexed access operator

// | Expression 	     | Translated to            |
// | ------------------- | ------------------------ |
// | a[i]	             | a.get(i)                 |
// | a[i, j]	         | a.get(i, j)              |
// | a[i_1, ……, i_n]     | a.get(i_1, ……, i_n)      |
// | a[i] = b	         | a.set(i, b)              |
// | a[i, j] = b	     | a.set(i, j, b)           |
// | a[i_1, ……, i_n] = b | a.set(i_1, ……, i_n, b)   |

// 方括號轉換為調用帶有適當數量參數的get和set。

// ### 調用運算子 Invoke operator

// | Expression 	 | Translated to             |
// | --------------- | ------------------------- |
// | a()	         | a.invoke()                |
// | a(i)	         | a.invoke(i)               |
// | a(i, j)	     | a.invoke(i, j)            |
// | a(i_1, ……, i_n) | a.invoke(i_1, ……, i_n)    |

// 圓括號轉換為調用帶有適當數量參數的invoke。

// ### 廣義賦值 Augmented assignments

// | Expression 	| Translated to                             |
// | -------------- | ----------------------------------------- |
// | a += b         | a.plusAssign(b)                           |
// | a -= b         | a.minusAssign(b)                          |
// | a *= b         | a.timesAssign(b)                          |
// | a /= b         | a.divAssign(b)                            |
// | a %= b	        | a.remAssign(b), a.modAssign(b)（已棄用）    |

// 對於賦值操作，例如 `a += b`，編譯器執行以下步驟：

// - 如果右列的函數可用
//      - 如果相應的二元函數（即plusAssign()對應於plus()）也可用，那麼報告錯誤（模糊），
//      - 確保其返回類型是 Unit，否則報告錯誤，
//      - 生成 `a.plusAssign(b)` 的程式碼；
// - 否則試著生成a = a + b 的程式碼（這裡包含類型檢測：a + b的類型必須是a的子類型）。
// 注意：賦值在Kotlin中不是表達式。

// ### 相等與不等運算子 Equality and inequality operators

// | Expression 	| Translated to                     |
// | -------------- | --------------------------------- |
// | a == b	        | a?.equals(b) ?: (b === null)      |
// | a != b	        | !(a?.equals(b) ?: (b === null))   |

// 這些運算子只使用函數equals(other: Any?): Boolean，可以覆蓋它來提供自定義的相等性檢測實現。不會調用任何其他同名函數（如equals(other: Foo)）。
//
// 注意：===和!==（同一性檢測）不可重載，因此不存在對他們的約定。
//
// 這個==運算子有些特殊：它被翻譯成一個複雜的表達式，用於篩選null值。 null == null 總是true，對於非空的x，x == null總是false而不會調用x.equals()。

// ### 比較運算子 Comparison operators

// | Expression 	| Translated to       |
// | -------------- | ------------------- |
// | a > b	        | a.compareTo(b) > 0  |
// | a < b	        | a.compareTo(b) < 0  |
// | a >= b	        | a.compareTo(b) >= 0 |
// | a <= b	        | a.compareTo(b) <= 0 |

// 所有的比較都轉換為對compareTo的調用，這個函數需要返回Int值

// ### 屬性委託運算子 Property delegation operators
// provideDelegate、getValue以及setValue運算子函數已在委託屬性中描述。

// ### 具名函數的中綴調用 Infix calls for named functions
// 我們可以通過中綴函數的調用來模擬自定義中綴運算子。(https://kotlinlang.org/docs/reference/functions.html#infix-notation)

/** region */
/** endregion */

/** region */
/** endregion */

/** region */
/** endregion */

/** region */
/** endregion */

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine() ?: exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> unaryPrefixOperators()
            2 -> increments()
            10 -> conclusion()
            else -> exitProcess(1)
        }

        println("======")
    }
}