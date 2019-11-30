package base.utils

import spock.lang.Specification

class UriQueryParamExtractorTest extends Specification {

	def "uri query parameter extract test"() {
		given:
		def uri = new URI("www.study.com/member?name=abc&age=20&country=Korea")

		when:
		def result = UriQueryParamExtractor.extract(uri)

		then:
		with(result) {
			get("name") == "abc"
			get("age") == "20"
			get("country") == "Korea"
		}
	}
}
