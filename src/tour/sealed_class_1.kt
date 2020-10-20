package tour

import kotlin.system.exitProcess

// 在表達一個元件的狀態時，可能第一個想到的會是 enum class 。
// 而現在有了新的選擇—— sealed class 。它們倆的差別是：
//
// - enum class 可以拿來簡單的判斷狀態，但不能傳遞變數；
// - 反之，sealed class 可以攜帶變數。
//
// sealed class 是一個 abstract class ，本身並不能被 instantiate（實體化）。
// 但可以透過繼承某個 sealed class ，限縮可能的類型。可以傳遞變數這種特性讓它可以作為進階版的 enum 使用，與 when語法配合後效果更佳。

/** region 複習 enum */
private enum class MachineState {
    START, SLEEP, RUNNING
}

private fun machineStateChecker(state: MachineState) = when (state) {
    MachineState.START -> "Machine is about to start"
    MachineState.SLEEP -> "Machine is sleeping"
    MachineState.RUNNING -> "Machine is running"
}
/** endregion */

// 當然，如果不需要傳遞變數，也能單純建立許多 object ，這樣用起來與 enum class 就會是一樣的感覺。
// 在 when 判斷式之中，如果某個 sealed class 子類別是 object ，
// 便無需使用 is 來判斷。另外，如果已經判斷完所有的可能狀況（即判斷了所有的 sealed class 子類別），
// when 就不用另外處理 else 的情境。此外，sealed class 需要定義在同一個檔案裡頭。

// 以下範例以透過 Id 分別取得三台不同機器的 WorkingState，再由 machineStatePrinting() Function 印出不同狀態的結果：
/** region Sealed Class */
sealed class WorkingState {
    // data class
    data class Finished(val result: List<String>): WorkingState()
    data class ErrorHappened(val whatHappened: String): WorkingState()

    // object
    object Working: WorkingState()
    object EmptyResult: WorkingState()
}

private fun sealedClassDemo1() {
    val machineState = checkStateByMachineId(1)
    machineStatePrinting(machineState)    // result 01

    val secondMachineState = checkStateByMachineId(2)
    machineStatePrinting(secondMachineState)    // result 02

    val thirdMachineState = checkStateByMachineId(3)
    machineStatePrinting(thirdMachineState)    // result 03
}

fun checkStateByMachineId(id: Int): WorkingState {
    // Do some logic
    return when (id) {
        1 -> {
            val result = mutableListOf("Warthog", "Hedgehog", "Badger", "Drake")
            WorkingState.Finished(result)
        }
        2 -> WorkingState.ErrorHappened("Too big too eat.")
        3 -> WorkingState.EmptyResult
        else -> WorkingState.Working
    }
}

fun machineStatePrinting(workingState: WorkingState) {
    when (workingState) {
        is WorkingState.Finished -> {
            if (workingState.result.isNotEmpty()) {
                for ((index, text) in workingState.result.withIndex()) {
                    println("the ${index + 1} result is $text")
                }
            }
        }
        is WorkingState.ErrorHappened -> {
            println("""
                Error Occurred: reason is
                ${workingState.whatHappened}
            """.trimIndent())
        }
        WorkingState.Working -> {
            println("Printing machine is working...")
        }
        WorkingState.EmptyResult -> {
            println("It's empty result.")
        }
    }
}
/** endregion */

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine() ?: exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> println("current state = ${machineStateChecker(MachineState.SLEEP)}")
            2 -> sealedClassDemo1()
            else -> exitProcess(1)
        }

        println("======")
    }
}

// ref:
// - https://medium.com/@louis383/kotlin-sealed-classes-%E7%9A%84%E5%9F%BA%E7%A4%8E%E4%BD%BF%E7%94%A8-de660dbb63d2