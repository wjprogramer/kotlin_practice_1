package playground

val res = mutableListOf<MutableList<Int>>()

/** 主要函式，輸入一組不重複的數字，回傳全部排列組合 */
fun permute(numbers: IntArray): MutableList<MutableList<Int>> {
    // 記錄路徑
    val track = mutableListOf<Int>()
    backtrack(numbers, track)
    return res
}

/**
 * 路徑：記錄在 track 中
 * 選擇清單：numbers 中不存在於 track 的元素
 * 結束條件：numbers 中的元素全都在 track 中出現
 */
fun backtrack(numbers: IntArray, track: MutableList<Int>) {
    // 觸發結束條件
    if (track.size == numbers.size) {
        res.add(track.toMutableList()) // clone
        return
    }

    for (num in numbers) {
        // 排除不合理的選擇
        if (track.contains(num)) {
            continue
        }
        // 做選擇
        track.add(num)
        // 進入下一層決策樹
        backtrack(numbers, track)
        // 取消選擇
        track.removeAt(track.lastIndex)
    }
}

fun main() {
    permute(intArrayOf(1,2,3))
    res.forEach {
        println(it.joinToString(","))
    }
}