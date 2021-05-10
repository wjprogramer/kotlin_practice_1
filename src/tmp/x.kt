package tmp

fun rotate(matrix: Array<IntArray>): Unit {
    val sideSize = matrix.size
    val count = sideSize * sideSize + 1

    var nextRowIndex = 0
    var nextColIndex = 0

    var nextNumber = matrix[nextRowIndex][nextColIndex]

    for (i in 1..count) {
        val tempRowIndex = nextRowIndex
        nextRowIndex = nextColIndex
        nextColIndex = sideSize - 1 - tempRowIndex

        val tempNumber = nextNumber
        nextNumber = matrix[nextRowIndex][nextColIndex]
        matrix[nextRowIndex][nextColIndex] = tempNumber
    }


//    for (ri in 0 until rowSize) {
//        val newCol = matrix.size - 1 - ri
//        val colLimit = colSize - ri
//        for (ci in 0 until colLimit) {
//            val temp = matrix[ri][ci]
//            matrix[ri][ci] = matrix[ci][newCol]
//            matrix[ci][newCol] = temp
//        }
//    }
}

fun main() {
    rotate(arrayOf(
        intArrayOf(1,2,3),
        intArrayOf(4,5,6),
        intArrayOf(7,8,9)
    ))
}