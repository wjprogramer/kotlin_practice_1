package playground

class TreeNode(var value: Int? = null) {
    val children: MutableList<TreeNode> = mutableListOf()
}

fun traverse(root: TreeNode): Int {
    if (root.children.isEmpty()) {
        return 1
    }
    var max = 0
    for (n in root.children) {
        val v = traverse(n)
        if (v > max) {
            max = v
        }
    }
    return max + 1
}

fun main() {
    val root = TreeNode()

    traverse(root)
}