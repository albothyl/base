package base.application.member;

import base.domain.member.entity.Member;
import base.domain.member.exception.MemberNotFoundException;
import base.domain.member.exception.MemberPasswordEqualException;
import base.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberModifier {

    private final MemberRepository memberRepository;

    public void changePassword(final Long memberId, final String newPassword) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new MemberNotFoundException(memberId));

        final String currentPassword = member.getMemberPassword();

        checkPasswordEqual(currentPassword, newPassword);

        member.changePassword(newPassword);
    }

    private void checkPasswordEqual(String currentPassword, String newPassword) {
        if(StringUtils.equals(currentPassword, newPassword)) {
            throw new MemberPasswordEqualException();
        }
    }
}
