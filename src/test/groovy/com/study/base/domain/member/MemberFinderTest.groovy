package com.study.base.domain.member

import com.study.base.domain.member.entity.Member
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import spock.lang.Specification

@SpringBootTest
//@Transactional //rollback
class MemberFinderTest extends Specification {

	@Autowired
	MemberRepository memberRepository

	def "member save test"() {
		given:
		def passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
		def password = passwordEncoder.encode("admin")

		def member = Member.builder()
				.memberPassword(password)
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
		println(result)
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
