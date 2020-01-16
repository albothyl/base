package base.domain.member;

import base.domain.member.entity.Member;
import base.domain.member.exception.MemberDuplicatedException;
import base.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSaver {

    private final MemberRepository memberRepository;

    public Member signUp(final Member member) {
        String memberEmail = member.getMemberEmail();
        boolean exists = memberRepository.existsMemberByMemberEmail(memberEmail);

        if(exists) {
            throw new MemberDuplicatedException("memberEmail을 이미 사용중입니다.");
        }

        return memberRepository.save(member);
    }
}
