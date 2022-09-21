package algo.sort

// 選擇排序法（ Selection Sort ）
// paradigm: Incremental Method
// intro: https://web.ntnu.edu.tw/~algo/AlgorithmDesign.html
// code: https://chercher.tech/kotlin/selection-sort-kotlin

fun selectionSort(sampleArray:IntArray){
    val n = sampleArray.size
    var temp: Int
    for (i in 0 until n) {
        var indexOfMin = i
        for (j in n-1 downTo  i) {
            if (sampleArray[j]< sampleArray[indexOfMin])
                indexOfMin=j
        }
        if (i != indexOfMin) {
            temp = sampleArray[i]
            sampleArray[i] = sampleArray[indexOfMin]
            sampleArray[indexOfMin] = temp
        }
    }
}

private fun main(arg: Array<String>) {
    println("Before Sort")
    val array = intArrayOf(1, 7, 3, 9, 4)
    for (i in array) print(i)

    selectionSort(array)

    println("Sorted array is : ")
        for (i in array) print(i)
}