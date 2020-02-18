package base.application.member;

import base.domain.member.entity.Member;
import java.util.Optional;
import base.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberFinder {

	private final MemberRepository memberRepository;

	public Optional<Member> findMemberByEmail(final String memberEmail) {
		return memberRepository.findByMemberEmail(memberEmail);
	}
}
