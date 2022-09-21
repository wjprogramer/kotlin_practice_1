package algo.graph

import java.util.LinkedList

// https://web.ntnu.edu.tw/~algo/Graph.html

// ==================================================
// Graph 資料結構 1: 最簡單的
// --------------------------------------------------
// 這種資料結構相當簡單直觀，也非常節省空間，
// 卻不適合用於計算 ── 無法迅速找到一個點碰觸的所有邊。
// ==================================================

private data class Edge(val a: Int, val b: Int) // 一條邊的起點與終點

/**
 * 圖一
 *       0
 *       ● ─ ─ ─ ● 1
 * 2     |\
 * ●     | \
 *       |  \
 *       ● ─  ●
 *       3    4
 */
private fun graph1() {
    arrayOf(Edge(1, 0), Edge(0, 3), Edge(0, 4), Edge(3, 4))
}



// ==================================================
// Graph 資料結構 2: 常見1 Adjacency Matrix
// --------------------------------------------------
// ==================================================
private fun adjacencyMatrix() {
    // 與圖一相同
    val graph = Array(5) { IntArray(5) { 0 } }

    // 若是有權重的圖，可以不要放1，而是改存入權重的值

    graph[0][1] = 1
    graph[0][3] = 1
    graph[0][4] = 1
    graph[3][4] = 1

    graph[1][0] = 1
    graph[3][0] = 1
    graph[4][0] = 1
    graph[4][3] = 1
}

// ==================================================
// Graph 資料結構 3: 常見2 Adjacency Lists
// --------------------------------------------------
// ==================================================

private fun adjacencyList() {
    val list = Array(5) { LinkedList<Int>() }

    list[0].add(1)
    list[0].add(3)
    list[0].add(4)

    list[1].add(1)

    list[3].add(0)
    list[3].add(4)

    list[4].add(0)
    list[4].add(3)
}


