package playground.rtsp

import java.util.*

class RtspTransService {

    companion object {
        var ffmpegProcessMap = mutableMapOf<UUID, Thread>()
        var ffmpegProcessTimerMap = mutableMapOf<UUID, Timer>()
    }

    fun doStartTrans(url: String, playChannel: UUID) {
        val commands = mutableListOf<String>()

        commands.add("ffmpeg")
        commands.add("-i")
        commands.add("rtsp://admin:winsonic16350755@59.120.179.81:5540/chID=1&streamType=sub&linkType=tcp")
        commands.add("-f")
        commands.add("mpegts")
        commands.add("-codec:v")
        commands.add("mpeg1video")
        commands.add("-stats")
        commands.add("-r")
        commands.add("30")
        commands.add("-filter:v")
        commands.add("fps=fps=1/10")
        commands.add("-")

        val rtspTransThread = RtspTransThread(commands)
        rtspTransThread.start()

    }

    fun registerProcess(playChannel: UUID, thread: Thread) {
        ffmpegProcessMap[playChannel] = thread
    }

}