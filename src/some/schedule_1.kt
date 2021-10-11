package some

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

// https://stackoverflow.com/questions/52317863/schedule-that-runs-every-day-at-a-specific-time-with-hhmmss

fun main() {
    java.util.Date // represents a date with a time-of-day in UTC.
        .from(
            ZonedDateTime.of(
                LocalDate.now( ZoneId.of( "America/Montreal" )  ) ,
                LocalTime.parse( "11:07:09" ) ,
                ZoneId.of( "America/Montreal" )
            )
                .toInstant()
        )
}