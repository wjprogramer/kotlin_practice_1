package tour.io.file

import java.io.File
import kotlin.system.exitProcess

private fun readAndWrite() {
    val list = File("data/my-data.txt")
        .readText()
        .split("\n")

    list.forEachIndexed { index, line ->
        val (_, _, v3) = line.split(",")
        println("$index. $line ... $v3")
    }



}

fun main() {
    while(true) {
        println(message = "Type number:")
        val option = readLine()

        when(option?.toIntOrNull()) {
            1 -> readAndWrite()
            else -> exitProcess(1)
        }

        println("======")
    }
}