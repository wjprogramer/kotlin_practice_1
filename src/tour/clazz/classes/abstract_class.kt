package tour.clazz.classes

/**
 * 抽象類別 （在中間）
 */

/**
 * 介面與抽象類別差異：
 * 一個類別只能繼承一個抽象類別，但可以實作多個介面
 * 介面不能定義建構函數
 */
interface  Fightable {
    val diceCount: Int
    val diceSides: Int
}

/**
 * ## 抽象類別
 *
 * 採用另一種方式，迫使其他類別進行一些具體的事情．無法產生一個抽象類別的實體
 * 使命就是提供函數實作
 *
 * 可以有具體函數，也可以包含抽象函數（只有定義、沒有內容）
 *
 * ---
 *
 * 經驗法則
 *
 * 需要一組公共物件行為或屬性，如果繼承行不通，改用介面
 * 如果繼承可行，但又不需要太具體的父類別，便採用抽象類別（如果還想產生父類別實體，就只能使用一般類別）
 */
// 沒必要實作全部 （自己實作或繼承）
abstract class Monster: Fightable {
    override val diceSides: Int = 6
}

// 要實作全部 （自己實作或繼承）
class Goblin: Monster() {
    override val diceCount: Int = 5
}

fun main() {

}

