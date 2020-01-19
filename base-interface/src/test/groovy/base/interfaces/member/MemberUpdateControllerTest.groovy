package base.interfaces.member

import base.domain.member.entity.Member
import base.domain.member.repository.MemberRepository
import base.support.PasswordEncoderUtils
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class MemberUpdateControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    MemberRepository memberRepository

    def setup() {
        memberRepository.deleteAll()
    }

    def "비밀번호 변경 api 호출시 성공적으로 비밀번호 변경"() {
        given:
        def builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3,5)
                .collectionSizeRange(1,3)
                .build();

        def testMember = builder.nextObject(Member, "memberId")
        def newPassword = "newPassword"

        testMember = memberRepository.save(testMember)

        when:
        mockMvc.perform(patch("/members/{memberId}", testMember.memberId)
                .content(newPassword))
                .andDo(print())
                .andExpect(status().isOk())

        def result = memberRepository.findById(testMember.memberId).get()

        then:
        assert PasswordEncoderUtils.matches(newPassword, result.memberPassword)
    }
}
