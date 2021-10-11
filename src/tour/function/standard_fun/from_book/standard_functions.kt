package tour.function.standard_fun.from_book

import java.io.File
import java.nio.file.Paths

private val healthPoints = (1..20).shuffled().last()

fun main() {

    val path = Paths.get("").toAbsolutePath().toString()
    val filePath = "$path/README.md"

    // # apply
    // no use
    val menuFile1 = File(filePath)
    menuFile1.setReadable(true)
    menuFile1.setWritable(true)
    menuFile1.setExecutable(false)
    // use apply
    val menuFile2 = File(filePath).apply {
        setReadable(true)
        setWritable(true)
        setExecutable(false)
    }
    println(menuFile2.readText())

    // # let
    // no use let
    val firstElement1 = listOf(1,2,3).first()
    val firstItemSquared1 = firstElement1 * firstElement1
    // use let
    val firstItemSquared2 = listOf(1,2,3).first().let { it * it }

    // =>
    // apply:   回傳接收者(this)本身
    // let:     回傳最後一行 (lambda 結果)

    // # run
    // use run
    val menuFileRun = File(filePath)
    val servesDragonBreath = menuFileRun.run {
        readText().contains("Dragon's Breath")
    }

    "Madrigal".run(::nameIsLong)

    "Madrigal"
        .run(::nameIsLong)
        .run(::playerCreateMessage)
        .run(::println)

    println(playerCreateMessage(nameIsLong("Madrigal")))

    // 少用，知道可以這樣用就好
    val status = run {
        if (healthPoints == 20) "perfect health" else "has injuries"
    }

    // =>
    // run:     返回最後一行 (lambda 結果)
    // apply:   返回接收者

    // # with
    // run 的變形，不推薦使用
    val nameTooLong = with("Hello World") {
        length >= 20
    }
    "Hello World".run {
        length >= 20
    }

    // # also
    // 與 let 相似, also 返回接收者, let 返回結果
    var fileContents: List<String>
    File(filePath)
        .also {
            print(it.name)
        }.also {
            fileContents = it.readLines()
        }

    // # takeIf
    // use takeIf
    val fileContents1 = File(filePath)
        .takeIf { it.canRead() && it.canWrite() }
        ?.readText()
    // no use takeIf
    val file2 = File(filePath)
    val fileContents2 = if (file2.canRead() && file2.canWrite()) {
        file2.readText()
    } else {
        null
    }

    // # takeUnless
    // 不推薦使用
    val fileContents3 = File(filePath)
        .takeUnless {
            it.isHidden
        }?.readText()

}

private fun nameIsLong(name: String) = name.length >= 20

private fun playerCreateMessage(result: Boolean) = "name is ${if (result) "Long" else "Short"}"