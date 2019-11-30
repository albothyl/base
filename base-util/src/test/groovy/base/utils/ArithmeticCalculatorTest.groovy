package base.utils

import spock.lang.Specification

import java.math.RoundingMode

class ArithmeticCalculatorTest extends Specification {

	def "add test"() {
		given:
		def num1 = BigDecimal.valueOf(6)
		def num2 = BigDecimal.valueOf(2)

		expect:
		ArithmeticCalculator.add(num1, num2) == 8
	}

	def "subtract test"() {
		given:
		def num1 = BigDecimal.valueOf(6)
		def num2 = BigDecimal.valueOf(2)

		expect:
		ArithmeticCalculator.subtract(num1, num2) == 4
	}

	def "multiply test"() {
		given:
		def num1 = BigDecimal.valueOf(6)
		def num2 = BigDecimal.valueOf(2)

		expect:
		ArithmeticCalculator.multiply(num1, num2) == 12
	}

	def "multiply with scale test"() {
		given:
		def num1 = BigDecimal.valueOf(6.123)
		def num2 = BigDecimal.valueOf(2.123)
		def scale = 2
		def roundingMode = RoundingMode.HALF_UP

		expect:
		ArithmeticCalculator.multiplyWithScale(num1, num2, scale, roundingMode) == 13.00
	}

	def "divide test"() {
		given:
		def num1 = BigDecimal.valueOf(6)
		def num2 = BigDecimal.valueOf(2)

		expect:
		ArithmeticCalculator.divide(num1, num2) == 3
	}

	def "divide with scale test"() {
		given:
		def num1 = BigDecimal.valueOf(6.123)
		def num2 = BigDecimal.valueOf(2.22)
		def scale = 2
		def roundingMode = RoundingMode.HALF_UP

		expect:
		ArithmeticCalculator.divideWithScale(num1, num2, scale, roundingMode) == 2.76
	}

	def "percentage conversion test"() {
		given:
		def num1 = BigDecimal.valueOf(6)

		expect:
		ArithmeticCalculator.percentageConversion(num1) == 0.06
	}
}
