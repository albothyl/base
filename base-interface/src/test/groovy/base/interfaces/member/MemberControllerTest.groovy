package base.interfaces.member

import base.domain.member.entity.Member
import base.domain.member.repository.MemberRepository
import base.interfaces.member.dto.MemberSignUpRequest
import base.support.PasswordEncoderUtils
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification
import springfox.documentation.service.MediaTypes
import sun.nio.cs.UTF_8

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    MemberRepository memberRepository

    @Shared
    def enhancedRandom

    def setupSpec() {
        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3, 5)
                .collectionSizeRange(1, 3)
                .build()
    }

    def setup() {
        memberRepository.deleteAll()
    }

    //TODO - 한글 인코딩 문제 발생
    def "정상 회원가입 테스트"() {
        given:
        def memberRequestDto = MemberSignUpRequest.builder()
                .memberPassword("비밀번호7722")
                .memberName("김동환")
                .memberEmail("abc@naver.com")
                .memberAge(12)
                .memberSex("남")
                .memberAddress("서울시")
                .memberPhoneNumber("01020001231")
                .roles(["admin"])
                .memberGrade("SILVER")
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build()


        expect:
        mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(memberRequestDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("memberPassword").doesNotExist())
                .andExpect(jsonPath("memberId").exists())
                .andExpect(jsonPath("memberName").exists())
                .andExpect(jsonPath("memberEmail").exists())
                .andExpect(jsonPath("memberAge").exists())
                .andExpect(jsonPath("memberSex").exists())
                .andExpect(jsonPath("memberAddress").exists())
                .andExpect(jsonPath("memberPhoneNumber").exists())

    }

    def "비밀번호 변경 api 호출시 성공적으로 비밀번호 변경"() {
        given:
        def testMember = enhancedRandom.nextObject(Member, "memberId")
        def newPassword = "newPassword"

        testMember = memberRepository.save(testMember)

        when:
        mockMvc.perform(patch("/members/{memberId}", testMember.memberId)
                .content(newPassword))
                .andDo(print())

        def result = memberRepository.findById(testMember.memberId).get()

        then:
        assert PasswordEncoderUtils.matches(newPassword, result.memberPassword)
    }

    def "입력 형식을 지키지 않고 회원가입을 시도했을 때 400 에러 발생"() {
        given:
        def signUpMember = enhancedRandom.nextObject(MemberSignUpRequest.class)

        expect:
        mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(signUpMember)))
                .andDo(print())
                .andExpect(status().isBadRequest())
    }

    def "핸드폰 형식을 지키지 않고 회원가입을 시도했을 때 400 에러 발생"() {
        given:
        def signUpMember = MemberSignUpRequest.builder()
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
