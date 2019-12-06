package base.interfaces.member

import base.domain.member.MemberRepository
import base.domain.member.entity.Member
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerImplTest extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    MemberRepository memberRepository

    def setup() {
        memberRepository.deleteAll()
    }

    def "비밀번호 변경 api 호출시 성공적으로 비밀번호 변경"() {
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
        mockMvc.perform(patch("/members/{memberId}", member.getMemberId())
                .param("newPassword", member.getMemberPassword()+1))
                .andDo(print())
                .andExpect(status().isOk())

        def result = memberRepository.findById(member.getMemberId())

        then:
        with(result.get()) {
            memberPassword == member.getMemberPassword()+1
        }
    }
}
