package base.application.member

import base.domain.member.entity.Member
import base.domain.member.exception.MemberDuplicatedException
import base.domain.member.repository.MemberRepository
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
class MemberRegisterUnitTest extends Specification {

    @Autowired
    MemberRepository memberRepository

    @Autowired
    MemberRegister memberRegister

    @Shared
    def enhancedRandom

    def setupSpec() {
        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3, 5)
                .collectionSizeRange(3, 5)
                .build()
    }

    def "회원가입 정상완료 테스트"(){
        given:
        def member = enhancedRandom.nextObject(Member.class, "memberId")

        when:
        def result = memberRegister.signUp(member)

        then:
        result.memberId != null
        with(result) {
            memberPassword == member.memberPassword
            memberName == member.memberName
            memberEmail == member.memberEmail
            memberAge == member.memberAge
            memberSex == member.memberSex
            memberAddress == member.memberAddress
            memberPhoneNumber == member.memberPhoneNumber
            roles.get(0) == member.roles.get(0)
            createdAt == member.createdAt
            modifiedAt == member.modifiedAt
        }
    }


    def "똑같은 이메일이 들어오면 MemberDuplicatedException 발생"() {
        given:
        def member = enhancedRandom.nextObject(Member.class)
        memberRepository.save(member)

        when:
        memberRegister.signUp(member)

        then:
        thrown(MemberDuplicatedException.class)
    }
}
