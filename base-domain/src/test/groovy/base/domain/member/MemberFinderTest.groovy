package base.domain.member

import base.domain.member.entity.Member
import base.domain.member.repository.MemberBuildSpecs
import base.domain.member.repository.MemberRepository
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Ignore
import spock.lang.Specification

@SpringBootTest
class MemberFinderTest extends Specification {

    @Autowired
    MemberRepository memberRepository

    EnhancedRandom enhancedRandom;

    def setup() {
        memberRepository.deleteAll()

        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3, 5)
                .collectionSizeRange(2, 3)
                .build();
    }

    def "member find test"() {
        given:
        def member = enhancedRandom.nextObject(Member.class, "memberId")
        def saveMember = memberRepository.save(member)

        when:
        def result = memberRepository.findMemberByMemberEmail(saveMember.memberEmail)

        then:
        with(result.get()) {
            memberName == saveMember.memberName
            memberEmail == saveMember.memberEmail
            memberAge == saveMember.memberAge
            memberSex == saveMember.memberSex
            memberAddress == saveMember.memberAddress
            memberPhoneNumber == saveMember.memberPhoneNumber
            memberGrade == saveMember.memberGrade
        }
    }

    @Ignore
    def "member find test using querydsl"() {
        given:
        def member = enhancedRandom.nextObject(Member.class, "memberId")
        def saveMember = memberRepository.save(member)

        when:
        def result = memberRepository.findMemberByMemberEmailUsingQuerydsl(saveMember.memberEmail)

        then:
        with(result.get()) {
            memberName == saveMember.memberName
            memberEmail == saveMember.memberEmail
            memberAge == saveMember.memberAge
            memberSex == saveMember.memberSex
            memberAddress == saveMember.memberAddress
            memberPhoneNumber == saveMember.memberPhoneNumber
            memberGrade == saveMember.memberGrade
        }
    }

    def "member find test using criteria spec"() {
        given:
        def member = enhancedRandom.nextObject(Member.class, "memberId")
        def saveMember = memberRepository.save(member)

        when:
        def result = memberRepository.findOne(MemberBuildSpecs.findMemberByMemberEmail(saveMember.memberEmail))

        then:
        with(result.get()) {
            memberName == saveMember.memberName
            memberEmail == saveMember.memberEmail
            memberAge == saveMember.memberAge
            memberSex == saveMember.memberSex
            memberAddress == saveMember.memberAddress
            memberPhoneNumber == saveMember.memberPhoneNumber
            memberGrade == saveMember.memberGrade
        }
    }
}
