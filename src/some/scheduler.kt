package some

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

// How to schedule tasks in Ktor microservice app
// https://stackoverflow.com/questions/58717774/how-to-schedule-tasks-in-ktor-microservice-app

// Ktor doesn't have built-in scheduler, so you'd have to implement your own
//
//I've written small class using Java's Executors for this task for myself, you might find it useful

class Scheduler(private val task: Runnable) {
    private val executor = Executors.newScheduledThreadPool(1)!!

    fun scheduleExecution(every: Every) {

        val taskWrapper = Runnable {
            task.run()
        }

        executor.scheduleWithFixedDelay(taskWrapper, every.n, every.n, every.unit)
    }


    fun stop() {
        executor.shutdown()

        try {
            executor.awaitTermination(1, TimeUnit.HOURS)
        } catch (e: InterruptedException) {
        }

    }
}

data class Every(val n: Long, val unit: TimeUnit)