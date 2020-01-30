package base.domain.member;

import base.domain.member.entity.Member;
import java.util.Optional;
import base.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberFinder {

	private final MemberRepository memberRepository;

	public Optional<Member> findMemberById(Long memberId) {
		return memberRepository.findById(memberId);
	}

	public Optional<Member> findMemberByEmail(String memberEmail) {
		return memberRepository.findByMemberEmail(memberEmail);
	}
}
