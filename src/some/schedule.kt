package some

import java.util.Timer
import kotlin.concurrent.schedule

// https://stackoverflow.com/questions/43348623/how-to-call-a-function-after-delay-in-kotlin

fun main() {
    Timer("SettingUp", false).schedule(1500) {
        println("do something...")
    }
}