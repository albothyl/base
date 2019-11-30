package base.utils

import spock.lang.Specification
import spock.lang.Unroll

class EmailCheckerTest extends Specification {

	def "valid email check test"() {
		given:
		def validEMailAddress = "abc@naver.com"

		when:
		def result = EmailChecker.check(validEMailAddress)

		then:
		result == true
	}

	@Unroll
	def "invalid email check test"() {
		given:
		def inValidEMailAddress = INVALID_EMAIL_ADDRESS

		when:
		def result = EmailChecker.check(inValidEMailAddress)

		then:
		result == false

		where:
		INVALID_EMAIL_ADDRESS << [
				"abc.naver.com",
				"abc@naver.comcomcom"
		]
	}
}
