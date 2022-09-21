package design_pattern.creational_pattern.observer

/**
 * - Publisher – this is the observer interface with the ability to register or remove (subscribe or unsubscribe) the
 *   observable objects and with the method for all
 * - WeatherSubscriber – this is the observable interface with the update method
 * - Display – additional interface defining displaying behavior
 */

internal interface Publisher<T>{
    fun register(subscriber: WeatherSubscriber)
    fun remove(subscriber: WeatherSubscriber)
    fun notifySubscribers()
}

interface WeatherSubscriber {
    fun update(temperature: Float, humidity: Float): WeatherSubscriber
}

internal interface DisplayElement {
    fun display(temperature: Float, humidity: Float)
}

