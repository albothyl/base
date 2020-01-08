package base.domain.member;

import base.domain.member.entity.Member;
import base.domain.member.exception.MemberPasswordEqualException;
import base.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberChanger {

    private final MemberFinder memberFinder;

    public void changePassword(final Long id, final String newPassword) {
        final Member member = memberFinder.findMemberById(id);

        final String currentPassword = member.getMemberPassword();

        if(StringUtils.equals(currentPassword, newPassword)) {
            throw new MemberPasswordEqualException("new password and exist password are equal");
        }

        member.changePassword(newPassword);
    }
}
