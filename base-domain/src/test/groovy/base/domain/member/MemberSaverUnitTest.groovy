package base.domain.member

import base.domain.member.entity.Member
import base.domain.member.exception.MemberDuplicatedException
import base.domain.member.repository.MemberRepository
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Shared
import spock.lang.Specification

class MemberSaverUnitTest extends Specification {

    MemberRepository memberRepository
    MemberSaver memberSaver
    @Shared EnhancedRandom enhancedRandom

    def setupSpec() {
        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3, 5)
                .collectionSizeRange(3, 5)
                .build()
    }

    def "똑같은 이메일이 들어오면 MemberDuplicatedException 발생"() {
        given:
        Member member = enhancedRandom.nextObject(Member.class)

        memberRepository = Stub(MemberRepository.class)
        memberRepository.existsMemberByMemberEmail(member.memberEmail) >> true

        memberSaver = new MemberSaver(memberRepository)

        when:
        memberSaver.signUp(member)

        then:
        def error = thrown(MemberDuplicatedException.class)
        error.message == "입력한 email은 이미 사용중입니다."
    }
}
