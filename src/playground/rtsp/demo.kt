package playground.rtsp

import java.util.*
import kotlin.collections.HashMap

val rtspTransService = RtspTransService()

fun main() {
    val url = "rtsp://admin:winsonic16350755@59.120.179.81:5540/chID=1&streamType=sub&linkType=tcp"
    val simpleString = url.replace("//", "/")
    val splitList = simpleString.split('/')

    if (splitList.isEmpty() || splitList[0].toLowerCase() != "rtsp:" || splitList.size < 2) {
        println("不是有效的 rtsp 網址")
        return
    }

    val playChannel = UUID.nameUUIDFromBytes(url.toByteArray())
    val ffmpegThread = RtspTransService.ffmpegProcessMap[playChannel]
    if (ffmpegThread == null) {
        rtspTransService.doStartTrans(url, playChannel)
    } else if (ffmpegThread.state == Thread.State.TERMINATED){
        rtspTransService.doStartTrans(url, playChannel)
    }

//    var timer = RtspTransService.ffmpegProcessTimerMap[playChannel]
//    if (timer == null) {
//        timer = Timer()
//        RtspTransService.ffmpegProcessTimerMap[playChannel] = timer
//    } else {
//        timer.cancel()
//        timer.purge()
//        timer = Timer()
//        RtspTransService.ffmpegProcessTimerMap[playChannel] = timer
//    }
//
//    timer.schedule(
//        object: TimerTask() {
//            override fun run() {
//                if (RtspTransService.ffmpegProcessMap[playChannel] != null) {
//                    RtspTransService.ffmpegProcessMap[playChannel]?.interrupt()
//                }
//            }
//        },
//        9999
//    )

}