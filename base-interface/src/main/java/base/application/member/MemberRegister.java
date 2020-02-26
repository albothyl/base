package base.application.member;

import base.domain.member.entity.Member;
import base.domain.member.exception.MemberDuplicatedException;
import base.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberRegister {

    private final MemberRepository memberRepository;

    public Member signUp(final Member member) {
        validateMemberEmail(member.getMemberEmail());

        return memberRepository.save(member);
    }

    private void validateMemberEmail(String memberEmail) {
        if(memberRepository.existsByMemberEmail(memberEmail)) {
            throw new MemberDuplicatedException(memberEmail);
        }
    }
}
