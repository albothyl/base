package com.study.base.domain.member.entity;

import com.study.base.domain.support.entity.CreatedAndModifiedEntity;
import com.study.base.domain.support.entity.RoleAttributeConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members", schema = "base")
public class Member extends CreatedAndModifiedEntity {

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

	@Convert(converter = RoleAttributeConverter.class)
	private List<String> roles;
	private Boolean accountNonExpired;
	private Boolean accountNonLocked;
	private Boolean credentialsNonExpired;
	private Boolean enabled;
}
