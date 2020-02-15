package base.domain.member.entity

import io.github.benas.randombeans.EnhancedRandomBuilder
import spock.lang.Shared
import spock.lang.Specification

class MemberTest extends Specification {

    @Shared
    def enhanceRandom

    def setupSpec() {
        enhanceRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3, 5)
                .collectionSizeRange(2, 3)
                .build()
    }

    def "memberPassword를 변경했을 때 성공적으로 변경"() {
        given:
        def member = enhanceRandom.nextObject(Member.class, "memberPassword")
        def newPassword = "newPassword"

        when:
        member.changePassword(newPassword)

        then:
        member.memberPassword == newPassword
    }
}
