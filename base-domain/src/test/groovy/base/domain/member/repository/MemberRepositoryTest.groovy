package base.domain.member.repository

import base.domain.member.entity.Member
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
//@Transactional //rollback
class MemberRepositoryTest extends Specification {

	@Autowired
    MemberRepository memberRepository

	@Shared def enhancedRandom

	def setupSpec() {
		enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
							.stringLengthRange(3, 5)
							.collectionSizeRange(3, 5)
							.build()
	}

	def setup() {
		memberRepository.deleteAll()
	}

	def cleanup() {
		memberRepository.deleteAll()
	}

	def "member email exist method test"() {
		given:
		def member = enhancedRandom.nextObject(Member.class, "memberId")

		def savedMember = memberRepository.save(member)

		when:
		def exists = memberRepository.existsByMemberEmail(savedMember.memberEmail)

		then:
		exists
	}

	@Ignore
	def "member save test"() {
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


		when:
		def result = memberRepository.save(member)

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
			memberGrade == member.memberGrade

			roles.get(0) == member.roles.get(0)
			accountNonExpired == member.accountNonExpired
			accountNonLocked == member.accountNonLocked
			credentialsNonExpired == member.credentialsNonExpired
			enabled == member.enabled

			createdAt == member.createdAt
			modifiedAt == member.modifiedAt
		}

	}
}
