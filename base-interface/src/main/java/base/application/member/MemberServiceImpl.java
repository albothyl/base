package base.application.member;

import base.domain.member.MemberRepository;
import base.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public boolean changePassword(Long memberId, String newPassword) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("member is not found")
        );

        String currentPassword = member.getMemberPassword();

        if(currentPassword.equals(newPassword)) {
            throw new IllegalArgumentException("password is equal");
        }

        member.changePassword(newPassword);

        return true;
    }
}
