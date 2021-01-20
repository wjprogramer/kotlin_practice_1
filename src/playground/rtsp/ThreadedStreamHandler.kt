package playground.rtsp

import java.io.*

class ThreadedStreamHandler(
    private val inputStream: InputStream,
    private var outputStream: OutputStream?,
    private var adminPassword: String?
): Thread() {

    private val printWriter: PrintWriter? = if (outputStream != null) PrintWriter(outputStream!!) else null
    private val outputBuffer = StringBuilder()
    private var sudoIsRequested = true

    constructor(
        inputStream: InputStream
    ): this(inputStream, null, null) {
        this.sudoIsRequested = false
    }

    override fun run() {

        if (sudoIsRequested) {
            printWriter?.println(adminPassword)
            printWriter?.flush()
        }

        var bufferedReader: BufferedReader? = null

        try {
            bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                outputBuffer.append(line).append("\n")
                line = bufferedReader.readLine()
                println(line)
            }
            println("Stream thread end")
            println("=================print ffmpeg running/error log=================================")
            println(outputBuffer)
        } catch (ioe: IOException) {
            // TODO: handle this better
            ioe.printStackTrace()
        } catch (t: Throwable) {
            // TODO: handle this better
            t.printStackTrace()
        } finally {
            try {
                bufferedReader?.close()
            } catch (e: IOException) {
                // ignore this one
            }
        }
    }

    private fun doSleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            // ignore
        }
    }

}