package base.domain.member.entity;

import base.domain.support.entity.MemberDetails;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = "base", name = "members")
public class Member extends MemberDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;
	private String memberPassword;
	private String memberName;
	private String memberEmail;
	private Integer memberAge;
	private String memberSex;
	private String memberAddress;
	private String memberPhoneNumber;
	private String memberGrade;

	@Builder
    private Member(Long memberId, String memberPassword, String memberName, String memberEmail,
                   Integer memberAge, String memberSex, String memberAddress, String memberPhoneNumber, String memberGrade,
				   List<String> roles, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled) {
	    super(roles, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberAge = memberAge;
        this.memberSex = memberSex;
        this.memberAddress = memberAddress;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberGrade = memberGrade;
    }

	public void changePassword(final String newPassword) {
		memberPassword = newPassword;
	}
}
