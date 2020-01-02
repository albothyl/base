package base.application.member;

import base.application.member.exception.MemberPasswordEqualException;
import base.domain.member.MemberRepository;
import base.domain.member.entity.Member;
import base.support.PasswordChanger;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberInfoChanger implements PasswordChanger {

    private final MemberRepository memberRepository;

    @Override
    public void changePassword(final Long id, final String newPassword) {
        final Member member = memberRepository.findById(id)
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
