package tour.bit

import kotlin.math.pow
import kotlin.system.exitProcess

// Bitwise Operation

fun bit1() {
//    1. or
//    12 = 00001100 (In Binary)
//    25 = 00011001 (In Binary)
//
//    Bitwise OR Operation of 12 and 25
//    00001100 or
//    00011001
//    ________
//    00011101  = 29 (In decimal)
    println(12 or 25)

//    2. and
//    12 = 00001100 (In Binary)
//    25 = 00011001 (In Binary)
//
//    Bit Operation of 12 and 25
//    00001100 and
//    00011001
//    ________
//    00001000  = 8 (In decimal)
    println(12.and(25))
    println(12 and 25)

//    3. xor (兩個不同 回傳1)
//    12 = 00001100 (In Binary)
//    25 = 00011001 (In Binary)
//
//    Bitwise OR Operation of 12 and 25
//    00001100 xor
//    00011001
//    ________
//    00010101  = 21 (In decimal)
    println(12 xor 25)

//    4. inv
//    35 = 00100011 (In Binary)
//
//    Bitwise complement Operation of 35
//    00100011
//    ________
//    11011100  = 220 (In decimal)
    println(35.inv()) // -36

//    ## Why are we getting output -36 instead of 220?

//    It's because the compiler is showing 2's complement of that number; negative notation of the binary number.

//    For any integer `n`, 2's complement of `n` will be `-(n+1)`.

//    ```
//    Decimal         Binary                      2's complement
//    ---------       ---------          ---------------------------------------
//    0             00000000          -(11111111+1) = -00000000 = -0(decimal)
//    1             00000001          -(11111110+1) = -11111111 = -256(decimal)
//    12            00001100          -(11110011+1) = -11110100 = -244(decimal)
//    220           11011100          -(00100011+1) = -00100100 = -36(decimal)
//
//    Note: Overflow is ignored while computing 2's complement.
//    ```

//    The bitwise complement of 35 is 220 (in decimal).
//    The 2's complement of 220 is -36. Hence, the output is -36 instead of 220.

//    5. shl

//    212 (In binary: 11010100)
//
//    212 shl 1 evaluates to 424  (In binary: 110101000)
//    212 shl 0 evaluates to 212  (In binary: 11010100)
//    212 shl 4 evaluates to 3392 (In binary: 110101000000)
    println(212 shl 1)
    println(212 shl 0)
    println(212 shl 4)

//    6. shr

//    212 (In binary: 11010100)
//
//    212 shr 1 evaluates to 106 (In binary: 01101010)
//    212 shr 0 evaluates to 212 (In binary: 11010100)
//    212 shr 8 evaluates to 0 (In binary: 00000000)
    println(212 shr 1)
    println(212 shr 0)
    println(212 shr 4)

//    7. ushr

//    The ushr function shifts zero into the leftmost position.
    println(5 shr 1)        // 2
    println(5 ushr 1)       // 2

    println(-5 shr 1)       // -3
    println(-5 ushr 1)      // 2147483645

//    Notice, how signed and unsigned right shift function works differently for 2's complement.

//    The 2's complement of 2147483645 is 3.
}

fun bit2() {
    println(257.toByte())
    println(256.toByte())
    println(255.toByte())
    println(254.toByte())
    println(253.toByte())
    println(252.toByte())
    println(251.toByte())
    println(250.toByte())
    println(129.toByte())
    println(128.toByte())
    println(127.toByte())
    println(64.toByte())
    println(32.toByte())
    println(16.toByte())
    println(8.toByte())
    println(4.toByte())
}

fun bit3() {
    val max = Int.MAX_VALUE
    println(max)                        // 2147483647
    println(max.toString(2))

    val two31 = 2.0.pow(31.0)
    println(two31)                      //

    println(two31 - max - 1)

}

fun main() {
    while(true) {
        print("Type number: ")
        val option = readLine()
        if (option == null || option.isEmpty()) exitProcess(1)
        println("======")

        when(option.toInt()) {
            1 -> bit1()
            2 -> bit2()
            3 -> bit3()
            else -> exitProcess(1)
        }

        println("======")
    }
}