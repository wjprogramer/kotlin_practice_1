package tour.clazz.advanced_generic_type

/**
 * 不允許對泛型參數 T 做類型檢查，因為泛型參數類型會被「類型消除（type erasure）」
 * 也就是說，T 類型資訊在執行時期是不可知的
 */
//fun <T> randomOrBackupLoot(backupLoot: () -> T): T {
//    val items = listOf(Coin(14), Fedora("a fedora of the ages", 150))
//    val randomLoot: Loot = items.shuffled().first()
//    return if (randomLoot is T) {
//        randomLoot
//    } else {
//        backupLoot()
//    }
//}

/**
 * 然而，與 java 不同，kotlin 提供 reified 關鍵字，允許在執行時期保留類型資訊，
 * 現在 `randomLoot is T` 類型檢查沒有問題，因為已經保留他的類型資訊．亦即保留了通常會被抹除的泛型類型資訊，
 * 所以編譯器可以對泛型參數進行類型檢查
 */
inline fun <reified T> randomOrBackupLoot(backupLoot: () -> T): T {
    val items = listOf(Coin(14), Fedora("a fedora of the ages", 150))
    val randomLoot: Loot = items.shuffled().first()
    return if (randomLoot is T) {
        randomLoot
    } else {
        backupLoot()
    }
}

/**
 * 有了 reified 關鍵字，不需要藉由 「反射（reflection）」也能檢查泛型參數的類型
 * 反射是指在執行時期了解一個屬性或函數的名稱或類型，屬於一種開銷很大的操作
 */
fun main() {
    randomOrBackupLoot {
        Fedora("a backup fedora", 15)
    }.run {
        println(name)
    }
}

