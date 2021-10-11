package tour.variables.types

import models.Person
import kotlin.system.exitProcess
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

// Reified Type (具體型別)

// target:

// ```
// 原本: Post post = gson.fromJson(json, Post.class);
// 改成: Post post = gson.fromJson(json);
// ```

/** region 泛型  */
// 原本作法: 透過泛型推定型別(infer)
private fun useGeneric() {
    val personJson = """
    {
        "id": 1,
        "firstName": "Jay",
        "lastName": "Wu"
    }
    """.trimIndent()

    val person = fromJson(personJson, Person::class.java)
    println(person)
}

private fun <T> fromJson(json: String, klass: Class<T>): T {
    val jsonObj = Json.parseToJsonElement(json).jsonObject.entries
    val properties = mutableMapOf<String, Any?>()

    jsonObj.forEach {
        properties[it.key] = it.value
    }

    return klass
        .getDeclaredConstructor(Int::class.java, String::class.java, String::class.java)
        .newInstance(Integer.parseInt(
            properties["id"].toString()),
            properties["firstName"].toString().removePrefix("\"").removeSuffix("\""),
            properties["lastName"].toString().removePrefix("\"").removeSuffix("\"")
        )
}
/** endregion  */

/** region 具體型別  */
private fun useReified() {
    val utility = Utility()
    val personJson = """
    {
        "id": 1,
        "firstName": "Jay",
        "lastName": "Wu"
    }
    """.trimIndent()

    val person = utility.fromJson2<Person>(personJson)
    println(person)
}

private class Utility
private inline fun <reified T> Utility.fromJson2(json: String): T? {
    return fromJson(json, T::class.java)
}
/** endregion  */

fun main() {
    while(true) {
        println("Type number:")
        val option = readLine()

        when(option?.toInt()) {
            1 -> useGeneric()
            2 -> useReified()
            else -> exitProcess(1)
        }

        println("======")
    }
}
// ref:
// https://medium.com/@yongjhih/reified-type-%E5%8F%AF%E5%BB%BA%E6%A7%8B%E5%9E%8B%E5%88%A5-kotlin-46a8b7e848dd