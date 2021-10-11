package tour.function.standard_fun

import models.GoodSmartPhone
import kotlin.system.exitProcess

data class Laptop(var maker: String, var model: String, var system: String)

// 因為 `run` 有兩種用法，這裡為了避免混淆而將 T 寫出來。
//
// T.run 也能像 `with` 一樣來做初始化，而且 extension function 有個好處是可以在使用時就進行 「?」 或 「!!」 的宣告。
// 另外，T 能夠以 `this` 的形式在 scope 內取用。像是上面的範例，如果用 `T.run` 來 做初始化，就會是：
fun tRun11(goodSmartPhone: GoodSmartPhone?) {
    goodSmartPhone?.run {
        this.setCleanSystemInterface(true)

        // `this` is not necessary
        setGreatBatteryLife(true)
        setGreatBuildQuality(true)
    }
}

// 當然如果傳進來的變數是空值，`T.run{}` 內的程式碼就根本不會執行了。
// 除此之外， `T.run` 和 `run` 的特性完全一樣。可以將最後一行的東西回傳，或是傳給下一個 chain。
// 參考以下範例，要根據筆電系統版本印出 Windows 的開發代號：
fun tRun12() {
    val laptopA = Laptop("Dell", "XPS 13 9343c", "Windows 8.1")
    val laptopB = Laptop("Lenovo", "T420s", "Windows 7")
    val laptopC = Laptop("MSI", "GS65 Stealth", "Windows 10")

    fun printWindowsCodeName(laptop: Laptop?) {
        val codename = laptop?.run {
            // `this` is Laptop.
            // `this` can ignore when use fields and methods
            system.split(" ")    // <-- pass to next chain
        }?.run {
            // `this` is the split strings. a List<String>
            val result = when (this.last()) {
                "7" -> "Blackcomb"
                "8" -> "Milestone"
                "8.1" -> "Blue"
                "10" -> "Threshold"
                else -> "Windows 9"
            }
            result    //  <-- pass value back
        }

        println("${laptop?.system} codename is $codename")
    }

    printWindowsCodeName(laptopA)
    printWindowsCodeName(laptopB)
    printWindowsCodeName(laptopC)
}



fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine() ?: exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> tRun11(null)
            2 -> tRun12()
            else -> exitProcess(1)
        }

        println("======")
    }
}