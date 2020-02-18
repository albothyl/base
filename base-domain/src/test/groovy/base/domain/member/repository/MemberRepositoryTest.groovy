package base.domain.member.repository

import base.domain.member.entity.Member
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class MemberRepositoryTest extends Specification {

	@Autowired
    MemberRepository memberRepository

	@Shared
	def enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
							.stringLengthRange(3, 5)
							.collectionSizeRange(3, 5)
							.build()


	def "member email exist method test"() {
		given:
		def member = enhancedRandom.nextObject(Member.class, "memberId")

		def savedMember = memberRepository.save(member)

		when:
		def exists = memberRepository.existsByMemberEmail(savedMember.memberEmail)

		then:
		exists
	}

	def "MemberRepository findyByEmail 테스트"(){
		given:
		def member = enhancedRandom.nextObject(Member.class, "memberId")
		def savedMember = memberRepository.save(member)

		when:
		def result = memberRepository.findByMemberEmail(savedMember.memberEmail)

		then:
		result.get() != null
		with(result.get()){
			memberPassword == member.memberPassword
			memberName == member.memberName
			memberEmail == member.memberEmail
			memberAge == member.memberAge
			memberSex == member.memberSex
			memberPhoneNumber == member.memberPhoneNumber
			memberAddress == member.memberAddress
			roles.size() == member.roles.size()
			account == member.account
			memberAddress == member.memberAddress
		}

	}

	def "member queryDsl 휴먼계정 조회 테스트"(){
		given:
		(1..10).each {
			def member = enhancedRandom.nextObject(Member.class)
			memberRepository.save(member)
		}

		when:
		List<Member> resultList = memberRepository.findByDormantMember()

		then:
		resultList.size()>0
	}


	@Ignore
	def "member save test"() {
		given:

		def member = enhancedRandom.nextObject(Member.class, "memberId")


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

			roles.get(0) == member.roles.get(0)

			createdAt == member.createdAt
			modifiedAt == member.modifiedAt
		}

	}
}
