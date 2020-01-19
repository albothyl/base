package base.interfaces.member.converter;

import base.domain.member.entity.Member;
import base.interfaces.member.dto.SignUpMember;
import base.support.PasswordEncoderUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SignUpMemberToMemberConverter implements Converter<SignUpMember, Member> {

    @Override
    public Member convert(SignUpMember signUpMember) {
        String encodePassword = PasswordEncoderUtils.passwordEncode(signUpMember.getMemberPassword());

        return Member.builder()
                .memberName(signUpMember.getMemberName())
                .memberAddress(signUpMember.getMemberAddress())
                .memberAge(signUpMember.getMemberAge())
                .memberEmail(signUpMember.getMemberEmail())
                .memberGrade("default")
                .memberPassword(encodePassword)
                .memberPhoneNumber(signUpMember.getMemberPhoneNumber())
                .memberSex(signUpMember.getMemberSex())
                .credentialsNonExpired(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .roles(Arrays.asList("USER"))
                .build();
    }
}
