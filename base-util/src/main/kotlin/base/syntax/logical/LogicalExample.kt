package base.syntax.logical

import base.extend.repeat2

fun logicalIf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

fun logicalIf2(a: Int, b: Int): Int {
    return if (a > b) {
        a
    } else {
        b
    }
}

fun logicalWhen(a: Int): String {
    return when (a) {
        1, 2 -> "One"
        in 3 .. 5 -> "range"
        6 -> "Long"
        else -> "Unknown"
    }
}
