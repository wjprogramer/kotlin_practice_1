package tour

/**
 * 函式程式設計
 * 「轉換 transform, 過濾 filter, 合併 combine」
 */

private fun transformMapDemo() {
    val animals = listOf("zebra", "giraffe", "elephant", "rat")
    val babies = animals
        .map { animal -> "A baby $animal" }
        .map { baby -> "$baby, with the cutest little tail ever!" }
    println(babies)
}

private fun transformFlatMapDemo() {
    val itemsOfManyColors = listOf(
        listOf("red apple", "green apple", "blue apple"),
        listOf("red fish", "blue fish"),
        listOf("yellow banana", "teal banana")
    )
    val redItems = itemsOfManyColors.flatMap { it.filter { items -> items.contains("red") } }
    println(redItems)
}

private fun filterDemo() {
    val numbers = listOf(7, 4, 8, 4, 3, 22, 18, 11)
    val primes = numbers.filter { number ->
        (2 until number)
            .map { number % it }
            .none { it == 0 }
    }
    println(primes)
}

private fun combineZipDemo() {
    val employees = listOf("Denny", "Claudette", "Peter")
    val shirtSize = listOf("large", "x-large", "medium")
    val employeeShirtSizes = employees.zip(shirtSize).toMap()
    println(employeeShirtSizes["Denny"])
}

private fun combineFoldDemo() {
    val foldedValue = listOf(1, 2, 3, 4).fold(0) { accumulator, number ->
        println("Accumulated value: $accumulator")
        accumulator + (number * 3)
    }
    println("Final value: $foldedValue")
}

fun main() {
    transformMapDemo()
    transformFlatMapDemo()

    filterDemo()

    combineZipDemo()
    combineFoldDemo()
}