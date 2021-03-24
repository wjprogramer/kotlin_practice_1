package tour.advanced_generic_type

/**
 * 泛型參數：扮演兩種角色
 * 1. 「生產者」：可讀不可寫
 * 2. 「消費者」：可寫不能讀
 */

/**
 * out 代表泛型參數扮演可讀不能寫的生產者，也就是不能再以 var 關鍵字定義 item，
 * 否則他不僅是 Fedora 的生產者，還是可寫的消費者
 */
class Barrel<out T>(val item: T)

class BarrelConsumer<in T>(item: T)

fun main() {
    println("\n--- PRODUCER ---")

    val fedoraBarrel: Barrel<Fedora> = Barrel(Fedora("a generic-looking fedora", 15))
    var lootBarrel: Barrel<Loot> = Barrel(Coin(15))
    println(lootBarrel.item.value)

    lootBarrel = fedoraBarrel
    println(lootBarrel.item.value)
    val myFedora: Fedora = lootBarrel.item

    // ----

    println("\n--- CONSUMER ---")

    var fedoraBarrel2: BarrelConsumer<Fedora> = BarrelConsumer(Fedora("a generic-looking fedora", 15))
    val lootBarrel2: BarrelConsumer<Loot> = BarrelConsumer(Coin(15))
//    println(lootBarrel2.item.value)

//    lootBarrel2 = fedoraBarrel2
    fedoraBarrel2 = lootBarrel2
//    println(fedoraBarrel2.item.value)
//    val myFedora2: Fedora = fedoraBarrel2.item
}