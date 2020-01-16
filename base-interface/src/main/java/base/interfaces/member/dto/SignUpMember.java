package base.interfaces.member.dto;

import base.domain.member.entity.Member;
import base.support.PasswordEncoderUtils;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Arrays;

@Getter
@ToString
public class SignUpMember {

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String memberPassword;

    @NotEmpty(message = "이름을 입력해주세요.")
    private String memberName;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "메일 양식을 지켜주세요.")
    private String memberEmail;

    private Integer memberAge;
    private String memberSex;
    private String memberAddress;

    @Pattern(regexp = "[0-9]{10,11}", message = "핸드폰 입력 형식을 지켜주세요.")
    private String memberPhoneNumber;

    public Member signUpMemberToMember() {
        String encodePassword = PasswordEncoderUtils.passwordEncode(memberPassword);

        return Member.builder()
                .memberName(memberName)
                .memberAddress(memberAddress)
                .memberAge(memberAge)
                .memberEmail(memberEmail)
                .memberGrade("default")
                .memberPassword(encodePassword)
                .memberPhoneNumber(memberPhoneNumber)
                .memberSex(memberSex)
                .credentialsNonExpired(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .roles(Arrays.asList("USER"))
                .build();
    }
}
