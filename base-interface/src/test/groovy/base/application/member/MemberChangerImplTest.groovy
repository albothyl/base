package base.application.member

import base.application.member.exception.MemberPasswordEqualException
import base.domain.member.MemberRepository
import base.domain.member.entity.Member
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class MemberChangerImplTest extends Specification {

    @Autowired
    MemberChanger memberChanger

    @Autowired
    MemberRepository memberRepository

    def setup() {
        memberRepository.deleteAll()
    }

    def "MemberId를 DB에 조회해보았을 때 없다면 IllegalArgumentException 발생"() {
        given:
        Member member = EnhancedRandom.random(Member)

        when:
        memberChanger.changePassword(member.memberId+1, "newPassword")

        then:
        thrown(IllegalArgumentException)
    }

    def "새로운 패스워드가 기존 패스워드와 동일할 때 IllegalArgumentException 발생"() {
        given:
        def member = Member.builder()
                .memberPassword("{bcrypt}\$2a\$10\$bX1r6QoadC9c/AMdORDVnuLW4d4e3bUQKZk0MMBhvj/wj2X6CKiJa")
                .memberName("admin")
                .memberEmail("admin@naver.com")
                .memberAge(20)
                .memberSex("MALE")
                .memberAddress("korea")
                .memberPhoneNumber("010-1234-1234")
                .memberGrade("GOLD")
                .roles(["ADMIN"])
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build()

        memberRepository.save(member)

        when:
        memberChanger.changePassword(member.memberId, member.memberPassword)

        then:
        thrown(MemberPasswordEqualException)
    }

    def "기존 패스워드와 다른 새로운 패스워드가 들어올 때 성공적으로 비밀번호 변경"() {
        given:
        def builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3,5)
                .collectionSizeRange(1,3)
                .build();


        def testMember = builder.nextObject(Member, "memberId")

        def newPassword = "newPassword"

        testMember = memberRepository.save(testMember)

        when:
        memberChanger.changePassword(testMember.memberId, newPassword)
        def result = memberRepository.findById(testMember.memberId)

        then:
        with(result.get()) {
            memberId == testMember.memberId
            memberPassword == "newPassword"
        }
    }
}
