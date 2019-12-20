package base.domain.member

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class MemberFinderTest extends Specification {

    @Autowired
    MemberRepository memberRepository

    def "member find test"() {
        given:
        def memberEmail = "admin@naver.com"

        when:
        def result = memberRepository.findMemberByMemberEmail(memberEmail)

        then:
        with(result.get()) {
            memberName == "admin"
            memberEmail == "admin@naver.com"
            memberAge == 20
            memberSex == "MALE"
            memberAddress == "korea"
            memberPhoneNumber == "010-1234-1234"
            memberGrade == "GOLD"
        }
    }

    def "member find test using querydsl"() {
        given:
        def memberEmail = "admin@naver.com"

        when:
        def result = memberRepository.findMemberByMemberEmailUsingQuerydsl(memberEmail)

        then:
        with(result.get()) {
            memberName == "admin"
            memberEmail == "admin@naver.com"
            memberAge == 20
            memberSex == "MALE"
            memberAddress == "korea"
            memberPhoneNumber == "010-1234-1234"
            memberGrade == "GOLD"
        }
    }
}
