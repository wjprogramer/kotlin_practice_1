# Tour

## also

原文：[`swap`，從不會到略懂 `a = b.also{b = a}`](https://medium.com/@spff/swap-%E5%BE%9E%E4%B8%8D%E6%9C%83%E5%88%B0%E7%95%A5%E6%87%82-a-b-also-b-a-5bf6f7001cac)

如同 C 的 `a = i++` 其實是 `(t = i;i = i+1;t)`，這裡的 `a = b.also{b = a}` 實際上也可以視作 `(t = b;b = a;t)` ，然後把回傳的值 assign 回`a`
also的簽名如下 `public inline fun <T> T.also(block: (T) -> Unit): T`

This is a generic inline extension function which takes a function which returns Unit as input and return T
名詞解釋

[以下是官網的解釋](https://kotlinlang.org/docs/reference/lambdas.html#lambda-expressions-and-anonymous-functions)

A lambda expression or an anonymous function is a “function literal”, i.e. a function that is not declared, but passed immediately as an expression.

Function可以被當成參數餵給其他function或是作為return，也就是所謂 [first-class function](https://en.wikipedia.org/wiki/First-class_function) 的概念。Function是一個interface，lambda expression是一種Function，長成用大括號包起來的樣子`{something()}`。
BTW在C++裡面lambda, function object, function reference講的是不同的事情~~~

### Extension function

Extension function很有趣，在官網有非常簡單易懂的介紹。

如果你幫Int寫了一個extension function `fun Int.test1(float: Float)`，
實際上在JVM層會變成`fun test1(int: Int, float: Float)` 。
詳見[heimashi/kotlin_tips的tip4](https://github.com/heimashi/kotlin_tips#tip4--%E6%89%A9%E5%B1%95%E5%87%BD%E6%95%B0%E5%92%8C%E5%B1%9E%E6%80%A7) 。

值得一提的是如果 `fun Int.test2()` 和 `fun test2(int: Int)` 同時存在會出錯，原因就是JVM層級的function簽名一樣。根據這點如果我們只寫了`fun Int.test2()`，已知背後會轉成`fun test2(int: Int)`，那是否可以直接呼叫`test2(5)`呢? __答：在Kotlin不行，不過從其他Java來call Kotlin的時候可以__ 。

### Generic

不解釋。

### Input(Argument)

Kotlin的變數/常數宣告方式： `var`(mutable)/`val`(immutable)`: Type`

- 先來解釋一下Kotlin的參數傳遞。Kotlin是JVM語言，它傳non-primitive的reference其實是用類似pointer的那套而非C++那種reference，[這裡](https://openhome.cc/Gossip/Java/ArrayCopy.html) 有Java這套清楚的圖解。
- Kotlin官方在2013年以後規定function的input是`val`，也就是這個(primitive type)的值或是 (non-primitive)所指到的”地方”(而非指到的地方的”內容”)不能改。假如寫`fun test3(int: Int){int = 5}` 就會出錯因為這裡的`int`是`val`。

所以 `T.also` 的input是一個 `val`，名字叫做`block`，其型態是一個`Function`，這個`Function`輸入參數是`T`，回傳是`Unit`。

Kotlin沒有`void`而是`Unit`。任何沒表明回傳的function[都視同回傳`Unit`](https://kotlinlang.org/docs/reference/functions.html#unit-returning-functions)，所以其實任何function都會有return。要注意的是用作lambda的時候必須寫`-> Unit`，要不然會被視作一個`Object`而非`Function`然後就會跳error說無法invoke。

`Unit`是一個class，繼承自`Any`，而且屬於`object class`，也就是singleton，(如此一來不用每次都new出一個`Unit`浪費記憶體)，然後`.toString()`會回傳`”Kotlin.Unit”` ，詳見[Nothing (else) matters in Kotlin](https://proandroiddev.com/nothing-else-matters-in-kotlin-994a9ef106fc) 。非常推薦這篇文章，從`Unit`開始說起，接著提到`Nothing`和`null`, Kotlin與Java中`void`的關係。精采絕倫！
先來回頭看`a = b.also{ b = a}`

它其實是`a = b.also({b=a})` 。
當lambda是最後一個參數的時候可以拉出來變成`a = b.also(){b = a}`
最後如果`()`裡頭沒東西則省略`a = b.also{b = a}`

這個`{b = a}`到底是什麼呢? 其實它是`fun anonymous(it: T): Unit{ b = a}`。 變成一個lambda的時候會長這樣 `{it: T -> b = a}`。
通常input型態已經被外面規定好了，例如我們要餵給also，它已經規定`block`是一個`Function`接受一個T型態的input然後回傳`Unit`。`(block: (T) -> Unit)`，所以可以省略成`{it -> b = a}`。

這裡的變數名稱`it`是可以隨便取的。 由於我們沒用到這個`it`，所以會得到一個warning說可以改成`{_-> b=a}`，這是為了當你有一堆不會用到的參數所有不會用到的都可以直接寫成_而不用去逐一取名。

然後我們又更懶了一點，由於只有一個參數所以如果不寫的話它會自[動獲得it這個變數名稱](https://kotlinlang.org/docs/reference/lambdas.html#it-implicit-name-of-a-single-parameter) ，`{b = a}`然後在這裡我們沒用到它。

### Inline

每個語言的inline主要都是為了加速，其他用途以及進階玩法可以看官方介紹，簡單加速說明可以參考這邊：[when to use an inline function in Kotlin](https://stackoverflow.com/questions/44471284/when-to-use-an-inline-function-in-kotlin)?

also裡面到底做了什麼事

```kotlin
public inline fun <T> T.also(block: (T) -> Unit): T {
contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
block(this)
return this
}
```

抱歉，`contract`那邊還沒研究，不過大致是在設定接下來呼叫`block(this)`的一些行為規範(歡迎補充) 所以說`also`其實也就是把傳過來的`Function`設定好以後呼叫它然後再`return this`。

所以說swap到底怎麼辦到的？

展開成 `b.also({it -> b = a})` 較好理解，重點是生成`Function`的行為。

藉由把編好的JVM byte code給disassemble來觀察，我的發現是(歡迎糾正、補充)，其實是把b(primitive)或是`b`這個Pointer(non-primitive)複製一份給`it`(而且它是val，不能修改的) 進去以後`b = a`，然後執行完這個lambda以後also會回傳`it`，最後把它assign給a 也就完成swap的動作了 咦！突然發現如果是non-primitive type其實這個swap是shallow swap呢！可以把這坨丟到[這邊](https://try.kotlinlang.org/#/Examples/Hello,%20world!/Simplest%20version/Simplest%20version.kt) 跑來驗證是shallow swap。

```kotlin
fun main(args: Array<String>) {
    var a = A()
    var b = A()
    val c = b
    b. variable = 5
    println("${a.variable} ${b.variable} ${c.variable}")
    a = b.myalso { b = a}
    println("${a.variable} ${b.variable} ${c.variable}")
}

class A( var variable: Int = 2 )

fun A.myAlso(block: (A) -> Unit): A {
    block(this)
    return this
}
```

而更多的細節必須去挖掘closure(閉包)和lambda來更好地理解。

看到這邊，只要學會 **swap** ，少少幾個字 **a = b.also{b = a}** ，你就學會 **extension functions, inline, generic, higher-order functions, lambda, Unit** 了呢！

BTW higer-order functions除了`also`，還有一堆，詳見這篇好文 [Kotlin 的 with、apply、let 等函数的用处](https://blog.cat73.org/2017/12/09/kotlin-standard/)。

舉個例子`fun <T, R> T.run(block: T.() -> R): R`你要傳給他的`block`就會自動變成`T`的extension function，這個時候在`block`裡面就可以用`this`來獲取這個T的instance。這個`block`是一個function takes no input argument 然後回傳的任意型態會直接被`run`給return。

我開始覺得這真適合當作殘害脆弱小生命的面談的題目了(抖
**請用also (簽名：public inline fun <T> T.also(block: (T) -> Unit): T)寫個shallow swap並解釋**













