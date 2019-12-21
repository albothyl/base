package base.extend

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

fun String.repeat2(count: Int) {
    for (cnt in 1..count) {
        println(this)
    }
}

fun LocalDateTime.today(): LocalDateTime {
    return LocalDateTime.now()
            .truncatedTo(ChronoUnit.DAYS)
}

fun LocalDateTime.tomorrow(): LocalDateTime {
    return LocalDateTime.now()
            .plusDays(1L)
            .truncatedTo(ChronoUnit.DAYS)
}

fun LocalDateTime.yesterday(): LocalDateTime {
    return LocalDateTime.now()
            .plusDays(1L)
            .truncatedTo(ChronoUnit.DAYS)
}