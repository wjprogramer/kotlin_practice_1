package standard_fun.from_book

import java.io.File
import java.nio.file.Paths

private val healthPoints = (1..20).shuffled().last()

fun main() {

    val path = Paths.get("").toAbsolutePath().toString()
    val filePath = "$path/README.md"

    // # apply
    // this, return this
    File(filePath).apply {
        setReadable(true)
        setWritable(true)
        setExecutable(false)
    }
        .readText()
        .run(::println)

    // # let (it go)
    // it, return result
    listOf(1,2,3)
        .first()
        .let { it * it }
        .run(::println)

    // # run
    // this, return result
    "Madrigal"
        .run { length >= 20 }
        .run(::playerCreateMessage)
        .run(::println)

    // # also
    // it, return this
    File(filePath)
        .also {
            print(it.name)
        }.also {
            val fileContents = it.readLines()
            println(fileContents.size)
        }


    // -----------------

    // # takeIf
    // it, return this
    val fileContents1 = File(filePath)
        .takeIf { it.canRead() && it.canWrite() }
        ?.readText()


}

private fun playerCreateMessage(result: Boolean) = "name is ${if (result) "Long" else "Short"}"