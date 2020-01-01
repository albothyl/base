package base.domain.member

import base.domain.member.entity.Member
import base.domain.member.repository.MemberRepository
import base.domain.member.repository.MemberBuildSpecs
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
class MemberFinderTest extends Specification {

    @Autowired
    MemberRepository memberRepository

    @Shared
    EnhancedRandom enhancedRandom

    @Shared
    def member

    def setupSpec() {
        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3, 5)
                .collectionSizeRange(2, 3)
                .build();

        member = enhancedRandom.nextObject(Member.class, "memberId")
    }

    def setup() {
        member = memberRepository.save(member)
    }

    def cleanup() {
        memberRepository.deleteAll()
    }

    def "member find test"() {
        given:
        def testMember = member

        when:
        def result = memberRepository.findMemberByMemberEmail(testMember.memberEmail)

        then:
        with(result.get()) {
            memberName == testMember.memberName
            memberEmail == testMember.memberEmail
            memberAge == testMember.memberAge
            memberSex == testMember.memberSex
            memberAddress == testMember.memberAddress
            memberPhoneNumber == testMember.memberPhoneNumber
            memberGrade == testMember.memberGrade
        }
    }

    @Ignore
    def "member find test using querydsl"() {
        given:
        def testMember = member

        when:
        def result = memberRepository.findMemberByMemberEmailUsingQuerydsl(testMember.memberEmail)

        then:
        with(result.get()) {
            memberName == testMember.memberName
            memberEmail == testMember.memberEmail
            memberAge == testMember.memberAge
            memberSex == testMember.memberSex
            memberAddress == testMember.memberAddress
            memberPhoneNumber == testMember.memberPhoneNumber
            memberGrade == testMember.memberGrade
        }
    }

    def "member find test using criteria spec"() {
        given:
        def testMember = member

        when:
        def result = memberRepository.findOne(MemberBuildSpecs.findMemberByMemberEmail(testMember.memberEmail))

        then:
        with(result.get()) {
            memberName == testMember.memberName
            memberEmail == testMember.memberEmail
            memberAge == testMember.memberAge
            memberSex == testMember.memberSex
            memberAddress == testMember.memberAddress
            memberPhoneNumber == testMember.memberPhoneNumber
            memberGrade == testMember.memberGrade
        }
    }
}
