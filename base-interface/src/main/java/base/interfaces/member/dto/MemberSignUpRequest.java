package base.interfaces.member.dto;

import base.domain.support.entity.MemberDetails;
import base.support.validator.PhoneNumber;
import lombok.*;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSignUpRequest extends MemberDetails {

    @NotBlank(message = "Please enter your password.")
    @Size(min = 8)
    private String memberPassword;

    @NotEmpty(message = "Please enter your name.")
    private String memberName;

    @NotBlank(message = "Please enter your email.")
    @Email(message = "Please observe the email entry form.")
    private String memberEmail;

    @Positive(message = "You can enter only positive numbers.")
    @NotNull(message = "Please enter your age.")
    private Integer memberAge;

    @NotEmpty
    private String memberSex;

    @NotEmpty
    private String memberAddress;

    @PhoneNumber
    private String memberPhoneNumber;

    private String memberGrade;

    @Builder
    private MemberSignUpRequest(String memberPassword, String memberName, String memberEmail,
                                Integer memberAge, String memberSex, String memberAddress, String memberPhoneNumber, String memberGrade,
                                List<String> roles, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled) {
        super(roles, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberAge = memberAge;
        this.memberSex = memberSex;
        this.memberAddress = memberAddress;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberGrade = memberGrade;
    }


}
