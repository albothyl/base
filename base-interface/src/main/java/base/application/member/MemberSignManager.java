package base.application.member;

import base.domain.member.entity.Member;
import base.domain.member.exception.MemberDuplicatedException;
import base.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSignManager {

    private final MemberRepository memberRepository;

    public Member signUp(final Member member) {
        validateMemberEmail(member.getMemberEmail());

        return memberRepository.save(member);
    }

    private void validateMemberEmail(String memberEmail) {
        boolean exists = memberRepository.existsByMemberEmail(memberEmail);

        if(exists) {
            throw new MemberDuplicatedException(memberEmail);
        }
    }
}
