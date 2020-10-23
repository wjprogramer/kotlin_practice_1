package algo_examples

fun hanoi(n: Int, src: Char, aux: Char, dst: Char) {
    if (n == 1) {
        println("$src -> $dst")
    } else {
        hanoi(n - 1, src, dst, aux)
        println("$src -> $dst")
        hanoi(n - 1, aux, src, dst)
    }
}

fun main() {
    hanoi(4, 'A', 'B', 'C')
}