package base.interfaces.member.dto;

import base.domain.member.entity.Address;
import base.support.validator.PhoneNumber;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSignUpRequest {

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
    private Address memberAddress;

    @PhoneNumber
    private String memberPhoneNumber;


}
