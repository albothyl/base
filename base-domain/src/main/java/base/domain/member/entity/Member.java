package base.domain.member.entity;

import base.domain.support.entity.CreatedAndModifiedEntity;
import base.domain.support.entity.RoleAttributeConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Builder @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(schema = "base", name = "members")
public class Member extends CreatedAndModifiedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;
	private String memberPassword;
	private String memberName;
	private String memberEmail;
	private Integer memberAge;
	private String memberSex;
	private String memberPhoneNumber;

    @Convert(converter = RoleAttributeConverter.class)
    private List<String> roles;

	@Embedded
	private Account account;

	@Embedded
	private Address memberAddress;


	public void changePassword(final String newPassword) {
		memberPassword = newPassword;
	}
}
