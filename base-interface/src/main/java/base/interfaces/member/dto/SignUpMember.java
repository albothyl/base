package base.interfaces.member.dto;

import base.domain.member.entity.Member;
import base.support.PasswordEncoderUtils;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.Arrays;

@Getter
@ToString
public class SignUpMember {

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8)
    private String memberPassword;

    @NotEmpty(message = "이름을 입력해주세요.")
    private String memberName;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "메일 입력 양식을 지켜주세요.")
    private String memberEmail;

    @Positive(message = "양수만 입력 가능합니다.")
    @NotNull(message = "나이를 입력해주세요.")
    private Integer memberAge;

    @NotEmpty
    private String memberSex;

    @NotEmpty
    private String memberAddress;

    @Pattern(regexp = "[0-9]{10,11}", message = "핸드폰 입력 양식을 지켜주세요.")
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
