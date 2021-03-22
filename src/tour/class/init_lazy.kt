package tour.`class`

import java.io.File

/**
 * 惰性初始化
 *
 * 如果某個屬性初始化時，會產生很多實體物件、涉及讀取檔案之類的密集型任務
 * 且暫時沒有其他類別會用到該屬性，可以使用惰性初始化
 *
 * 惰性初始化是透過一種叫作「代理」的機制來實作
 * 代理負責約定該如何初始化屬性
 *
 * 使用代理要加上 by 關鍵字
 * 而代理有一些 kotlin 內建的，例如：`lazy`
 *
 */
private fun demo() {
    val c = C()
    println(c.hometown)
}

private class C {

    val hometown by lazy { selectHometown() }

    private fun selectHometown() = File("data/towns.txt")
        .readText()
        .split("\n")
        .shuffled()
        .first()

}

fun main() {
    demo()
}