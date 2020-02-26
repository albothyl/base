package base.application.member;

import base.domain.member.entity.Member;
import base.domain.member.exception.EmailNotFoundException;
import base.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberFinder {

    private final MemberRepository memberRepository;

    public Member findByMemberEmail(final String memberEmail) {
        final Member member = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new EmailNotFoundException(memberEmail));
        return member;
    }
}
