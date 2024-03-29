package design_pattern.creational_pattern.observer

import design_pattern.creational_pattern.observer.system_lambda.SystemLambda
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.io.PrintStream


// https://blog.mestwin.net/observer-pattern-in-kotlin-design-patterns/

/**
 * Observer pattern – defines one-to-many relationship between objects in such a way, that when the object changes his state,
 * then all of his dependent objects are notified and updated
 */

class CurrentConditionsDisplayTest {

    private val standardOut = System.out
    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
    }

    @Test
    fun `should notify both displays with the measured weather data`() {
        val weatherStation = WeatherStation()
        val mobileDisplay = CurrentConditionsDisplay(station = weatherStation, name = "Mobile Display")
        val stationaryDisplay = CurrentConditionsDisplay(station = weatherStation, name = "Stationary Display")
        weatherStation.register(mobileDisplay)
        weatherStation.register(stationaryDisplay)

        val output = SystemLambda.tapSystemOut {
            weatherStation.updateData(10F, 20F)
        }

        assertEquals(
            "Current conditions on Mobile Display. Temperature: 10.0°C, humidity: 20%\n"
                    + "Current conditions on Stationary Display. Temperature: 10.0°C, humidity: 20%",
            output.trim()
        )
    }

    @Test
    fun `should notify only one weather data when one of the subscribers is removed`() {
        val weatherStation = WeatherStation()
        val mobileDisplay = CurrentConditionsDisplay(station = weatherStation, name = "Mobile Display")
        val stationaryDisplay = CurrentConditionsDisplay(station = weatherStation, name = "Stationary Display")
        weatherStation.register(mobileDisplay)
        weatherStation.register(stationaryDisplay)
        weatherStation.notifySubscribers()
        weatherStation.remove(mobileDisplay)

        val output = SystemLambda.tapSystemOut {
            weatherStation.updateData(20F, 90F)
        }

        assertEquals("Current conditions on Stationary Display. Temperature: 20.0°C, humidity: 90%", output.trim())
    }

    @Test
    fun `should not notify any object if there are no subscribers`() {
        val weatherStation = WeatherStation()

        val output = SystemLambda.tapSystemOut {
            weatherStation.updateData(20F, 90F)
        }

        assertEquals(output.trim(), "")
    }
}