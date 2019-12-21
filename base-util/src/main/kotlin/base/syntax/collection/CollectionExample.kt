package base.syntax.collection

import java.util.*

fun collectionEx() {
    val numberList = listOf(1, 2, 3, 4, 5)
    val numberMutableList = mutableListOf(1, 2, 3, 4, 5)

    numberList
            .filter { !Objects.isNull(it) }
            .map { it.toString() }
            .toList()

    val numberSet = setOf(1, 2, 3, 4, 5)
    val numberMutableSet = mutableSetOf(1, 2, 3, 4, 5)

    numberSet
            .filter { !Objects.isNull(it) }
            .map { it.toString() }
            .toList()

    val pair: Pair<Int, Int> = 1 to 1

    val numberMap = mapOf(1 to 1, 2 to 2, 3 to 3)
    val numberMutableMap = mutableMapOf(1 to 1, 2 to 2, 3 to 3)

    numberMutableMap.put(4, 4)
    numberMutableMap[5] = 5

    numberMap
            .filterKeys { !Objects.isNull(it) }
            .map { entry -> entry.value + 1 }
            .toList()

//    https://kotlinlang.org/docs/reference/collections-overview.html
}