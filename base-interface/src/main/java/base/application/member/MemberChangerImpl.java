package base.application.member;

import base.application.member.exception.MemberPasswordEqualException;
import base.domain.member.MemberRepository;
import base.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberChangerImpl implements MemberChanger {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void changePassword(final Long memberId, final String newPassword) {
        final Member member = memberRepository.findById(memberId)
            .orElseThrow(
                () -> new IllegalArgumentException("member is not found")
            );

        final String currentPassword = member.getMemberPassword();

        if(StringUtils.equals(currentPassword, newPassword)) {
            throw new MemberPasswordEqualException("new password and exist password are equal");
        }

        member.changePassword(newPassword);
    }
}
