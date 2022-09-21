package design_pattern.creational_pattern.observer.system_lambda

/**
 * Code that should be executed by on of the methods of [SystemLambda].
 * This code may throw an [Exception]. Therefore we cannot use
 * [Runnable].
 */
interface Statement {
    /**
     * Execute the statement.
     *
     * @throws Exception the statement may throw an arbitrary exception.
     */
    fun execute()
}