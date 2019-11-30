@file:JvmName("ArithmeticCalculator")

package base.utils

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

private const val DEFAULT_SCALE = 0
private val DEFAULT_DIVISOR = BigDecimal(100)
private val DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP

fun add(num1: BigDecimal, num2: BigDecimal): BigDecimal {
    return num1.add(num2)
}

fun subtract(num1: BigDecimal, num2: BigDecimal): BigDecimal {
    return num1.subtract(num2)
}

fun subtract(num1: Double, num2: Double): Double {
    return subtract(BigDecimal.valueOf(num1), BigDecimal.valueOf(num2)).toDouble()
}

fun multiply(num1: BigDecimal, num2: BigDecimal): BigDecimal {
    return num1.multiply(num2)
}

fun multiply(num1: Double, num2: Double): BigDecimal {
    return multiply(BigDecimal.valueOf(num1), BigDecimal.valueOf(num2))
}

fun multiply(num1: BigDecimal, num2: BigDecimal, roundingMode: RoundingMode): BigDecimal {
    return multiplyWithScale(num1, num2, DEFAULT_SCALE, roundingMode)
}

fun multiplyWithScale(num1: BigDecimal, num2: BigDecimal, scale: Int, roundingMode: RoundingMode): BigDecimal {
    return num1.multiply(num2, MathContext.DECIMAL128).setScale(scale, roundingMode)
}

fun divide(num1: BigDecimal, num2: BigDecimal): BigDecimal {
    return divideWithScale(num1, num2, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE)
}

fun divide(num1: BigDecimal, num2: BigDecimal, roundingMode: RoundingMode): BigDecimal {
    return divideWithScale(num1, num2, DEFAULT_SCALE, roundingMode)
}

fun divideWithScale(num1: BigDecimal, num2: BigDecimal, scale: Int, roundingMode: RoundingMode): BigDecimal {
    return num1.divide(num2, MathContext.DECIMAL128).setScale(scale, roundingMode)
}

fun percentageConversion(dividend: BigDecimal): BigDecimal {
    return dividend.divide(DEFAULT_DIVISOR, MathContext.DECIMAL128);
}