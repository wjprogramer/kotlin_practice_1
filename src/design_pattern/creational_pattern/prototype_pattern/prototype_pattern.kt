// ref:
// https://zh.wikipedia.org/wiki/%E5%8E%9F%E5%9E%8B%E6%A8%A1%E5%BC%8F
package design_pattern.creational_pattern.prototype_pattern

/** Prototype Class  */
open class Cookie : Cloneable {
    var name = ""

    @Throws(CloneNotSupportedException::class)
    public override fun clone(): Any {
        // In an actual implementation of this pattern you would now attach references to
        // the expensive to produce parts from the copies that are held inside the prototype.
        return super.clone()
    }
}

/** Concrete Prototypes to clone  */
class CoconutCookie : Cookie()

/** Client Class */
class CookieMachine(
    // cookie 必须是可複製的
    private val cookie: Cookie
) {
    fun makeCookie(): Cookie? {
        try {
            return cookie.clone() as Cookie
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }
        return null
    }
}

fun main(args: Array<String>) {

    val prototype: Cookie = CoconutCookie()
    prototype.name = "Coconut"

    val cm = CookieMachine(prototype) //设置原型

    var tempCookie: Cookie? = null
    tempCookie = prototype
    println("name: ${tempCookie?.name}, same: ${tempCookie == prototype}")

    for (i in 0..2) {
        // 通过复制原型返回多个 cookie
        tempCookie = cm.makeCookie()
        println("name: ${tempCookie?.name}, same: ${tempCookie == prototype}")
    }
}