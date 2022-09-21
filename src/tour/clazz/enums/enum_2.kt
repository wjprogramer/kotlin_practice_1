package tour.clazz.enums

data class Coordinate(val x: Int, val y: Int) {
    val isInBounds = x >= 0 && y >= 0
}

enum class Direction1 {
    EAST,
    SOUTH,
    WEST,
    NORTH,
}

enum class Direction2(private val coordinate: Coordinate) {
    EAST(Coordinate(1, 0)),
    SOUTH(Coordinate(0, -1)),
    WEST(Coordinate(-1, 0)),
    NORTH(Coordinate(0, 1));

    fun updateCoordinate(playerCoordinate: Coordinate) =
        Coordinate(playerCoordinate.x + coordinate.x,
            playerCoordinate.y + coordinate.y)
}

fun main() {
    Direction2.EAST.updateCoordinate(Coordinate(1, 0))

    println(Direction1.valueOf("EAST"))
    println(Direction1.valueOf("SOUTH"))
    println(Direction1.valueOf("SOUTH"))
}