package base.syntax.loop

import base.extend.yesterday
import java.time.LocalDateTime

fun main() {
    loopFor(listOf("1", "2", "3", "4", "5"))
    loopWhile(0)
}

fun loopFor(stringList: List<String>) {
    println("#### for")

    for (string in stringList) {
        println(string)
    }

    println("-----------")

    for ((index, value) in stringList.withIndex()) {
        println("$index : $value")
    }

    println("-----------")

    for (index in stringList.indices) {
        println(index)
    }

    println("-----------")

    for (i in 1..3) {
        println(i)
    }

    println("-----------")

    for (i in 1 until 3) { //last value excluded
        println(i)
    }

    println("-----------")

    for (i in 4 downTo 0) {
        println(i)
    }

    println("-----------")

    for (i in 4 downTo 0 step 2) {
        println(i)
    }
}

fun loopWhile(a: Int) {
    var a = a

    println("#### while")

    while (a > 0) {
        a--
    }

    println("-----------")

    do {
        a--
        println(a)
    } while (a > 0)
}