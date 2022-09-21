package tour.clazz.enums

import kotlin.system.exitProcess

/** region Data */
enum class CurrencyType1 {
    USD, CNY, JPY, AUD, TWD
}

// (id, 中文名稱, 對臺幣粗略匯率)
enum class CurrencyType2(val chineseName: String,
                        val simpleExRate: Double) {
    USD("美元", 30.0),
    CNY("人民幣", 4.5),
    JPY("日圓", 0.3),
    AUD("澳幣", 20.0),
    TWD("臺幣", 1.0)
}

enum class CurrencyType(val chineseName: String,
                        val simpleExRate: Double) {
    USD("美元", 30.0),
    CNY("人民幣", 4.5),
    JPY("日圓", 0.3),
    AUD("澳幣", 20.0),
    TWD("臺幣", 1.0);

    override fun toString() = "$chineseName ${super.toString()}"
}

class CurrentAccount(val id: String,
                     val currencyType: CurrencyType,
                     var balance: Double) {
}
/** endregion */

/** region Demo */
fun enum11() {
    val ac1 = CurrentAccount("1", CurrencyType.USD, 3027.5)
    val ac2 = CurrentAccount("2", CurrencyType.CNY, 400.0)
    val ac3 = CurrentAccount("3", CurrencyType.JPY, 1800.0)

    val accounts = listOf(ac1, ac2, ac3)
    for (ac in accounts) {
        var info = ""

        info += "幣別：${ac.currencyType.chineseName}\n"
        info += "餘額：${ac.balance}\n"
        info += "換算臺幣：${ac.balance * ac.currencyType.simpleExRate}\n"

        println(info)
    }
}

fun enum12() {
    // 1. 「name」屬性代表列舉物件的名稱。
    // 2. 使用「valueOf」方法，可傳入字串，並取得名稱相符的物件（大小寫需相符）。若物件不存在，會拋出例外。
    // 3. 「toString」方法預設會回傳name屬性，可覆寫。

    // 取得名稱，印出：USD
    println(CurrencyType.USD.name)

    // 取得物件並比對，印出：true
    println(CurrencyType.valueOf("USD") == CurrencyType.USD)

    // 印出：美金 USD
    println(CurrencyType.USD.toString())
}

// 序數 ordinal
fun enum13() {
    // 列舉類別還內建另一個叫做「ordinal」的屬性，它代表的是該物件在類別中的順序，
    // 就像陣列與List的索引。例如，USD宣告在第一個，因此ordinal的值為0。依此類推，TWD的值為4。
    val ac1 = CurrentAccount("1", CurrencyType.AUD, 100.0)
    val ac2 = CurrentAccount("2", CurrencyType.USD, 200.0)
    val ac3 = CurrentAccount("3", CurrencyType.JPY, 300.0)
    val ac4 = CurrentAccount("4", CurrencyType.TWD, 400.0)
    val ac5 = CurrentAccount("5", CurrencyType.CNY, 500.0)

    val accounts = listOf(ac1, ac2, ac3, ac4, ac5)
        .sortedBy { it.currencyType.ordinal }

    for (ac in accounts) {
        println("${ac.currencyType.ordinal}. ${ac.currencyType.name}")
    }
}

fun enum14() {
    println("本程式支援的幣別如下：")

    val types: Array<CurrencyType> = CurrencyType.values()
    for (type in types) {
        println(type.chineseName)
    }
}

// 「valueOf」方法在無對應物件時，會拋出例外。
// 若想要設計一個類似的方法，卻不希望發生例外，可以在實作上利用「values」方法。
fun findCurrencyTypeByName(name: String): CurrencyType? {
    for (type in CurrencyType.values()) {
        if (type.name.equals(name, true)) {
            return type
        }
    }
    return null
}

/** endregion */

fun main() {
    while(true) {
        println(message = "Type number:")
        val option = readLine()

        when(option?.toInt()) {
            1 -> enum11()
            2 -> enum12()
            3 -> enum13()
            4 -> enum14()
            5 -> println(findCurrencyTypeByName("TWD"))
            else -> exitProcess(1)
        }

        println("======")
    }
}

// ref:
// - https://medium.com/chikuwa-tech-study/kotlin-%E7%AC%AC10%E8%AA%B2-%E5%88%97%E8%88%89-enum-4ef3a5001280