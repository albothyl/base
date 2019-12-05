package base.application.member

import base.domain.member.MemberRepository
import base.domain.member.entity.Member
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class MemberServiceImplTest extends Specification {

    MemberService memberService
    Member testMember
    MemberRepository memberRepository

    def setup() {
        memberRepository = Mock(MemberRepository.class)
        testMember = EnhancedRandom.random(Member.class)

        memberService = new MemberServiceImpl(memberRepository)
    }

    def "MemberId를 DB에 조회해보았을 때 없다면 IllegalArgumentException 발생"() {
        given:
        memberRepository.findById(testMember.getMemberId()+1) >> Optional.empty()

        when:
        memberService.changePassword(testMember.getMemberId()+1, "newPassword")

        then:
        thrown(IllegalArgumentException)
    }

    def "새로운 패스워드가 기존 패스워드와 동일할 때 IllegalArgumentException 발생"() {
        given:
        memberRepository.findById(testMember.getMemberId()) >> Optional.of(testMember)

        when:
        memberService.changePassword(testMember.memberId, testMember.memberPassword)

        then:
        thrown(IllegalArgumentException)
    }
}
