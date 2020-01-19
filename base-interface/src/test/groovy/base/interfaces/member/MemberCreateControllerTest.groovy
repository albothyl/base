package base.interfaces.member

import base.domain.member.repository.MemberRepository
import base.interfaces.member.dto.SignUpMember
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class MemberCreateControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    MemberRepository memberRepository

    @Shared def enhancedRandom

    def setupSpec() {
        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3,5)
                .collectionSizeRange(1,3)
                .build();
    }

    def "입력 형식을 지키지 않고 회원가입을 시도했을 때 400 에러 발생"() {
        given:
        def signUpMember = enhancedRandom.nextObject(SignUpMember.class)

        expect:
        mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(signUpMember)))
                .andDo(print())
                .andExpect(status().isBadRequest())
    }

    def "핸드폰 형식을 지키지 않고 회원가입을 시도했을 때 400 에러 발생"() {
        given:
        def signUpMember = SignUpMember.builder()
                            .memberAddress("멤버주소")
                            .memberAge(20)
                            .memberEmail("mollder@naver.com")
                            .memberName("이인규")
                            .memberPassword("password")
                            .memberSex("남")
                            .memberPhoneNumber("잘못된 핸드폰 번호")
                            .build()

        expect:
        mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(signUpMember)))
                .andDo(print())
                .andExpect(status().isBadRequest())
    }

}
