package standard_fun

import models.GoodSmartPhone
import kotlin.system.exitProcess

// `with` 一般常常作為初始化時使用， `with(T)` 之中的傳入值可以以 `this` (稱作 identifier) 在 scope 中取用，
// 不用打出 `this` 也沒關係。
// 雖然， with 也會 **將最後一行回傳**，
// 但目前看起來大部分還是只用它來做初始化。
// 透過 `with()` 很明確知道是為了括弧中的變數進行設定。

fun with1() {
    val greatSmartphone = GoodSmartPhone()

    with (greatSmartphone) {
        this.setCleanSystemInterface(true)

        // `this` is not necessary
        setGreatBatteryLife(true)
        setGreatBuildQuality(true)
    }
}

// 但很多使用狀況，變數可能是可為空的變數，
// 如此一來 with的 scope 中就必須要宣告 「?」或「!!」來取用該物件的方法 (Method)。
fun with2(goodSmartPhone: GoodSmartPhone?) {
    with(goodSmartPhone) {
        this?.setCleanSystemInterface(true)
        this?.setGreatBatteryLife(true)
        this?.setGreatBuildQuality(true)
    }
}


fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> with1()
            2 -> with2(null)
            else -> exitProcess(1)
        }

        println("======")
    }
}