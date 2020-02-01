package base.domain.member.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Member.class)
public abstract class Member_ extends base.domain.support.entity.CreatedAndModifiedEntity_ {

	public static volatile SingularAttribute<Member, Integer> memberAge;
	public static volatile ListAttribute<Member, String> roles;
	public static volatile SingularAttribute<Member, Boolean> credentialsNonExpired;
	public static volatile SingularAttribute<Member, String> memberName;
	public static volatile SingularAttribute<Member, String> memberAddress;
	public static volatile SingularAttribute<Member, String> memberSex;
	public static volatile SingularAttribute<Member, Boolean> enabled;
	public static volatile SingularAttribute<Member, String> memberEmail;
	public static volatile SingularAttribute<Member, String> memberPassword;
	public static volatile SingularAttribute<Member, String> memberPhoneNumber;
	public static volatile SingularAttribute<Member, String> memberGrade;
	public static volatile SingularAttribute<Member, Boolean> accountNonExpired;
	public static volatile SingularAttribute<Member, Long> memberId;
	public static volatile SingularAttribute<Member, Boolean> accountNonLocked;

	public static final String MEMBER_AGE = "memberAge";
	public static final String ROLES = "roles";
	public static final String CREDENTIALS_NON_EXPIRED = "credentialsNonExpired";
	public static final String MEMBER_NAME = "memberName";
	public static final String MEMBER_ADDRESS = "memberAddress";
	public static final String MEMBER_SEX = "memberSex";
	public static final String ENABLED = "enabled";
	public static final String MEMBER_EMAIL = "memberEmail";
	public static final String MEMBER_PASSWORD = "memberPassword";
	public static final String MEMBER_PHONE_NUMBER = "memberPhoneNumber";
	public static final String MEMBER_GRADE = "memberGrade";
	public static final String ACCOUNT_NON_EXPIRED = "accountNonExpired";
	public static final String MEMBER_ID = "memberId";
	public static final String ACCOUNT_NON_LOCKED = "accountNonLocked";

}

