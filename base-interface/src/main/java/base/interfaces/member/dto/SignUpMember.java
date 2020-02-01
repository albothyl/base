package base.interfaces.member.dto;

import base.support.validator.PhoneNum;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SignUpMember {

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

    @PhoneNum
    private String memberPhoneNumber;
}
