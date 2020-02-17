package base.interfaces.member.converter;

import base.domain.member.entity.Member;
import base.interfaces.member.dto.MemberSignUpRequest;
import base.support.PasswordEncoderUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SignUpMemberToMemberConverter implements Converter<MemberSignUpRequest, Member> {

    @Override
    public Member convert(MemberSignUpRequest memberSignUpRequest) {
        String encodePassword = PasswordEncoderUtils.passwordEncode(memberSignUpRequest.getMemberPassword());

        return Member.builder()
                .memberName(memberSignUpRequest.getMemberName())
                .memberAddress(memberSignUpRequest.getMemberAddress())
                .memberAge(memberSignUpRequest.getMemberAge())
                .memberEmail(memberSignUpRequest.getMemberEmail())
                .memberPassword(encodePassword)
                .memberPhoneNumber(memberSignUpRequest.getMemberPhoneNumber())
                .memberSex(memberSignUpRequest.getMemberSex())
                .roles(Arrays.asList("USER"))
                .build();
    }
}
