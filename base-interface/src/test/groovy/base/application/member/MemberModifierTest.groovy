package base.application.member

import base.domain.member.entity.Member
import base.domain.member.exception.MemberNotFoundException
import base.domain.member.exception.MemberPasswordEqualException
import base.domain.member.repository.MemberRepository
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
class MemberModifierTest extends Specification {

    @Autowired
    MemberModifier memberModifier

    @Autowired
    MemberRepository memberRepository

    @Shared
    def enhanceRandom

    def setupSpec() {
        enhanceRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3,5)
                .collectionSizeRange(1,3)
                .build()
    }

    def setup() {
        memberRepository.deleteAll()
    }

    def "MemberId를 DB에 조회해보았을 때 없다면 MemberNotFoundException 발생"() {
        given:
        def member = enhanceRandom.nextObject(Member)

        def differentId = member.memberId+1

        when:
        memberModifier.changePassword(differentId, "newPassword")

        then:
        thrown(MemberNotFoundException.class)
    }

    def "새로운 패스워드가 기존 패스워드와 동일할 때 changePassword 메소드를 호출하면 MemberPasswordEqualException 발생"() {
        given:
        def testMember = enhanceRandom.nextObject(Member, "memberId")

        testMember = memberRepository.save(testMember)

        when:
        memberModifier.changePassword(testMember.memberId, testMember.memberPassword)

        then:
        thrown(MemberPasswordEqualException.class)
    }

    def "기존 패스워드와 다른 새로운 패스워드가 들어올 때 성공적으로 비밀번호 변경"() {
        given:
        def testMember = enhanceRandom.nextObject(Member, "memberId")

        def newPassword = "newPassword"

        testMember = memberRepository.save(testMember)

        when:
        memberModifier.changePassword(testMember.memberId, newPassword)
        def result = memberRepository.findById(testMember.memberId)

        then:
        with(result.get()) {
            memberId == testMember.memberId
            memberPassword == newPassword
        }
    }
}
