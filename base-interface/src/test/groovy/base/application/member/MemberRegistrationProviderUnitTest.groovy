package base.application.member

import base.domain.member.entity.Member
import base.domain.member.exception.MemberDuplicatedException
import base.domain.member.repository.MemberRepository
import io.github.benas.randombeans.EnhancedRandomBuilder
import spock.lang.Shared
import spock.lang.Specification

class MemberRegistrationProviderUnitTest extends Specification {

    def memberRepository
    def memberSignManager
    @Shared def enhancedRandom

    def setupSpec() {
        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3, 5)
                .collectionSizeRange(3, 5)
                .build()
    }

    def "똑같은 이메일이 들어오면 MemberDuplicatedException 발생"() {
        given:
        def member = enhancedRandom.nextObject(Member.class)

        memberRepository = Stub(MemberRepository.class)
        memberRepository.existsByMemberEmail(member.memberEmail) >> true

        memberSignManager = new MemberRegistrationProvider(memberRepository)

        when:
        memberSignManager.signUp(member)

        then:
        thrown(MemberDuplicatedException.class)
    }
}
