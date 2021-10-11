package tour.clazz.classes

data class Fish(
    val name: String,
    val age: Int
) {
    val length: Int = 10 * age
}

class FishMarket(val name: String, val price: Int) {
    operator fun component1() = name
    operator fun component2() = price
}

fun main() {
    val market = FishMarket("First Market", 100)
    val (marketName, marketPrice) = market
    println("market info: $marketName, $marketPrice")

    val fish = Fish("鮭", 20)
    val (fishName, fishAge) = fish
    println("fish info: $fishName, $fishAge")
}