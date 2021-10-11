package some

import java.net.HttpURLConnection
import java.net.URL

fun main() {
    val url = URL("http://59.120.179.81:8080/GetChannelList")
    val connection = url.openConnection() as HttpURLConnection
    connection.setRequestProperty("Authorization", "Basic YWRtaW46d2luc29uaWMxNjM1MDc1NQ==")

    with(connection) {
        requestMethod = "GET"  // optional default is GET

        println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")

        inputStream.bufferedReader().use {
            it.lines().forEach { line ->
                println(line)
            }
        }
    }
}