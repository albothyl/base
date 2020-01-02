package base.domain.member;

import base.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MemberFinder {

	private MemberRepository memberRepository;

	public MemberFinder(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Optional<Member> findMemberById(Long memberId) {
		return memberRepository.findById(memberId);
	}

	public Optional<Member> findMemberByEmail(String memberEmail) {
		return memberRepository.findMemberByMemberEmail(memberEmail);
	}
}
