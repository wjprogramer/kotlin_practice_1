package playground.rtsp

import java.io.IOException
import java.lang.Exception

class RtspTransThread(private val commands: List<String>): Thread() {

    override fun run() {
        try {
            while (true) {
                val pb = ProcessBuilder(commands)
                val process = pb.start()

                val inputStream = process.inputStream
                val errorStream = process.errorStream

                val inputStreamHandler = ThreadedStreamHandler(inputStream)
                val errorStreamHandler = ThreadedStreamHandler(errorStream)

                inputStreamHandler.start()
                errorStreamHandler.start()

                while (true) {
                    if (!process.isAlive) {
                        break
                    }
                    if (currentThread().isInterrupted) {
                        process.destroy()
                        break
                    }
                }
                val exitValue = process.waitFor()

                if (currentThread().isInterrupted) {
                    break
                }
            }
        } catch (e: InterruptedException) {
            // timer interrupt the thread to end the stream
        } catch (e: IOException) {
            println("start stream fail")
        }
        println("RtspTransThread End")
    }

}