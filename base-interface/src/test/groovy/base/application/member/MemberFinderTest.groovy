package base.application.member

import base.domain.member.entity.Member
import base.domain.member.exception.MemberNotFoundException
import base.domain.member.repository.MemberRepository
import base.domain.member.repository.MemberSpecs
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class MemberFinderTest extends Specification {

    @Autowired
    MemberFinder memberFinder

    @Autowired
    MemberRepository memberRepository

    @Shared
    def enhancedRandom

    def setupSpec() {
        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3, 5)
                .collectionSizeRange(2, 3)
                .build()
    }

    def "member find test"() {
        given:
        def member = enhancedRandom.nextObject(Member.class, "memberId")
        def saveMember = memberRepository.save(member)

        when:
        def result = memberFinder.findByMemberEmail(saveMember.memberEmail)

        then:
        with(result) {
            memberName == saveMember.memberName
            memberEmail == saveMember.memberEmail
            memberAge == saveMember.memberAge
            memberSex == saveMember.memberSex
            memberPhoneNumber == saveMember.memberPhoneNumber
            memberAddress.city == saveMember.memberAddress.city
            memberAddress.street == saveMember.memberAddress.street
            memberAddress.zipCode == saveMember.memberAddress.zipCode
        }
    }

    def "member not found exception"() {
        when:
        memberFinder.findByMemberEmail("exception")

        then:
        def e = thrown(MemberNotFoundException.class)
        e.getMessage() == "member is not found"
    }




}
