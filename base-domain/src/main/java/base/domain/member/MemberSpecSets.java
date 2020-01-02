package base.domain.member;

import base.domain.member.entity.Member;
import base.domain.member.entity.Member_;
import base.domain.support.entity.CommonSpecificationUtils;
import org.springframework.data.jpa.domain.Specification;

public class MemberSpecSets {

  public static Specification<Member> findMemberByMemberEmail(String memberEmail) {
    return (root, query, builder) -> builder.and(
        CommonSpecificationUtils.equal(Member_.memberEmail, memberEmail)
            .toPredicate(root, query, builder)
    );
  }
}
