package tour.function

/**
 * 擴充屬性
 *
 * 和計算屬性一樣，也沒支援欄位 fields
 * 因此，一個有效的擴充屬性必須自行定義 get 和 set 函數，以計算出應該回傳的屬性值
 */
private val String.numVowels
    get() = count { "aeiouy".contains(it) }


// Error
//var String.preferredCharacters = 10

/**
 * 可空類別擴充
 */
private infix fun String?.printWithDefault(default: String) = println(this ?: default)


private fun <T> Iterable<T>.random(): T = this.shuffled().first()

// ## use (if public)
// import xxx.random
// or
// import xxx.random as randomizer // 重新命名

fun main() {
    "How many vowels?".numVowels.run { println(this) }

     val nullableString: String? = null
    nullableString printWithDefault "Default string"
    nullableString.printWithDefault("Default string")
}